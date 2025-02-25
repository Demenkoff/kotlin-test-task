package my.demenkov.osint.controller

import my.demenkov.osint.data.FoundData
import my.demenkov.osint.service.FoundDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class OsintDataController(@Autowired private val foundDataService: FoundDataService) {

    @ResponseBody
    @GetMapping("/get")
    fun getHostInfo(@RequestParam("host") host: String): FoundData =
        foundDataService.getInfo(host);

}