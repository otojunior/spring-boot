/**
 * 
 */
package sample.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomStepListener implements StepExecutionListener {
	private static Logger LOG = LoggerFactory.getLogger(CustomStepListener.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(stepExecution.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(stepExecution.toString());
		}
		return stepExecution.getExitStatus();
	}
}
