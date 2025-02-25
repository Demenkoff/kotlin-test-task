package my.demenkov.osint.provider

interface OsintDataProvider {

    fun getData(host: String): OsintData
}

data class OsintData(
    val host: String,
    val ips: List<String>,
)