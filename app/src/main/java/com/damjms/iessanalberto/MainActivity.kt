package com.damjms.iessanalberto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Variables para los elementos de la interfaz
    private lateinit var edtIntentos: EditText
    private lateinit var edtIntroduceLetra: EditText
    private lateinit var btnComprobar: Button
    private lateinit var edtPalabra: EditText
    private lateinit var edtLetrasNoEstan: EditText
    private lateinit var txtGameOver: TextView

    // Palabra secreta y estado actual mostrado al usuario
    private var palabraSecreta = "Kotlin"
    private var palabraMostrada = "*".repeat(palabraSecreta.length)

    // Letras incorrectas y número de intentos
    private var letrasNoEstan: MutableSet<Char> = mutableSetOf()
    private var numIntentos = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de elementos de la interfaz
        edtIntentos = findViewById(R.id.edtIntentos)
        edtIntroduceLetra = findViewById(R.id.edtIntroduceLetra)
        btnComprobar = findViewById(R.id.btnComprobar)
        edtPalabra = findViewById(R.id.edtPalabra)
        edtLetrasNoEstan = findViewById(R.id.edtLetrasNoEstan)
        txtGameOver = findViewById(R.id.txtGameOver)

        // Configuración inicial de los elementos de la interfaz
        edtIntentos.setText(numIntentos.toString())
        edtPalabra.setText(palabraMostrada)

        btnComprobar.setOnClickListener {
            // Obtención de la letra introducida por el usuario
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
                // Mostrar mensaje de victoria si se adivina la palabra
                txtGameOver.text = "¡GANASTE!"
                txtGameOver.visibility = View.VISIBLE
                btnComprobar.isEnabled = false
            }

            edtIntroduceLetra.text.clear()
        }
    }

    private fun actualizarPalabraMostrada(letra: Char) {
        // Actualizar la palabra mostrada al usuario con la letra adivinada
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
        // Decrementar el número de intentos y actualizar la interfaz
        numIntentos--
        edtIntentos.setText(numIntentos.toString())

        if (numIntentos == 0) {
            // Mostrar mensaje de fin de juego si se agotan los intentos
            txtGameOver.text = "GAME OVER. La palabra era: $palabraSecreta"
            txtGameOver.visibility = View.VISIBLE
            btnComprobar.isEnabled = false
        }
    }
}
