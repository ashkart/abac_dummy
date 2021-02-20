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
@DiscriminatorValue(AttrAction.DISCRIMINATOR_TYPE)
public class AttrAction extends Attribute<ActionType> {

  public static final String DISCRIMINATOR_TYPE = "ACTION";

  @Column
  @Enumerated(EnumType.STRING)
  protected ActionType name;
}
