package dev.cmartin.gateway

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.gateway.server.mvc.config.GatewayMvcProperties

@SpringBootTest
class GatewayApplicationTests {

	@Autowired
	private lateinit var gatewayProperties: GatewayMvcProperties

	@Test
	fun contextLoads() {
		assertThat(gatewayProperties.routes.map { it.id }).containsExactly("ms-one", "ms-two")
	}

}
