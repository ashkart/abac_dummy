package com.example.servingwebcontent.jpa.model.abac.attribute.type;

public enum EntityType {
  CUSTOMER,
  COUNTRY,
  CATEGORY,
  USER,
  SUBJECT, // means user as authorized identity
  ENVIRONMENT,
  ATTRIBUTE
}
