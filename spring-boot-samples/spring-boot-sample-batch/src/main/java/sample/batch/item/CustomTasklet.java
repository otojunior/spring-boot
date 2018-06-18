/**
 * 
 */
package sample.batch.item;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import sample.batch.data.Item;
import sample.batch.data.ItemRepository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomTasklet implements Tasklet {
	private static final Logger LOG = LoggerFactory.getLogger(CustomTasklet.class);

	@Autowired private CustomItemReader reader;
	@Autowired private ItemRepository repository;
	
	/**
	 * 
	 * @param valor
	 */
	public CustomTasklet() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		if (LOG.isTraceEnabled()) {
			LOG.trace("CustomTasklet.execute()");
		}
		
		List<String> keysList = repository.
			findByValido(Boolean.FALSE).
			stream().
			map(Item::getNome).
			collect(Collectors.toList());
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("keysIterator: {}", keysList.size());
		}
		
		reader.setKeysIterator(keysList.iterator());
		return null;
	}
}
