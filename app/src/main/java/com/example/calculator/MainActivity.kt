package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var screen: TextView? = null
    private var dot: Boolean = false
    private var lastnum: Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screen = findViewById(R.id.screen)
    }

    fun onDigit(view: View) {
        screen?.append((view as Button).text)
        lastnum=true
        dot=false
    }

    fun clear(view:View){
        screen?.text=""
    }

    fun delete(view: View) {
        val text = screen?.text.toString()
        if (text.isNotEmpty()) {
            screen?.text = text.substring(0, text.length - 1)
        }
    }

    fun Equal(view: View){
        if (lastnum){
            var screenVal=screen?.text.toString()
            var pref=""
            try {
                if (screenVal.startsWith("-")){
                    pref="-"
                    screenVal=screenVal.substring(1)
                }
                if (screenVal.contains("-")){
                val splitval=screenVal.split("-")
                var one=splitval[0]
                var two=splitval[1]

                if(pref.isNotEmpty()){
                    one=pref+one
                }

                var res=one.toDouble()-two.toDouble()

                screen?.text=res.toString()
                }
                if (screenVal.contains("+")){
                    val splitval=screenVal.split("+")
                    var one=splitval[0]
                    var two=splitval[1]

                    if(pref.isNotEmpty()){
                        one=pref+one
                    }

                    var res=one.toDouble()+two.toDouble()

                    screen?.text=res.toString()
                }
                if (screenVal.contains("%")){
                    val splitval=screenVal.split("%")
                    var one=splitval[0]
                    var two=splitval[1]

                    if(pref.isNotEmpty()){
                        one=pref+one
                    }

                    var res=one.toDouble()/two.toDouble()

                    screen?.text=res.toString()
                }
                if (screenVal.contains("*")){
                    val splitval=screenVal.split("*")
                    var one=splitval[0]
                    var two=splitval[1]

                    if(pref.isNotEmpty()){
                        one=pref+one
                    }

                    var res=one.toDouble()*two.toDouble()

                    screen?.text=res.toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

     fun operator(view: View){
        screen?.text.let {
            if (lastnum && !operatorAdded(it.toString())){
                screen?.append((view as Button).text)
                lastnum=false
                dot=false
            }
        }
    }

    private fun operatorAdded(value: String): Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            (value.contains("/")) ||
                    (value.contains("*")) ||
                    (value.contains("-")) ||
                    (value.contains("+"))
        }
    }

    fun decimal(view: View){
        if(lastnum && !dot){
            screen?.append(".")
            lastnum=false
            dot=true
        }
    }
}
