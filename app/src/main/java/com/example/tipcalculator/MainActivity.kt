package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Viewbinding
    // Binding class is generated from XML
    // We create a lateinit object of type ActivityMainBinding
    private lateinit var reference: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        reference=ActivityMainBinding.inflate(layoutInflater)
        setContentView(reference.root)

        this.initialState() // Early call to change views to ""

        val tvTipPercentageLabel=reference.tvTipPercentageLabel

        val etAmount=reference.etAmount
        val seekBar=reference.seekBar
        val tvTipAmount=reference.tvTipAmount
        val tvTotalAmount=reference.tvTotalAmount

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvTipPercentageLabel.text= p1.toString()+"%"
                Update()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        etAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Update()
            }
        })
    }
    private fun Update() {
        if (reference.etAmount.text.toString()=="") {
            reference.tvTipAmount.text=""
            reference.tvTotalAmount.text=""
            return
        }
        var Base = reference.etAmount.text.toString().toFloat()
        var TipPercent = reference.seekBar.progress.toFloat()
        var Tip = (Base*TipPercent*0.01).toFloat()

        this.reference.tvTipAmount.text="%.2f".format(Tip)
        this.reference.tvTotalAmount.text="%.2f".format(Tip+Base)
    }

    private fun initialState() {
        reference.tvTipAmount.text=""
        reference.tvTotalAmount.text=""
    }
}