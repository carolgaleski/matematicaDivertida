package ufpr.br.appmatematica

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.random.nextInt

class ContagemActivity : AppCompatActivity() {

    private lateinit var imgAnimal: ImageView
    private lateinit var botaoOp1: Button
    private lateinit var botaoOp2: Button
    private lateinit var botaoOp3: Button

    private val imagens = listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10)

    private val quantidades = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    private var perguntas = mutableListOf<Int>()
    private var acertos = 0
    private var perguntasRespondidas = 0
    private val totalPerguntas = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contagem)

       imgAnimal = findViewById(R.id.imgAnimal)
        botaoOp1 = findViewById(R.id.botaoOp1)
        botaoOp2 = findViewById(R.id.botaoOp2)
        botaoOp3 = findViewById(R.id.botaoOp3)

        novaPergunta()
    }

    private fun novaPergunta() {
        if (perguntasRespondidas == totalPerguntas){
            mostrarResultado()
                return
        }

        //sorteio das imagens
        var indice: Int
        do {
            indice = Random.nextInt(imagens.size)
        } while (perguntas.contains(indice))

        perguntas.add(indice)

        val quantidadeCorreta = quantidades[indice]
        imgAnimal.setImageResource(imagens[indice])

        //opções erradas
        val opc =mutableSetOf(quantidadeCorreta)
        while (opc.size < 3 ) {
            val respostaErrada = Random.nextInt(1, 10)
            if (respostaErrada != quantidadeCorreta) opc.add(respostaErrada)
        }

        val listaRespostas = opc.shuffled()

        //aloca para cada botão as possíveis respostas
        botaoOp1.text = listaRespostas[0].toString()
        botaoOp2.text = listaRespostas[1].toString()
        botaoOp3.text = listaRespostas[2].toString()

        val botoes = listOf(botaoOp1, botaoOp2, botaoOp3)
        botoes.forEach { botao ->
            botao.setOnClickListener {
                val resposta = botao.text.toString().toInt()
                verificarResposta(resposta, quantidadeCorreta)
            }
        }

    }

    private fun verificarResposta(resposta: Int, correta: Int) {
        perguntasRespondidas++

        val msg =  if (resposta == correta) {
            acertos++
            "Resposta correta!!"
        }   else {
            "Resposta errada!! A resposta certa é: $correta"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Próxima") {
                dialog, _ -> dialog.dismiss()
                novaPergunta()
            }
            .show()
    }
    private fun mostrarResultado() {
        val pontuacao = (acertos * 100) / totalPerguntas

        AlertDialog.Builder(this)
            .setTitle("Fim do jogo")
            .setMessage("Você acertou $acertos de $totalPerguntas. Sua nota foi de $pontuacao !!")
            .setCancelable(false)
            .setPositiveButton("ok") {
                    dialog, _ -> dialog.dismiss()
                finish()
            }
            .show()
    }
}
