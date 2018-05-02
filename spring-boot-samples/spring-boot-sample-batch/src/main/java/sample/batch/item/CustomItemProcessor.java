/**
 * 
 */
package sample.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemProcessor implements ItemProcessor<String, String> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemProcessor.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String process(String input) throws Exception {
		String output = input.toUpperCase();
		LOG.info("process() chamado - input:" + input + " | output:" + output);
		return output;
	}
}
