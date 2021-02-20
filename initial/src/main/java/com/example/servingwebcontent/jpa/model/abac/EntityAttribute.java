package com.example.servingwebcontent.jpa.model.abac;

import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class EntityAttribute {
  @Id
  protected Long id;

  @Column
  protected Long entityId;

  @Column
  @Enumerated(EnumType.STRING)
  protected EntityType entityType;

  @Column
  protected Long attributeId;
}
