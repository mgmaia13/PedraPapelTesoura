package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura
//
//MARCELO GONCALVES MAIA - SC3004996 / PedraPapelTesoura
//
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        fun jokenpo(pedraPapelTesoura: Int){
            val oponente = Random.nextInt(3)
            Log.d("Escolha: ",oponente.toString())
            if(oponente == 0){
                 activityMainBinding.jogadaOponente.text = "pedra"
            } else if(oponente == 1){
                activityMainBinding.jogadaOponente.text = "papel"
            } else {
                activityMainBinding.jogadaOponente.text = "tesoura"
            }
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
        activityMainBinding.pedra.setOnClickListener {
            jokenpo(0)
        }
        activityMainBinding.papel.setOnClickListener {
            jokenpo(1)
        }
        activityMainBinding.tesoura.setOnClickListener {
            jokenpo(2)
        }
    }
}