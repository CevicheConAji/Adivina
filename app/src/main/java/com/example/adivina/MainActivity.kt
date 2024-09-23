package com.example.adivina

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var etNumero:EditText
    lateinit var btnJugar:Button
    lateinit var tvInfoJugada:TextView
    lateinit var tvNumeroJugada:TextView
    lateinit var btnReset:Button

    var nAleatorio:Int = (1..100).random();
    var nIntroducir:Int = 0
    var contador:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initComponets()
        Log.i("Piero Numero Random",nAleatorio.toString())
        initListener()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponets(){

        etNumero = findViewById(R.id.etNumero)
        btnJugar = findViewById(R.id.btnJugar)
        tvInfoJugada = findViewById(R.id.tvInfoJugada)
        tvNumeroJugada = findViewById(R.id.tvNumeroJugada)
        btnReset = findViewById(R.id.btnReset)

    }
    private fun initListener(){
        btnJugar.setOnClickListener(){
            jugar()
        }
        btnReset.setOnClickListener(){
            reset()
        }
    }
    private fun jugar(){
        //importante .text
        nIntroducir = etNumero.text.toString().toInt()

        if(nAleatorio > nIntroducir){
            tvInfoJugada.text = "El número secreto es mayor"
            contador++
        }
        if(nAleatorio < nIntroducir){
            tvInfoJugada.text = "El número secreto es menor"
            contador++
        }
        if(nAleatorio.equals(nIntroducir) ){
            tvInfoJugada.text = "Ganaste"
            btnJugar.text = "Ganaste"
        }

        tvNumeroJugada.text = contador.toString()
        if(contador >= 3){
            btnJugar.isEnabled = false
            tvInfoJugada.text = "Perdiste"
        }


    }
    private fun reset(){
        nAleatorio = (1..5).random();
        Log.i("Piero nuevo Random",nAleatorio.toString())
        btnJugar.isEnabled = true
        contador = 0
        btnJugar.text = "Jugar"
        tvInfoJugada.text = "info de la jugada"
    }
}