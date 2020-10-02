/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rsocket.routing.client.spring;

import io.rsocket.routing.common.spring.RoutingFrameDecoder;
import io.rsocket.routing.common.spring.RoutingFrameEncoder;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration;
import org.springframework.boot.rsocket.messaging.RSocketStrategiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.rsocket.routing.client.spring.RoutingClientProperties.CONFIG_PREFIX;

@Configuration
@ConditionalOnProperty(name = CONFIG_PREFIX + ".enabled", matchIfMissing = true)
@AutoConfigureBefore(RSocketStrategiesAutoConfiguration.class)
public class RoutingClientRSocketStrategiesAutoConfiguration {
	@Bean
	public RSocketStrategiesCustomizer clientRSocketStrategiesCustomizer() {
		return strategies -> strategies
				.decoder(new RoutingFrameDecoder())
				.encoder(new RoutingFrameEncoder());
	}

}
