package my.demenkov.osint.provider

import com.github.dockerjava.api.command.CreateContainerResponse
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports.Binding
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import java.time.Duration

class AmassOsintDataProvider: OsintDataProvider {
    override fun getData(host: String) = OsintData(host, mutableListOf())

    fun runDockerWithDockerJava() {
        val config: DockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost("tcp://localhost:2375")
            .withDockerTlsVerify(false)
            //.withDockerCertPath("~/user/.docker")
//            .withRegistryUsername(registryUser)
//            .withRegistryPassword(registryPass)
//            .withRegistryEmail(registryMail)
//            .withRegistryUrl(registryUrl)
            .build()


        val httpClient: DockerHttpClient = ApacheDockerHttpClient.Builder()
            .dockerHost(config.dockerHost)
            .sslConfig(config.sslConfig)
            .maxConnections(100)
            .connectionTimeout(Duration.ofSeconds(30))
            .responseTimeout(Duration.ofSeconds(45))
            .build()

        val dockerClient = DockerClientImpl.getInstance(config, httpClient)

        try {
            val exposedPort = ExposedPort(8080)
            val binding: Binding = Binding.bindPortSpec("8080")
            val portBinding = PortBinding(binding, exposedPort)
            val hostConfig = HostConfig
                .newHostConfig()
                .withPortBindings(portBinding)

            val container: CreateContainerResponse = dockerClient
                .createContainerCmd("caffix/amass")
                .withHostConfig(hostConfig)
                .exec()

            dockerClient.startContainerCmd(container.id)
                .exec()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            dockerClient.close()
        }
    }
}