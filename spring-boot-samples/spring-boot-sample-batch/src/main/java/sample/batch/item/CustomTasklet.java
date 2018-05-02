/**
 * 
 */
package sample.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomTasklet implements Tasklet {
	private static Logger LOG = LoggerFactory.getLogger(CustomTasklet.class);

	private String tipo;
	
	/**
	 * 
	 * @param valor
	 */
	public CustomTasklet(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOG.info("Tasklet chamado :: tipo: " + this.tipo);
		return null;
	}
}
