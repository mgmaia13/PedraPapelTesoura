package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

interface ConfigDAO {
    fun insert(config: Config): Long
    fun update(config: Config): Int
    fun load(): Config

}