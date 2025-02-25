package my.demenkov.osint.data

import jakarta.persistence.*
import my.demenkov.osint.provider.OsintData
import java.io.Serializable

@Entity
@Table(name = "found_data")
data class FoundData(
    @Id
    @Column(name = "host")
    val host: String = "empty host",
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "host")
    val ips: List<Ip> = mutableListOf()
)

fun OsintData.toFoundData(): FoundData {
    return FoundData(
        host,
        ips
            .map { ip -> Ip(host, ip)}
            .toList()
    )
}

infix fun FoundData.from(osintData: OsintData): FoundData {
    return FoundData(
        osintData.host,
        osintData.ips
            .map { ip -> Ip(osintData.host, ip)}
            .toList()
    )
}

@Entity
@Table(name = "ip")
@IdClass(IpHostKey::class)
data class Ip(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    val id: Long? = null,
    @Id
    val host: String = "empty host",
    @Id
    val ip: String = "empty ip"
)

data class IpHostKey(
    val ip: String? = "empty ip",
    val host: String? = "empty host",
): Serializable
