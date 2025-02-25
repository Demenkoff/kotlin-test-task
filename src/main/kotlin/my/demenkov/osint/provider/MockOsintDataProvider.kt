package my.demenkov.osint.provider

import org.springframework.stereotype.Service
import java.util.*

@Service
class MockOsintDataProvider: OsintDataProvider {

    override fun getData(host: String): OsintData {
        return OsintData(host, mutableListOf(UUID.randomUUID().toString()))
    }

}