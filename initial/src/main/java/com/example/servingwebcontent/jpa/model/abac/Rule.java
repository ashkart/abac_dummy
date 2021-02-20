package com.example.servingwebcontent.jpa.model.abac;

import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.ActionType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EnvironmentType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.SubjectType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity

@Getter
@Setter

@TypeDefs({
        @TypeDef(name = "string_array", typeClass = StringArrayType.class),
})
public class Rule {

  @Id
  protected Long id;

  @Column
  protected Long policyId;

  @Column
  @Enumerated(EnumType.STRING)
  protected ActionType actionType;

  @Column
  @Enumerated(EnumType.STRING)
  protected Operator operator;

  /** User should have these */
  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "entity_type = 'SUBJECT'")
  @Fetch(FetchMode.SUBSELECT)
  protected List<Attribute<SubjectType>> subjectAttributes;

  /** To access these */
  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "entity_type != 'SUBJECT' and entity_type != 'ENVIRONMENT'")
  @Fetch(FetchMode.SUBSELECT)
  protected List<Attribute<EntityType>> resourceAttributes;

  /** But for only in these environment conditions */
  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "entity_type = 'ENVIRONMENT'")
  @Fetch(FetchMode.SUBSELECT)
  protected List<Attribute<EnvironmentType>> environmentAttributes;

  @ManyToOne
  @JoinColumn(name = "policyId", insertable = false, updatable = false)
  protected Policy policy;

  public enum Operator {
    HAS_ANY,
    HAS_NOT_ANY,
    HAS_ALL,
    HAS_NOTHING_OF
  }
}
