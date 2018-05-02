package sample.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SampleBatchConfiguration {
	
	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
    
    /**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job importUserJob(
    			JobExecutionListener listener,
            	@Qualifier("step1") Step stepUm,
            	@Qualifier("step2") Step stepDois) {
        return jobBuilderFactory.get("fazAlgoJob").
        	listener(listener).
            start(stepUm).
            next(stepDois).
            build();
    }

    /**
     * 
     * @param writer
     * @return
     */
	@Bean
	public Step step1(
			StepExecutionListener listener,
			ItemReader<String> reader,
			ItemProcessor<String, String> processor,
			ItemWriter<String> writer) {
		return stepBuilderFactory.get("passo1").
			listener(listener).
			<String, String>chunk(3).
			reader(reader).
			processor(processor).
			writer(writer).
			build();
	}
	
	/**
     * 
     * @param writer
     * @return
     */
	@Bean
	public Step step2(@Qualifier("tasklet2") Tasklet tasklet) {
		return stepBuilderFactory.
			get("passo2").
			tasklet(tasklet).
			build();
	}
}