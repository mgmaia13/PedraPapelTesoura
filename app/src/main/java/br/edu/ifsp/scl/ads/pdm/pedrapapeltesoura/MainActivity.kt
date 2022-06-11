package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura
//
//MARCELO GONCALVES MAIA - SC3004996 / PedraPapelTesoura
//
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var configActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var activityMainBinding: ActivityMainBinding
    var configSalve: Config = Config(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        configActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == RESULT_OK) {
                if (resultado.data != null) {
                    val configSalve: Config? = resultado.data?.getParcelableExtra(Intent.EXTRA_USER)
                    if (configSalve?.numeroJogadores != 1) {
                        //Inserindo no banco
                    } else {
                        //atualizando no banco
                    }

                }
            }

            activityMainBinding.configBt.setOnClickListener {
                val configIntent = Intent(this, ConfigActivity::class.java)
                configActivityLauncher.launch(configIntent)
            }
        }
        activityMainBinding.pedra.setOnClickListener {
            jokenpo(0, configSalve?.numeroJogadores)
        }
        activityMainBinding.papel.setOnClickListener {
            jokenpo(1, configSalve?.numeroJogadores)
        }
        activityMainBinding.tesoura.setOnClickListener {
            jokenpo(2, configSalve?.numeroJogadores)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.settingsMi -> {
            configActivityLauncher.launch(Intent(this, ConfigActivity::class.java))
            true
        }
        else -> {
            false
        }
    }
    fun jokenpo(pedraPapelTesoura: Int, numeroJogador: Int?){
        val oponente = Random.nextInt(3)
        Log.d("Escolha: ",oponente.toString())
        if(oponente == 0){
            activityMainBinding.jogadaOponente.text = "pedra"
        } else if(oponente == 1){
            activityMainBinding.jogadaOponente.text = "papel"
        } else {
            activityMainBinding.jogadaOponente.text = "tesoura"
        }
        if(numeroJogador == 2){
            if(oponente == pedraPapelTesoura){
                activityMainBinding.tvOponente.text = "Oponente escolheu:"
                activityMainBinding.resultadoJogo.text = "Empate!"
                activityMainBinding.resultadoJogo.setTextColor(Color.YELLOW)
            } else if((oponente== 0 && pedraPapelTesoura == 1) || (oponente == 1 && pedraPapelTesoura == 2) || (oponente == 2 && pedraPapelTesoura == 0)){
                activityMainBinding.tvOponente.text = "Oponente escolheu:"
                activityMainBinding.resultadoJogo.text = "Voce Ganhou!"
                activityMainBinding.resultadoJogo.setTextColor(Color.GREEN)
            } else {
                activityMainBinding.tvOponente.text = "Oponente escolheu:"
                activityMainBinding.resultadoJogo.text =  "Voce Perdeu!"
                activityMainBinding.resultadoJogo.setTextColor(Color.RED)
            }
        }
    }
}
