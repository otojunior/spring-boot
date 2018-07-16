/**
 * 
 */
package sample.batch.item;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import sample.batch.data.Item;
import sample.batch.data.SimuladorAmazonS3Repository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemReader implements ItemReader<Item> {
	private static final Logger LOG = LoggerFactory.getLogger(CustomItemReader.class);

	@Autowired
	private GlobalContext context;
	
	@Autowired
	private SimuladorAmazonS3Repository simuladorAmazonS3;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item read() {
		if (LOG.isTraceEnabled()) {
			LOG.trace("CustomItemReader.read() chamado"); 
		}
		
		Iterator<String> keysIterator = context.get("importUserJob");
		if (keysIterator.hasNext()) {
			String key = keysIterator.next();
			Item item = amazonS3get(key);
			return item;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	private Item amazonS3get(String key) {
		List<Item> itens = simuladorAmazonS3.findByNome(key);
		Item item = null;
		if (itens != null && !itens.isEmpty()) {
			item = itens.get(0);
		}
		return item;
	}
}
