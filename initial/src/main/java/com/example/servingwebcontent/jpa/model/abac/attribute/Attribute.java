package com.example.servingwebcontent.jpa.model.abac.attribute;

import com.example.servingwebcontent.jpa.model.abac.attribute.type.AttrAction;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.AttrEnvironment;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.AttrResource;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name = Attribute.TABLE)

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case " +
        "when type = '" + AttrAction.DISCRIMINATOR_TYPE + "' then '" + AttrAction.DISCRIMINATOR_TYPE + "'" +
        "when type = '" + AttrResource.DISCRIMINATOR_TYPE + "' then '" + AttrResource.DISCRIMINATOR_TYPE + "'" +
        "when type = '" + AttrEnvironment.DISCRIMINATOR_TYPE + "' then '" + AttrEnvironment.DISCRIMINATOR_TYPE + "'" +
        " end"
)

@Getter
@Setter

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public abstract class Attribute<T> {
  public static final String TABLE = "attribute";

  @Id
  @GeneratedValue
  protected Long id;

  @Column
  @Enumerated(EnumType.STRING)
  protected AttributeType type;

  @Column(columnDefinition = "json")
  @Type(type = "json")
  protected Object value;

  public abstract T getName();
}
