package my.demenkov.osint.data

import org.springframework.data.jpa.repository.JpaRepository

interface FoundDataRepository: JpaRepository<FoundData, Long> {

    fun findByHost(host: String): FoundData?
}