/**
 * 
 */
package sample.batch.item;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;

import sample.batch.data.Item;
import sample.batch.data.SimuladorAmazonS3Repository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomCompositeItemReader implements ItemStreamReader<Item> {
	@Autowired
	private SimuladorAmazonS3Repository simuladorAmazonS3;
	
	@Autowired
	private ItemStreamReader<String> keysreader;
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public Item read() throws Exception {
		Item item = null;
		String key = keysreader.read();
		if (key != null) {
			item = amazonS3get(key);
		}
		return item;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		keysreader.open(executionContext);
		
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		keysreader.update(executionContext);
		
	}

	@Override
	public void close() throws ItemStreamException {
		keysreader.close();
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
