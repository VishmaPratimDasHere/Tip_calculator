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

        // We pass the properties of the present activity via the layoutInflater property to the binding class's inflate method
        // It creates a new object containing a view group object and passes a reference to that object
        reference=ActivityMainBinding.inflate(layoutInflater)
        setContentView(reference.root)

        //------------------------------------------VIEWBINDING ENDS HERE---------------------------

        // Left side
        val tvTipPercentageLabel=reference.tvTipPercentageLabel

        // Right side
        val etAmount=reference.etAmount
        val seekBar=reference.seekBar
        val tvTipAmount=reference.tvTipAmount
        val tvTotalAmount=reference.tvTotalAmount

        // Set listener on seekbar and reflect change in tvTipPercentagelabel and tvTipAmount
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tvTipPercentageLabel.text= p1.toString()+"%"
                // Tip=% of etAmount
                var Base = etAmount.text.toString().toInt()
                var TipPercent = p1.toInt()
                var Tip = (Base*TipPercent*0.01).toInt()
                Log.i("Main Activity","Base: $Base p1: $p1 Tip percentage: $TipPercent Tip: $Tip ")
                tvTipAmount.text="$Tip"
                tvTotalAmount.text=(Base+Tip).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        etAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()=="") return

                // Take Base, tip percentage
                val Base=p0.toString().toInt()
                val TipPercentage=seekBar.progress.toInt()
                tvTipAmount.text=(Base*TipPercentage*0.01).toString()
                tvTotalAmount.text=((Base*TipPercentage*0.01)+Base).toString()
                // Update tvTipAmount and tvTotalAmount
                Log.i("Text Change Listener","Base: $Base Tip percentage: $TipPercentage Tip: ${tvTipAmount.text}")
            }
        })
    }


}