package com.example.servingwebcontent.jpa.model.abac;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity

@Getter
@Setter
public class Policy {

  @Id
  @GeneratedValue
  protected Long id;

  protected String name;

  @OneToMany(mappedBy = "policyId")
  protected List<Rule> rules;
}
