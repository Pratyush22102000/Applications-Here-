package com.example.calcullator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var answer:TextView? = null
    private var lastNumeric:Boolean = true
    var lastDot:Boolean = false

//////////////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        answer = findViewById(R.id.answer)
        }

///////////////////////////////////////////////////////////////////////////////////////////////////

     fun onDigit(view:View){
        Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        answer?.append((view as Button).text)
         lastNumeric = true

    }


///////////////////////////////////////////////////////////////////////////////////////////////////


    fun onClear(view:View){
        answer?.text = ""
    }



///////////////////////////////////////////////////////////////////////////////////////////////////



    fun onDecimalPoint(view: View){
        if(answer?.text == ""){
            answer?.append("0.")
            lastDot =  true
            lastNumeric = false
        }
        if(lastNumeric && !lastDot){
            answer?.append(".")

            lastDot =  true
            lastNumeric = false
        }
    }




/////////////////////////////////////////////////////////////////////////////////////////////////////

    // ALl the operators will call the onOperator which will check the text in the answer textView that if it is empty and if not then will do
    // the instruction
    fun onOperator(view:View){// in this let statement we are assuring that (answer?.) if answer is not empty (text?.) if text is not empty
        // so execute the following code hence we are safely executing the code

        answer?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){// In this if statement we are checking if the last character entered is a
                // numeral and not an operator
                answer?.append((view as Button).text)
                lastDot =  false
              lastNumeric = false
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

   fun onEqual(view:View) {
        if(lastNumeric){
            var result = answer?.text.toString()
            var prefix = ""
            try{
                if(result.startsWith("-")){
                    prefix ="-"
                    result.substring(1)
                }
                if(result.contains("-")) {

                    val splitValue = result.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if((prefix.isNotEmpty())){
                        one = prefix + one
                    }
                    answer?.text = removeZeroAfter((one.toDouble() - two.toDouble()).toString())
                }else  if(result.contains("+")) {

                    val splitValue = result.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if((prefix.isNotEmpty())){
                        one = prefix + one
                    }
                    answer?.text = removeZeroAfter((one.toDouble() + two.toDouble()).toString())
                }else  if(result.contains("/")) {

                    val splitValue = result.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if((prefix.isNotEmpty())){
                        one = prefix + one
                    }
                    answer?.text = removeZeroAfter((one.toDouble() / two.toDouble()).toString())
                }else  if(result.contains("*")) {

                    val splitValue = result.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if((prefix.isNotEmpty())){
                        one = prefix + one
                    }
                    answer?.text = removeZeroAfter((one.toDouble() * two.toDouble()).toString())
                }
            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
   }
///////////////////////////////////////////////////////////////////////////////////////////////////
private fun removeZeroAfter(result:String):String{
    var value = result
    if(value.contains(".0")){
        value = value.substring(0,value.length-2)


    }
    return value
}


private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }

}