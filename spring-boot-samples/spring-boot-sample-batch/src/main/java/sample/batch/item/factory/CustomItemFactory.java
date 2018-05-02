/**
 * 
 */
package sample.batch.item.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import sample.batch.item.CustomItemProcessor;
import sample.batch.item.CustomItemReader;
import sample.batch.item.CustomItemWriter;
import sample.batch.item.CustomTasklet;
import sample.batch.mock.MockDados;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class CustomItemFactory {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemFactory.class);
	
    /**
     * 
     * @return
     */
    @Bean
    public ItemReader<String> reader() {
		return new CustomItemReader(MockDados.dados);
    }

    /**
     * 
     * @return
     */
    @Bean
    public ItemProcessor<String, String> processor() {
        return new CustomItemProcessor();
    }

    /**
     * 
     * @return
     */
    @Bean
    public ItemWriter<String> writer() {
    	return new CustomItemWriter();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    @Primary
    public Tasklet tasklet1() {
    	LOG.info(">>>>>>>>> tasklet1");
    	return new CustomTasklet("tasklet1");
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public Tasklet tasklet2() {
    	LOG.info(">>>>>>>>> tasklet2");
    	return new CustomTasklet("tasklet2");
    }
}
