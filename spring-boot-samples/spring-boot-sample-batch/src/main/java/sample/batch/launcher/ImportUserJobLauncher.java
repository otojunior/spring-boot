/**
 * 
 */
package sample.batch.launcher;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
@ConditionalOnProperty(prefix="sample.batch.schedule", name="importUserJob", havingValue="true", matchIfMissing=false)
public class ImportUserJobLauncher {
	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay=10_000) // 10 segundos.
	public void runJob() throws Exception {
		this.jobOperator.startNextInstance("importUserJob");
	}
}
