package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

class ConfigController(mainActivity: MainActivity) {
    val configDAO: ConfigDAO = ConfigSqlLite(mainActivity)
    fun insert(config:Config) = configDAO.insert(config)
    fun update(config:Config) = configDAO.update(config)
    fun load(): Config = configDAO.load()
}