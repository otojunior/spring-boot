/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@SpringBootApplication
@EnableScheduling
public class SampleSimpleApplication {
	private static final Logger LOG = LoggerFactory.getLogger(SampleSimpleApplication.class);
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SampleSimpleApplication.class, args);
	}
	
	/**
	 * 
	 */
	@Scheduled(fixedDelay=1)
	public void log() {
		LOG.info("teste");
	}
}
