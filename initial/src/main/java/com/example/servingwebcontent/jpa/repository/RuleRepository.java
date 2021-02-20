package com.example.servingwebcontent.jpa.repository;

import com.example.servingwebcontent.jpa.model.abac.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Long> {

  Rule findByPolicyId(Long policyId);

}
