package com.damjms.iessanalberto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
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

    private var palabraSecreta = "KOTLIN"
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

        // para que solo pueda escribir una letra cada vez
        edtIntroduceLetra.filters = arrayOf(InputFilter.LengthFilter(1))

        btnComprobar.setOnClickListener {
            //guardo la letra introducida por el usuario transformandola a mayuscula
            val letra = edtIntroduceLetra.text.toString().uppercase()
            if (letra.isNotEmpty()) {
                //busco si existe la letra dentro de la palabra secreta.
                //si existe actualiza la palabra mostrada y si no las letras incorrectas
                if (palabraSecreta.contains(letra)) {
                    actualizarPalabraMostrada(letra)
                    comprobarLetraIntento()
                } else {
                    actualizarLetrasIncorrectas(letra)
                    comprobarLetraIntento()
                }
            }
            //limpio la entrada por pantalla cada vez que se pulsa el boton
            edtIntroduceLetra.text.clear()
        }
    }

    private fun actualizarPalabraMostrada(letra: String) {
        // guardo la letra introducida por el usuario separada en caracteres
        val palabraMostrada = edtPalabra.text.toString().toCharArray()
        var palabraAdivinada = true

        // itero sobre la palabra secrea
        for (i in 0 until palabraSecreta.length) {
            // guardo la posicion de la letras letras de la palabra secreta
            val letraSecreta = palabraSecreta[i]

            // comparo la letra secreta con la letra introducia por el usuario y su posicion
            if (letraSecreta == letra[0]) {
                // si coincide actualizo la letra en su posicion en la palabra que se muestra en pantalla
                palabraMostrada[i] = letraSecreta
            }
            // cuando se adivine una letra le quito el *
            if (palabraMostrada[i] == '*') {
                palabraAdivinada = false
            }
        }
        // actualizo las letras de la palabra mostrada en pantalla
        edtPalabra.setText(palabraMostrada.toString())
    }

    private fun actualizarLetrasIncorrectas(letra: String) {
        // guardo las letras incorrectas
        val letrasIncorrectas = edtLetrasNoEstan.text.toString()
        // creo una lista de strings para guardar las letras incorrectas
        val nuevasLetrasIncorrectas = mutableListOf<String>()

        // comparo la letra incorrecta con la letra introducida por el usuario
        if (!letrasIncorrectas.contains(letra)) {
            // si no esta la añado a la lista de letras incorrectas y tambien añado las letras que ya se han usado
            nuevasLetrasIncorrectas.add(letrasIncorrectas)
            nuevasLetrasIncorrectas.add(letra)
        }
        // actualizo las letras incorrectas mostradas en pantalla
        edtLetrasNoEstan.setText(nuevasLetrasIncorrectas.toString())
    }

    private fun comprobarLetraIntento() {
        val palabraMostrada = edtPalabra.text.toString()
        if (!palabraMostrada.contains('*')) {
            // saca el mensaje de palabra correcta si todas las letras han sido adivinadas
            txtGameOver.text = "CORRECTO. La palabra es: $palabraSecreta"
            txtGameOver.visibility = View.VISIBLE
            btnComprobar.isEnabled = false
            //si la palabra no contiene la letra que se ha introducido
        } else if (!palabraSecreta.contains(edtIntroduceLetra.text.toString().uppercase())) {
            // resta un intento cada vez que se falla una letra. Si llega a 0 intentos sale GAME OVER
            numIntentos--
            edtIntentos.setText(numIntentos.toString())

            if (numIntentos == 0) {
                // saco el mensaje de GAME OVER si se agotan los intentos
                txtGameOver.text = "GAME OVER. La palabra era: $palabraSecreta"
                txtGameOver.visibility = View.VISIBLE
                btnComprobar.isEnabled = false
            }
        }
    }
}