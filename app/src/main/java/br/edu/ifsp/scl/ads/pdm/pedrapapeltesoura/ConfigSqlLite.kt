package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.sql.SQLException

class ConfigSqlLite (context: Context): ConfigDAO{
    companion object{
        private val BD_CONFIG = "config"
        private val TABELA_CONFIG = "config"
        private val COLUNA_ID = "id"
        private val COLUNA_NUM_JOGADORES = "jogadores"

        val CRIATABELA_TABELASTMT= "CREATE TABLE IF NOT EXISTS ${TABELA_CONFIG} (" +
        "${COLUNA_ID} INTEGER NOT NULL PRIMARY KEY," +
        "${COLUNA_NUM_JOGADORES} INTEGER NOT NULL);"
    }
    private val configBD: SQLiteDatabase
    init {
        configBD = context.openOrCreateDatabase(BD_CONFIG, MODE_PRIVATE, null)
        try {
            configBD.execSQL(CRIATABELA_TABELASTMT)
        } catch (se: SQLException){
            Log.e("Config", se.toString())
        }
    }

    override fun insert(config: Config): Long {
        val configCv = ContentValues()
        configCv.put(COLUNA_NUM_JOGADORES, config.numeroJogadores)
        return configBD.insert(TABELA_CONFIG, null, configCv)
    }

    override fun update(config: Config): Int {
        val configCv = ContentValues()
        configCv.put(COLUNA_NUM_JOGADORES, config.numeroJogadores)
        return configBD.update(TABELA_CONFIG, configCv, "${COLUNA_ID} = 1", null)

    }

    override fun load(): Config {
        val cursor = configBD.rawQuery("SELECT jogadores FROM config", null)
        return if (cursor.moveToFirst()){
            with(cursor){
                Config(getInt(getColumnIndexOrThrow(COLUNA_NUM_JOGADORES)))
            }
        } else {
            Config()
        }
    }

}