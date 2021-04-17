package com.example.calculadoradeimc2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoradeimc2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding   // config. view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   // config. view binding
        setContentView(binding.root)   // config. view binding

        val bt_calcular = binding.btCalcular
        val mensagem = binding.mensagem

        bt_calcular.setOnClickListener {

            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if (editPeso.isEmpty()){ /* condicional referente a possibilidade do
                edit peso estiver vazio. Mostrar uma mensagem para o usuário colocar esse peso. */
                         mensagem.setText("Informe o seu peso")
            }else if (editAltura.isEmpty()){ /* condicional referente a possibilidade do
                edit peso estiver vazio. Mostrar uma mensagem para o usuário colocar esse peso. */
                mensagem.setText("Informe a sua altura")
            }else{
                CalculoDeIMc()
            }

        }

    }

    private fun CalculoDeIMc(){
            val pesoId = binding.editPeso
            val alturaID = binding.editAltura

            val peso = Integer.parseInt(pesoId.text.toString()) // convertendo string em números Inteiros.
            val altura = java.lang.Float.parseFloat(alturaID.text.toString()) // conversão de altura para
            // Float = números fracionados. IMPORTANTE: Toda vez que usar numeros decimais essa tag será necessária.
            val resultado = binding.mensagem // Variável criada para dar o resultado obtido.
            val imc = peso / (altura * altura) // Variável IMC que recebe o peso dividido pela altura ao quadrado.

            val Mensagem = when{ //Variável mensagem recebe o controle de fluxo.
                imc <= 18.5 -> " Peso Baixo"
                imc <= 24.9 -> " Peso normal "
                imc <= 29.9 -> " Sobrepeso"
                imc <= 34.9 -> " Obesidade grau 1 "
                imc <= 39.9 -> " Obesidade grau 2 "
                else -> " Obesidade Mórbida grau 3"

            }
        imc.toString()    // convertendo o imc para string para exibir a mensagem.
        resultado.setText("IMC: $imc \n $Mensagem") // dando o resultado

    }
    /* Criação de método através do botão direito depois do fechamento Generate - override methods -
    * Create options menu.  */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { /* Criação de método através do */

        val inflate = menuInflater  // criação de variável para inflar o menu
        inflate.inflate(R.menu.menu_principal,menu)  // inflando o menu

        return true  // Retornando ------ foi mudado do original.
    }

    /* dando função ao ícone do menu - sobrescrita de método  - OnOptionsItemSelected*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){ //Criação de condicional para limpar o texto caso o botão seja acionado.
            R.id.reset ->{  //    IMPORTANTEEEEEEEEE

                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparMensagem = binding.mensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")

            }
        }

        return super.onOptionsItemSelected(item)
    }
}