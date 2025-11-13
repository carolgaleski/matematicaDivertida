package ufpr.br.appmatematica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MaiorNumeroActivity : AppCompatActivity() {

    private lateinit var num1: TextView
    private lateinit var num2: TextView
    private lateinit var num3: TextView
    private lateinit var resposta: EditText
    private lateinit var botaoResposta: Button

    private var perguntasRespondidas = 0
    private var acertos = 0
    private val totalPerguntas =5
    private var respostaCorreta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maior_numero)

        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        num3 = findViewById(R.id.num3)
        resposta = findViewById(R.id.resposta)
        botaoResposta = findViewById(R.id.botaoResposta)

        novaPergunta()

        botaoResposta.setOnClickListener {
            verificarResposta()
        }

    }

    private fun novaPergunta() {
        if (perguntasRespondidas == totalPerguntas) {
            mostrarResultado()
            return
        }

        val digitos = List(3) { Random.nextInt(0,9) }

        num1.text = digitos[0].toString()
        num2.text = digitos[1].toString()
        num3.text = digitos[2].toString()

        //ordenar em ordem e formar o número
        respostaCorreta  = digitos.sortedDescending().joinToString("").toInt()

        resposta.text.clear()
    }

    private fun verificarResposta() {
        val texto = resposta.text.toString()

        if (texto.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Digite uma resposta.")
                .setPositiveButton("ok", null)
                .show()
            return
        }

        perguntasRespondidas++
        val respostaUsuario = texto.toInt()

        val msg = if (respostaUsuario== respostaCorreta)  {
            acertos++
            "Acertou!!"
        }
        else {
            "Errou!! A resposta correta é $respostaCorreta"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Proxima") { dialog, _ ->
                dialog.dismiss()
                novaPergunta()
            }
            .show()
    }
    private fun mostrarResultado() {
        val pontuacao = (acertos * 100) / totalPerguntas

        AlertDialog.Builder(this)
            .setTitle("Fim do joogo")
            .setMessage("Você acertou $acertos de $totalPerguntas. Sua nota foi de $pontuacao !!")
            .setCancelable(false)
            .setPositiveButton("ok") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }
}