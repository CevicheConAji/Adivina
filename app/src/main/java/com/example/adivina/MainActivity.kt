package com.example.adivina

import android.os.Bundle
import android.text.Editable
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
    lateinit var tvPista:TextView
    lateinit var btnJugadaExtra:Button

    var nAleatorio:Int = (1..100).random();
    var nIntroducir:Int = 0
    var contador:Int = 0
    var numeroJugadas:Int = 4



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initComponets()
        Log.i("Piero Número",nAleatorio.toString())
        initListener()
        incio()

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
        tvPista = findViewById(R.id.tvPista)
        btnJugadaExtra = findViewById(R.id.btnJugadaExtra)

    }
    private fun initListener(){
        btnJugar.setOnClickListener(){
            jugar()
        }
        btnJugadaExtra.setOnClickListener(){
            jugadaExtra()

        }
        btnReset.setOnClickListener(){
            reset()
        }
    }
    private fun incio(){
        //valor por defecto en el editable
        var nAleatorio02:Int = (1..100).random();
        etNumero.text = Editable.Factory.getInstance().newEditable(nAleatorio02.toString())
        //Desactivamos boton jugada extra
        btnJugadaExtra.isEnabled = false
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

        tvNumeroJugada.text = "JUGADA "+contador.toString()

        if(contador == (numeroJugadas-2)){
            var pistaNumero:String = (5+nAleatorio).toString()
            tvPista.text = "PISTA EL NÚMERO SECRETO ES "+pistaNumero
        }
        if(contador == (numeroJugadas+1)){
            tvNumeroJugada.text = "JUGADA EXTRA"
            btnJugadaExtra.isEnabled = false
        }
        if(contador > numeroJugadas){
            btnJugar.isEnabled = false
            tvInfoJugada.text = "Perdiste"
            //Si perdiste activamos el botonJugadaExtra
            btnJugadaExtra.isEnabled = true
        }

        if(nAleatorio.equals(nIntroducir) ){
            tvInfoJugada.text = "Ganaste"
            btnJugar.text = "Ganaste"
            tvPista.text = "ENHORABUENA"
        }


    }
    private fun reset(){
        nAleatorio = (1..100).random()
        Log.i("Piero nuevo Random",nAleatorio.toString())
        btnJugar.isEnabled = true
        contador = 0
        btnJugar.text = "Jugar"
        tvInfoJugada.text = "info de la jugada"
        tvNumeroJugada.text = contador.toString()
        tvPista.text = "Esperando Pista"
        numeroJugadas = 4
    }
    private fun jugadaExtra(){
        btnJugar.isEnabled = true
        tvNumeroJugada.text = "JUGADA EXTRA"
        btnJugadaExtra.isEnabled = false
    }
}