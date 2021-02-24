package com.example.servingwebcontent.jpa.model.abac.engine.pep;

import com.example.servingwebcontent.jpa.model.abac.Policy;
import com.example.servingwebcontent.jpa.model.abac.annotaion.AccessControl;
import com.example.servingwebcontent.jpa.model.abac.annotaion.property.Id;
import com.example.servingwebcontent.jpa.model.abac.engine.AccessContext;
import com.example.servingwebcontent.jpa.model.abac.engine.pdp.PolicyDecisionPoint;
import com.example.servingwebcontent.jpa.model.abac.engine.pip.PolicyInformationPoint;
import com.example.servingwebcontent.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

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

    Annotation[][] allArgsAnnotations = ((MethodSignature) jp.getSignature()).getMethod().getParameterAnnotations();

    Integer parameterIndex = null;

    for (int i = 0; i < allArgsAnnotations.length; i++) {
      var allArgAnnotations = allArgsAnnotations[i];

      for (var annotation : allArgAnnotations) {
        if (Id.class.getName().equals(annotation.annotationType().getName())) {
          parameterIndex = i;
          break;
        }
      }
    }

    if (parameterIndex == null) {
      throw new Exception("access denied");
    }

    Long id = (Long) jp.getArgs()[parameterIndex];

    /* or else ```authUtils.getCurrentUser().getPolicy();``` */
    var user = userRepository.findById(1L).orElseThrow();
    Policy policy = user.getPolicy();

    AccessContext context = pip.getACData(user, policy, accessControl.action(), accessControl.resourceType(), id);

    if (!pdp.evaluatePolicy(context, policy)) {
      throw new Exception("access denied");
    }
  }
}
