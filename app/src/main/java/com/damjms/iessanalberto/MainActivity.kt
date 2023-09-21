package com.damjms.iessanalberto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var edtIntentos: EditText
    private lateinit var edtIntroduceLetra: EditText
    private lateinit var btnComprobar: Button
    private lateinit var edtPalabra: EditText
    private lateinit var edtLetrasNoEstan: EditText
    private lateinit var txtGameOver: TextView

    private var palabraSecreta = "Kotlin"
    private var numIntentos = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtIntentos = findViewById(R.id.edtIntentos)
        edtIntroduceLetra = findViewById(R.id.edtIntroduceLetra)
        btnComprobar = findViewById(R.id.btnComprobar)
        edtPalabra = findViewById(R.id.edtPalabra)
        edtLetrasNoEstan = findViewById(R.id.edtLetrasNoEstan)
        txtGameOver = findViewById(R.id.txtGameOver)

    }

    fun adivinaPalabra() {
        while(numIntentos > 0){

        }
    }
}