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
@DiscriminatorValue(AttrSubject.DISCRIMINATOR_TYPE)
public class AttrSubject extends Attribute<SubjectType> {

  public static final String DISCRIMINATOR_TYPE = "SUBJECT";

  @Column
  @Enumerated(EnumType.STRING)
  protected SubjectType name;
}

