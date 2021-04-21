package br.com.examplo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job helloWord(){
        return this.jobBuilderFactory
            .get("ImprimeHelloWord")
            .start(imprimeHelloWord())
            .build();
    }

    
    public Step imprimeHelloWord(){
        return this.stepBuilderFactory.get("NomeStep")
            .tasklet(new Tasklet(){
                @Override
                public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
                    System.out.println("Hello world!");
                    return RepeatStatus.FINISHED;
                }
            }).build();
    }

}
