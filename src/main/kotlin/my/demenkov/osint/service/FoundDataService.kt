package my.demenkov.osint.service

import my.demenkov.osint.data.FoundData
import my.demenkov.osint.data.FoundDataRepository
import my.demenkov.osint.data.toFoundData
import my.demenkov.osint.provider.OsintDataProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoundDataService(
    @Autowired private val osintDataProvider: OsintDataProvider,
    @Autowired private val foundDataRepository: FoundDataRepository
) {

    fun save(foundData: FoundData): FoundData = foundDataRepository.save(foundData)

    fun getInfo(host: String): FoundData {
        val foundData = foundDataRepository.findByHost(host)
        foundData
            ?.let { return it }
            ?: return with(osintDataProvider.getData(host).toFoundData()) {
                foundDataRepository.save(this)
            }
    }
}