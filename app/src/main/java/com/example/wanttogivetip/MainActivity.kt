package com.example.wanttogivetip

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanttogivetip.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonTocalc.setOnClickListener { calctip() }
    }

    private fun calctip() {
        val stringInTextField = binding.costEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text=""
            return
        }
        val selectid=binding.radioButton.checkedRadioButtonId
        val tippercent=when(selectid){
            R.id.twenty_percent->0.20
            R.id.fifteen_percent->0.15
            else->0.10
        }
        var Tip=tippercent*cost
        val roundoff=binding.switchOne.isChecked
        if(roundoff){
            Tip= kotlin.math.ceil(Tip)
        }
        val locale=Locale.getDefault()
        val formattedtip=java.text.NumberFormat.getCurrencyInstance(locale).format(Tip)
        binding.tipResult.text=getString(R.string.tip_amount,formattedtip)
    }
}