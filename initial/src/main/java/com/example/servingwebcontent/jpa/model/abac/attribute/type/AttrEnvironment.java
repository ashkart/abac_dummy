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
@DiscriminatorValue(AttrEnvironment.DISCRIMINATOR_TYPE)
public class AttrEnvironment extends Attribute<EnvironmentType> {

  public static final String DISCRIMINATOR_TYPE = "ENVIRONMENT";

  @Column
  @Enumerated(EnumType.STRING)
  protected EnvironmentType name;
}
