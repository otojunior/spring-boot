/**
 * 
 */
package sample.batch.item.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.batch.item.CustomItemProcessor;
import sample.batch.item.CustomItemReader;
import sample.batch.item.CustomItemWriter;
import sample.batch.item.CustomTasklet;

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
    public CustomItemReader reader() {
		return new CustomItemReader();
    }

    /**
     * 
     * @return
     */
    @Bean
    public CustomItemProcessor processor() {
        return new CustomItemProcessor();
    }

    /**
     * 
     * @return
     */
    @Bean
    public CustomItemWriter writer() {
    	return new CustomItemWriter();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public CustomTasklet tasklet() {
    	return new CustomTasklet();
    }
}
