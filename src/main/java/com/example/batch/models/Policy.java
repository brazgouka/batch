package com.example.batch.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "policy")
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "policy_id")
    private String policyID;

    @Column(name = "state_code")
    private String stateCode;

    private String country;
}
