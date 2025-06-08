package valdez.wilber.calculadoraritmetica_valdezwilber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)
        val btnBorrar: Button = findViewById(R.id.btnC)
        val btnIgual: Button = findViewById(R.id.btnIgual)

        btnIgual.setOnClickListener{
            var respuesta: Double = 0.0


            //convierte el texto del textview2 a numero
            val numero2: Double = try {
                tv_num2.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Numero inválidi", Toast.LENGTH_SHORT).show()
                tv_num2.text = "Error"
                tv_num1.text = ""
                numero1 = 0.0
                operador = 0
                return@setOnClickListener
            }



            when(operador) {
                1 -> respuesta = numero1 + numero2
                2 -> respuesta = numero1 - numero2
                3 -> respuesta = numero1 * numero2
                4 -> {
                    if (numero2 == 0.0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                        tv_num2.text = "Error"
                        tv_num1.text = ""
                        numero1 = 0.0
                        operador = 0
                        return@setOnClickListener
                    }
                    respuesta = numero1 / numero2
                }

            }

            tv_num2.setText(respuesta.toString())
            tv_num1.setText("")

        }
        btnBorrar.setOnClickListener {
            tv_num1.setText("")
            tv_num2.setText("")
            numero1 = 0.0
            operador = 0
            estaOperando = false
        }



    }

    var numero1: Double = 0.0
    var operador: Int = 0
    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView
    var estaOperando: Boolean = false


    fun presionarDigito(view: View){
        var num2: String = tv_num2.text.toString()

        tv_num1 = findViewById(R.id.tv_num1)
        when(view.id){
            R.id.btn0 -> tv_num2.setText(num2 + "0")
            R.id.btn1 -> tv_num2.setText(num2 + "1")
            R.id.btn2 -> tv_num2.setText(num2 + "2")
            R.id.btn3 -> tv_num2.setText(num2 + "3")
            R.id.btn4 -> tv_num2.setText(num2 + "4")
            R.id.btn5 -> tv_num2.setText(num2 + "5")
            R.id.btn6 -> tv_num2.setText(num2 + "6")
            R.id.btn7 -> tv_num2.setText(num2 + "7")
            R.id.btn8 -> tv_num2.setText(num2 + "8")
            R.id.btn9 -> tv_num2.setText(num2 + "9")
            R.id.btnPunto ->{
                // solo añade el punto si aun no existe uno en el numero actual
                if (!num2.contains(".")) {
                    tv_num2.text = num2 + "."
                }
            }

        }

    }

    fun clicOperacion(view: View) {

        // si esta vacio o hay error en la pantalla no hara nada cuando selecciones algun operador
        if (tv_num2.text.isEmpty() || tv_num2.text.toString() == "Error") {
            Toast.makeText(this, "Ingrese un numero primero", Toast.LENGTH_SHORT).show()
            return
        }
        estaOperando = true


        numero1 = tv_num2.text.toString().toDouble()
        var num2_text: String = tv_num2.text.toString()
        tv_num2.setText("")

        when(view.id){
            R.id.btnMas -> {
                tv_num1.setText(num2_text + "+")
                  operador = 1
            }
            R.id.btnMenos -> {
                tv_num1.setText(num2_text + "-")
                operador = 2
            }
            R.id.btnX -> {
                tv_num1.setText(num2_text + "X")
                operador = 3
            }
            R.id.btnDivision -> {
                tv_num1.setText(num2_text + "/")
                operador = 4
            }
        }

    }


}