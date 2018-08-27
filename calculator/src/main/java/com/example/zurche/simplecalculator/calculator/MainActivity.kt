package com.example.zurche.simplecalculator.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zero.setOnClickListener { /*perform zero action here*/ }
        one.setOnClickListener { /*perform one action here*/ }
        two.setOnClickListener { /*perform two action here*/ }
        three.setOnClickListener { /*perform three action here*/ }
        four.setOnClickListener { /*perform four action here*/ }
        five.setOnClickListener { /*perform five action here*/ }
        six.setOnClickListener { /*perform six action here*/ }
        seven.setOnClickListener { /*perform seven action here*/ }
        eight.setOnClickListener { /*perform eight action here*/ }
        nine.setOnClickListener { /*perform nine action here*/ }

        ac.setOnClickListener { /*perform AllClear() here*/ }
        plus_minus_switch.setOnClickListener { /*perform PlusMinusSwitch action here*/ }
        percentage.setOnClickListener { /*perform Percentage action here*/ }
        comma.setOnClickListener { /*perform comma action here*/ }
        equals.setOnClickListener { /*perform equals action here*/ }

        divide.setOnClickListener { /*perform divide action here*/ }
        multiply.setOnClickListener { /*perform multiply action here*/ }
        plus.setOnClickListener { /*perform plus action here*/ }
        minus.setOnClickListener { /*perform minus action here*/ }
    }
}
