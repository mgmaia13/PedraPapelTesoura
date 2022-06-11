package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityConfigBinding
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding

class ConfigActivity: AppCompatActivity() {
    private lateinit var configBinding: ActivityConfigBinding

    object Constantes {
        val CONFIGURACOES_ARQUIVO = "configuracoes"
        val NUMERO_JOGADORES_ATRIBUTO = "jogadores"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBinding= ActivityConfigBinding.inflate(layoutInflater)
        setContentView(configBinding.root)

        configBinding.configBt.setOnClickListener {

            val numeroJogadores: Int = (configBinding.numJogadores).text.toString().toInt()
            val config = Config(numeroJogadores)
            val retornoIntent = Intent()
            retornoIntent.putExtra(Intent.EXTRA_USER, config)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}