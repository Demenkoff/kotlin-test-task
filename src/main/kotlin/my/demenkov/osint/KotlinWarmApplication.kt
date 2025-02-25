package my.demenkov.osint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWarmApplication

fun main(args: Array<String>) {
	runApplication<KotlinWarmApplication>(*args)
}
