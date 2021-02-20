package com.example.servingwebcontent.jpa.model.abac.engine.pep;

import com.example.servingwebcontent.jpa.model.abac.Policy;
import com.example.servingwebcontent.jpa.model.abac.annotaion.AccessControl;
import com.example.servingwebcontent.jpa.model.abac.engine.AccessContext;
import com.example.servingwebcontent.jpa.model.abac.engine.pdp.PolicyDecisionPoint;
import com.example.servingwebcontent.jpa.model.abac.engine.pip.PolicyInformationPoint;
import com.example.servingwebcontent.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PolicyEnforcementPoint {

  private final UserRepository userRepository;
  private final PolicyInformationPoint pip;
  private final PolicyDecisionPoint pdp;

  @Pointcut("@annotation(com.example.servingwebcontent.jpa.model.abac.annotaion.AccessControl)")
  public void accessControl() {

  }

  @Before("@annotation(accessControl)")
  public void checkAccess(JoinPoint jp, AccessControl accessControl) throws Exception {
    System.out.println("checking access");

    System.out.println(accessControl.action());
    System.out.println(accessControl.resourceType());

    Long id = 4L; // todo: get id from annotated method parameter

    /* or else ```authUtils.getCurrentUser().getPolicy();``` */
    var user = userRepository.findById(1L).orElseThrow();
    Policy policy = user.getPolicy();

    AccessContext context = pip.getACData(user, policy, accessControl.action(), accessControl.resourceType(), id);

    if (!pdp.evaluatePolicy(context, policy)) {
      throw new Exception("access denied");
    }
  }
}
