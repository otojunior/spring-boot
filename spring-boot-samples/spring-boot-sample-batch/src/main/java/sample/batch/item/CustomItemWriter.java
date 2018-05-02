/**
 * 
 */
package sample.batch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.support.ListItemWriter;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemWriter extends ListItemWriter<String> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemWriter.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		LOG.info("write() chamado - " + items.size());
		super.write(items);
	}
}
