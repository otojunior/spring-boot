/**
 * 
 */
package sample.batch.item.factory;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import sample.batch.data.Item;
import sample.batch.item.CustomCompositeItemReader;
import sample.batch.item.CustomItemProcessor;
import sample.batch.item.CustomItemReader;
import sample.batch.item.CustomItemWriter;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class CustomItemFactory {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CustomItemFactory.class);
	
	@Autowired
	private DataSource dataSource;
	
    /**
     * 
     * @return
     */
    @Bean
    public ItemReader<Item> reader() {
		return new CustomItemReader();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    @Primary
    public ItemReader<Item> compositereader() {
    	return new CustomCompositeItemReader();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public ItemStreamReader<String> jdbcreader() {
    	JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<>();
		reader.setSql("select nome from item where valido = false");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper((rs, rnum) -> rs.getString("nome"));
		return reader;
    }

    /**
     * 
     * @return
     */
    @Bean
    public ItemProcessor<Item, Item> processor() {
        return new CustomItemProcessor();
    }

    /**
     * 
     * @return
     */
    @Bean
    public ItemWriter<Item> writer() {
    	return new CustomItemWriter();
    }
}
