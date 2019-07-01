package com.example.batch.batch;

import com.example.batch.models.Policy;
import com.example.batch.repositories.PolicyRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseItemWriter implements ItemWriter<Policy> {

    private final PolicyRepository policyRepository;

    @Autowired
    public DatabaseItemWriter(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void write(final List<? extends Policy> users) {
        policyRepository.saveAll(users);
    }
}