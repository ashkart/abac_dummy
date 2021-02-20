package com.example.servingwebcontent.jpa.model.abac.annotaion;

import com.example.servingwebcontent.jpa.model.abac.attribute.type.ActionType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessControl {
  ActionType action();
  EntityType resourceType();
}
