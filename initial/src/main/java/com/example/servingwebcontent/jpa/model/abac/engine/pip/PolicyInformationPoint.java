package com.example.servingwebcontent.jpa.model.abac.engine.pip;

import com.example.servingwebcontent.jpa.model.IAttributable;
import com.example.servingwebcontent.jpa.model.User;
import com.example.servingwebcontent.jpa.model.abac.Policy;
import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.ActionType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EnvironmentType;
import com.example.servingwebcontent.jpa.model.abac.engine.AccessContext;
import com.example.servingwebcontent.jpa.repository.CustomerRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PolicyInformationPoint {
  private final CustomerRepository customerRepository;

  public AccessContext getACData(
          User user,
          Policy policy,
          ActionType actionType,
          EntityType resourceType,
          Long id
  ) throws ExecutionControl.NotImplementedException {

    IAttributable<EntityType> entity;

    switch (resourceType) {
      case CUSTOMER:
        entity = customerRepository.findById(id).orElseThrow();
        break;

      default:
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }


    return new AccessContext(
            actionType,
            user.getAttributes(),
            entity.getAttributes(),
            calculateAttributesFromEnvironment(user, policy, entity)
    );
  }

  public ArrayList<Attribute<EnvironmentType>> calculateAttributesFromEnvironment(
          User user,
          Policy policy,
          IAttributable<?> entity
  ) {
    return new ArrayList<>();
  }
}
