/**
 * 
 */
package sample.batch.item.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.batch.data.Item;
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
