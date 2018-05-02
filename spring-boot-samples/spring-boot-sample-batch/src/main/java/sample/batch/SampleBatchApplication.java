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

package sample.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@SpringBootApplication
public class SampleBatchApplication {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * System.exit is common for Batch applications 
		 * since the exit code can be used to 
		 * drive a workflow.
		 */
		ConfigurableApplicationContext context = SpringApplication.run(SampleBatchApplication.class, args);
		int exitcode = SpringApplication.exit(context);
		System.exit(exitcode);
	}
}
