package ufpr.br.appmatematica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val botaoContagemActivity: Button = findViewById(R.id.botaoContagem)
        botaoContagemActivity.setOnClickListener {
            val intent = Intent(this, ContagemActivity::class.java)
            startActivity(intent)
        }

        val botaoAritmeticaActivity: Button = findViewById(R.id.botaoAritmetica)
        botaoAritmeticaActivity.setOnClickListener {
            val intent = Intent(this, AritmeticaActivity::class.java)
            startActivity(intent)
        }

        val botaoMaiorNumeroActivity: Button = findViewById(R.id.botaoMaiorNumero)
        botaoMaiorNumeroActivity.setOnClickListener {
            val intent = Intent(this, MaiorNumeroActivity::class.java)
            startActivity(intent)
        }
    }
}