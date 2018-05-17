package sample.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import sample.batch.data.Item;
import sample.batch.item.CustomItemProcessor;
import sample.batch.item.CustomItemReader;
import sample.batch.item.CustomItemWriter;
import sample.batch.item.CustomTasklet;

@Configuration
@EnableBatchProcessing
public class SampleBatchConfiguration extends DefaultBatchConfigurer {
	@Autowired private JobRegistry jobRegistry;
	@Autowired private JobLauncher jobLauncher;
	@Autowired private JobRepository jobRepository;
	@Autowired private JobExplorer jobExplorer;
	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private ApplicationContext applicationContext;
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @Bean
	public JobRegistryBeanPostProcessor jobRegistrar() throws Exception {
		JobRegistryBeanPostProcessor registrar = new JobRegistryBeanPostProcessor();
		registrar.setJobRegistry(this.jobRegistry);
		registrar.setBeanFactory(this.applicationContext.getAutowireCapableBeanFactory());
		registrar.afterPropertiesSet();
		return registrar;
	}

    /**
     * 
     * @return
     * @throws Exception
     */
	@Bean
	public JobOperator jobOperator() throws Exception {
		SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
		simpleJobOperator.setJobLauncher(this.jobLauncher);
		simpleJobOperator.setJobParametersConverter(new DefaultJobParametersConverter());
		simpleJobOperator.setJobRepository(this.jobRepository);
		simpleJobOperator.setJobExplorer(this.jobExplorer);
		simpleJobOperator.setJobRegistry(this.jobRegistry);
		simpleJobOperator.afterPropertiesSet();
		return simpleJobOperator;
	}
    
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
        return jobBuilderFactory.get("importUserJob").
        	listener(listener).
        	incrementer(new RunIdIncrementer()).
            start(stepUm).
            next(stepDois).
            build();
    }

    /**
     * 
     * @param customItemWriter
     * @return
     */
	@Bean
	public Step step2(
			StepExecutionListener listener,
			CustomItemReader customItemReader,
			CustomItemProcessor customItemProcessor,
			CustomItemWriter customItemWriter) {
		return stepBuilderFactory.get("passo2").
			listener(listener).
			<Item, Item>chunk(3).
			reader(customItemReader).
			processor(customItemProcessor).
			writer(customItemWriter).
			build();
	}
	
	/**
     * 
     * @param writer
     * @return
     */
	@Bean
	public Step step1(CustomTasklet customTasklet) {
		return stepBuilderFactory.
			get("passo1").
			tasklet(customTasklet).
			build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Bean
	public JobLauncher getJobLauncher() {
		SimpleJobLauncher jobLauncher = null;
		try {
			jobLauncher = new SimpleJobLauncher();
			jobLauncher.setJobRepository(this.jobRepository);
			jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
			jobLauncher.afterPropertiesSet();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jobLauncher;
	}
}