package com.example.calc

fun calculate(str: String): String {
    if(checkInput(str)){
        val x = splitter(str)
        val arr = infxInPost(x)
        val stack = ArrayDeque<String>()
        for (i in arr) {
            if (i !in "+-*/") {
                stack.add(i)
            } else {
                val x1 = stack.removeLast().toInt()
                val x2 = stack.removeLast().toInt()
                if (i == "+") stack.add((x1 + x2).toString())
                else if (i == "-") stack.add((x2 - x1).toString())
                else if (i == "*") stack.add((x2 * x1).toString())
                else if (i == "/") stack.add((x2 / x1).toString())
            }
        }
        if (stack.size == 1) {
            return stack.last()
        }
    }
    return "Invalid input"
}

fun splitter(str: String): MutableList<String> {
    val arr: MutableList<String> = mutableListOf()
    if (checkBrackets(str)) {
        var st = ""
        for (i in str) {
            if (i !in "*/-+()") {
                st += i
            } else {
                if (st != "") {
                    arr.add(st)
                }
                st = ""
                arr.add(i.toString())
            }
        }
        if (st != "") {
            arr.add(st)
        }
        return arr
    }
    return arr
}

fun infxInPost(arr: MutableList<String>): MutableList<String> {
    val stack = ArrayDeque<String>()
    val res: MutableList<String> = mutableListOf()
    var i = 0
    while (i < arr.size) {
        if (arr[i] !in "+-/*()") {
            res.add(arr[i])
        }
        if (arr[i] in "+-/*()" && stack.isEmpty()) {
            stack.addLast(arr.get(i))
        } else if (arr[i] in "+-/*()" && !stack.isEmpty()) {
            if (arr[i] == "*" || arr[i] == "/") {
                while (!stack.isEmpty()) {
                    if (stack.last() == "*" || stack.last() == "/") {
                        val t = stack.removeLast()
                        res.add(t)
                    } else break
                }
                stack.addLast(arr[i])

            } else if (arr[i] == "+" || arr[i] == "-") {
                while (!stack.isEmpty()) {
                    if (stack.last() != "(") {
                        val t = stack.removeLast()
                        res.add(t)
                    } else break
                }
                stack.addLast(arr[i])

            } else if (arr[i] == "(") {
                stack.addLast(arr[i])
            } else if (arr[i] == ")") {
                var r = ""
                while (r != "(") {
                    r = stack.removeLast()
                    if (r !in "()") {
                        res.add(r)
                    }
                }
            }
        }
        i += 1
    }
    while (!stack.isEmpty()){
        val p = stack.removeLast()
        res.add(p)
    }
    return res
}

fun checkBrackets(str: String): Boolean {
    val stack = ArrayDeque<Char>()

    for (symbol in str) {
        if (symbol == '(' || symbol == '{' || symbol == '[') {
            stack.addLast(symbol)
        } else if ((symbol == ')' || symbol == '}' || symbol == ']') && !stack.isEmpty()) {
            if (symbol == ')' && stack.last() == '(') {
                stack.removeLast()
            } else if (symbol == '}' && stack.last() == '{') {
                stack.removeLast()
            } else if (symbol == ']' && stack.last() == '[') {
                stack.removeLast()
            } else return false
        } else if ((symbol == ')' || symbol == '}' || symbol == ']') && stack.isEmpty()) {
            return false
        }
    }
    return stack.isEmpty()
}
fun checkInput(str : String): Boolean{
    for(i in 0 until str.length-1){
        if(str[i] in "(" && str[i+1] in "/*-+"){
            return false
        }
        if(str[i] == '/' && str[i+1] == '0'){
            return false
        }
    }
    return true
}