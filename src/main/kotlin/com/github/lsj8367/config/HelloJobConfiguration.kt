package com.github.lsj8367.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class HelloJobConfiguration {

    @Bean
    fun helloJob(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Job {
        return JobBuilder("helloJob", jobRepository)
            .start(helloStep1(jobRepository, transactionManager))
            .next(helloStep2(jobRepository, transactionManager))
            .build()
    }

    private fun helloStep1(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("helloStep2", jobRepository)
            .tasklet({ contribution, chunkContext ->
                println("=====================")
                println("hello spring batch!!!")
                println("=====================")
                RepeatStatus.FINISHED }, transactionManager)
            .build()
    }

    private fun helloStep2(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("helloStep2", jobRepository)
            .tasklet ({ contribution, chunkContext ->
                println("=====================")
                println("hello step 2!!!!!")
                println("=====================")
                RepeatStatus.FINISHED }, transactionManager)
            .build()
    }
}
