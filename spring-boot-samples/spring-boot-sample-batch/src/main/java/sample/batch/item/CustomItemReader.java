/**
 * 
 */
package sample.batch.item;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.beans.factory.annotation.Autowired;

import sample.batch.data.Item;
import sample.batch.data.ItemRepository;
import sample.batch.data.SimuladorAmazonS3Repository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemReader implements ItemStreamReader<Item> {
	private static final Logger LOG = LoggerFactory.getLogger(CustomItemReader.class);

	@Autowired 
	private ItemRepository repository;
	
	@Autowired
	private SimuladorAmazonS3Repository simuladorAmazonS3;
	
	private Iterator<String> keysIterator;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item read() {
		if (LOG.isTraceEnabled()) {
			LOG.trace("CustomItemReader.read() chamado"); 
		}
		
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

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		LOG.info("OPEN chamado");
		
		List<String> keysList = repository.
			findByValido(Boolean.FALSE).
			stream().
			map(Item::getNome).
			collect(Collectors.toList());
		this.keysIterator = keysList.iterator();
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		LOG.info("UPDATE chamado");
	}

	@Override
	public void close() throws ItemStreamException {
		LOG.info("CLOSE chamado");
	}
}
