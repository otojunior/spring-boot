/**
 * 
 */
package sample.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomJobListener implements JobExecutionListener {
	private static Logger LOG = LoggerFactory.getLogger(CustomJobListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info(">>>> AFTER JOB CALLBACK");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOG.info(">>>> BEFORE JOB CALLBACK");
	}
}
