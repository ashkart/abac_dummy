package com.example.servingwebcontent.jpa.model.abac.attribute.type;

import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@DiscriminatorValue(AttrResource.DISCRIMINATOR_TYPE)
public class AttrResource extends Attribute<EntityType> {

  public static final String DISCRIMINATOR_TYPE = "RESOURCE";

  @Column
  @Enumerated(EnumType.STRING)
  protected EntityType name;
}
