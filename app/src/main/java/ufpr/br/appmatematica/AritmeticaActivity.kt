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

class AritmeticaActivity : AppCompatActivity() {

    private lateinit var equacao: TextView
    private lateinit var resposta: EditText
    private lateinit var botaoResposta: Button

    private var perguntasRespondidas =0
    private var acertos =0
    private var totalPerguntas = 5
    private var resultadoCorreto = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_aritmetica)

        equacao= findViewById(R.id.equacao)
        resposta = findViewById(R.id.resposta)
        botaoResposta= findViewById(R.id.botaoResposta )

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

        val num1 = Random.nextInt(0,9)
        val num2 = Random.nextInt(0,9)
        val operador = if (Random.nextBoolean()) '+'
                            else '-'

        resultadoCorreto = when (operador) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            else -> 0
        }

        equacao.text = "$num1 $operador $num2 = ?"
        resposta.text.clear()
    }

    private fun verificarResposta() {
        val textoResposta = resposta.text.toString()

        if (textoResposta.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Digite um número antes de continuar.")
                .setPositiveButton("ok", null)
                .show()
            return
        }

        perguntasRespondidas++

        val respostaInt = textoResposta.toInt()

        val msg = if (respostaInt == resultadoCorreto) {
                            acertos++
                            "Acertou!"
                        }   else {
                            "Errou! A resposta correta é $resultadoCorreto"
                        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Próxima") { dialog, _ ->
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


