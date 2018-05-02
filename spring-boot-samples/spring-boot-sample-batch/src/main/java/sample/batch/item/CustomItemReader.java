/**
 * 
 */
package sample.batch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.support.ListItemReader;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemReader extends ListItemReader<String> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemReader.class);

	/**
	 * 
	 * @param list
	 */
	public CustomItemReader(List<String> list) {
		super(list);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String read() {
		String read = super.read();
		LOG.info("read() chamado - " + read);
		return read;
	}
}
