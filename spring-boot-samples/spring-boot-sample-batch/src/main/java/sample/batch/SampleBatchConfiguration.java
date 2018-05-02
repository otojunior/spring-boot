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
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class SampleBatchConfiguration extends DefaultBatchConfigurer {
	@Autowired public JobRegistry jobRegistry;
	@Autowired public JobLauncher jobLauncher;
	@Autowired public JobRepository jobRepository;
	@Autowired public JobExplorer jobExplorer;
	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private ApplicationContext applicationContext;
  
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobLauncher getJobLauncher() {
		SimpleJobLauncher jobLauncher = null;
		try {
			jobLauncher = new SimpleJobLauncher();
			jobLauncher.setJobRepository(jobRepository);
			jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
			jobLauncher.afterPropertiesSet();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jobLauncher;
	}
}