package com.example.servingwebcontent.jpa.model.abac.engine.pdp;

import com.example.servingwebcontent.jpa.model.abac.Policy;
import com.example.servingwebcontent.jpa.model.abac.engine.AccessContext;
import org.springframework.stereotype.Component;

@Component
public class PolicyDecisionPoint {
  public boolean evaluatePolicy(AccessContext context, Policy policy) {
    return true;
  }
}
