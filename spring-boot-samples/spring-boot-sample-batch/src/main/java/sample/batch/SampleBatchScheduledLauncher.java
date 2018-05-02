/**
 * 
 */
package sample.batch;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
@EnableScheduling
public class SampleBatchScheduledLauncher {
	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 5000l)
	public void runJob() throws Exception {
		this.jobOperator.startNextInstance("importUserJob");
	}
}
