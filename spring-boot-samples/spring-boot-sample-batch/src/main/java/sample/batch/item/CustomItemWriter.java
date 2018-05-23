/**
 * 
 */
package sample.batch.item;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import sample.batch.data.Item;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CustomItemWriter implements ItemWriter<Item> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemWriter.class);

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void write(List<? extends Item> items) throws Exception {
		if (LOG.isTraceEnabled()) {
			LOG.trace("CustomItemWriter.write() chamado");
		}
		
		List<Object[]> batchArgs = items.
			stream().
			map(item -> {
				Object[] obj = new Object[4];
				obj[0] = item.getVersao();
				obj[1] = item.getNome();
				obj[2] = item.getValido();
				obj[3] = item.getId();
				return obj;
			}).
			collect(Collectors.toList());
		
		template.batchUpdate("update item set versao=?, nome=?, valido=? where id=?", batchArgs);
	}
}
