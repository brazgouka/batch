package com.example.batch.controllers;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public LoadController(final JobLauncher jobLauncher, final Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public BatchStatus load() throws Exception {


        StopWatch stopWatch = new StopWatch("My Stop Watch");

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);

        stopWatch.start("finalizing");

        JobExecution jobExecution = jobLauncher.run(job, parameters);

        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        return jobExecution.getStatus();
    }
}