package com.example.leccion1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leccion1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
    1. Calculadora de IMC (Índice de Masa Corporal):

Crear una aplicación que permita al usuario ingresar su peso y altura, y luego calcule e imprima su IMC.
Clasificar el IMC en categorías como "Bajo peso", "Normal", "Sobrepeso" u "Obesidad"
Subir a github
     */
    private lateinit var vista: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vista.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vista.calcular.setOnClickListener {
            calcularIMC();
        }

    }
    private fun calcularIMC(){

        try {
            val alto = vista.altura.text.toString().toDoubleOrNull()
            val peso = vista.peso.text.toString().toFloatOrNull()

            if (alto != null && peso != null) {
                val imc = peso / (alto * alto)

                var resultado=""
                    if (imc >= 16.0 && imc < 18.4){
                         resultado="BAJO PESO"}
                    else if (imc >= 18.5 && imc < 24.9){
                       resultado= "SALUDABLE"
                        }
                    else if (imc >= 25.0 && imc < 29.9){
                       resultado= "SOBREPESO"
                    }else{
                        resultado="Rango no definido para el valor $imc "
                    }

                vista.resultado.setText("Tu IMC es $resultado")
                }


        } catch (e:Exception){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }
    }

}