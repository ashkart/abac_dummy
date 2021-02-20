package com.example.servingwebcontent.jpa.model.abac.engine;

import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.ActionType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EnvironmentType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.SubjectType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class AccessContext {
  public final ActionType action;
  public final List<Attribute<SubjectType>> subjectAttributes;
  public final List<Attribute<EntityType>> resourceAttributes;
  public final List<Attribute<EnvironmentType>> environmentAttributes;
}
