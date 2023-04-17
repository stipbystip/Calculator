package com.example.calc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.calc.databinding.ActivityMainBinding
import com.example.calc.calculate


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun editNums(view: View) {
        var num: String = binding.editText.text.toString()
        if (num == "Edit") {
            num = ""
        }
        when (view.id) {
            binding.buttonOne.id -> num = num + "1"
            binding.buttonTwo.id -> num = num + "2"
            binding.buttonThree.id -> num = num + "3"
            binding.buttonFour.id -> num = num + "4"
            binding.buttonFive.id -> num = num + "5"
            binding.buttonSix.id -> num = num + "6"
            binding.buttonSeven.id -> num = num + "7"
            binding.buttonEight.id -> num = num + "8"
            binding.buttonNine.id -> num = num + "9"
            binding.buttonZero.id -> num = num + "0"
        }
        binding.editText.setText(num)

    }

    fun editOperations(view: View) {
        var str: String = binding.editText.text.toString()
        if (str == "Edit" || str == "") {
            str = ""
            binding.editText.setText(str)
        } else {
            val lastest: String = str.last().toString()
            if (lastest !in "0123456789()"){
                if((lastest == binding.buttonMinus.text.toString()) and (view.id == binding.buttonMinus.id)){
                    str = str.substring(0, str.length - 1)
                    str = str + "+"
                    binding.editText.setText(str)
                }
                else{
                    str = str.substring(0, str.length - 1)
                    when (view.id) {
                        binding.buttonMinus.id -> str = str + "-"
                        binding.buttonPlus.id -> str = str + "+"
                        binding.buttonDiv.id -> str = str + '/'
                        binding.buttonMul.id -> str = str + '*'
                    }
                    binding.editText.setText(str)
                }
            }else{
                when (view.id) {
                    binding.buttonMinus.id -> str = str + "-"
                    binding.buttonPlus.id -> str = str + "+"
                    binding.buttonDiv.id -> str = str + '/'
                    binding.buttonMul.id -> str = str + '*'
                }
                binding.editText.setText(str)
            }
        }
    }

    fun clearEdit(view: View) {
        var str: String = binding.editText.text.toString()
        if(str != "Edit"){
            str = "Edit"
            binding.editText.setText(str)
        }
    }

    fun eraseSim(view: View) {
        var str: String = binding.editText.text.toString()
        if(str != "Edit"){
            str = str.replaceFirst(".$".toRegex(), "")
            binding.editText.setText(str)
        }
        else{
            binding.editText.setText(str)
        }
    }

    fun editBrackets(view: View) {
        var str: String = binding.editText.text.toString()
        if(str == "Edit"){
            str = ""
        }
        when(view.id){
            binding.buttonBracket1.id -> str = str + "("
            binding.buttonBracket2.id -> str = str + ")"
        }
        binding.editText.setText(str)
    }


    fun Calc(view: View) {
            var str: String = binding.editText.text.toString()
            var res = ""
            if (str != "Edit") {
                when (view.id) {
                    binding.buttonEquals.id -> res = calculate(str)
                }
            }
            binding.editText.setText(res)

    }

}