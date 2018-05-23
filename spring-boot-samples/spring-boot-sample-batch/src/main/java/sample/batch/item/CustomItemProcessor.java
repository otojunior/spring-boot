/**
 * 
 */
package sample.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import sample.batch.data.Item;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemProcessor implements ItemProcessor<Item, Item> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemProcessor.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item process(Item input) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(
				"CustomItemProcessor.process() chamado - "
				+ "input:" + input.getNome() 
				+ " | output:" + input.getNome().toUpperCase());
			
			/*
			 * Atraso proposital, simulando um processamento mais pesado
			 * da funcionalidade.
			 */
			Thread.sleep(1000);
		}
		
		Item output = input;
		output.setNome(input.getNome().toUpperCase());
		output.setValido(Boolean.TRUE);
		return output;
	}
}
