package com.damjms.iessanalberto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    private var palabraMostrada = "*".repeat(palabraSecreta.length)
    private var letrasNoEstan: MutableSet<Char> = mutableSetOf()
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

        edtIntentos.setText(numIntentos.toString())
        edtPalabra.setText(palabraMostrada)

        btnComprobar.setOnClickListener {
            val letra = edtIntroduceLetra.text.toString().uppercase().getOrNull(0)

            if (letra != null) {
                if (palabraSecreta.contains(letra)) {
                    actualizarPalabraMostrada(letra)
                } else {
                    letrasNoEstan.add(letra)
                    edtLetrasNoEstan.setText(letrasNoEstan.joinToString(" ") { "_" })
                    decrementarIntento()
                }
            }

            if (palabraMostrada == palabraSecreta) {
                txtGameOver.text = "¡GANASTE!"
                txtGameOver.visibility = View.VISIBLE
                btnComprobar.isEnabled = false
            }

            edtIntroduceLetra.text.clear()
        }
    }

    private fun actualizarPalabraMostrada(letra: Char) {
        val nuevaPalabraMostrada = palabraMostrada.toCharArray()
        for (i in palabraSecreta.indices) {
            if (palabraSecreta[i] == letra) {
                nuevaPalabraMostrada[i] = letra
            }
        }
        palabraMostrada = nuevaPalabraMostrada.joinToString(" ")
        edtPalabra.setText(palabraMostrada)
    }

    private fun decrementarIntento() {
        numIntentos--
        edtIntentos.setText(numIntentos.toString())

        if (numIntentos == 0) {
            txtGameOver.text = "GAME OVER. La palabra era: $palabraSecreta"
            txtGameOver.visibility = View.VISIBLE
            btnComprobar.isEnabled = false
        }
    }
}
