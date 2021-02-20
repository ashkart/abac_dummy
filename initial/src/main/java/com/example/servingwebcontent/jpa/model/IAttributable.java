package com.example.servingwebcontent.jpa.model;

import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;

import java.util.List;

public interface IAttributable<T> {
  public List<Attribute<T>> getAttributes();
}
