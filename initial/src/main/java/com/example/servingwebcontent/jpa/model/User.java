package com.example.servingwebcontent.jpa.model;

import com.example.servingwebcontent.jpa.model.abac.Policy;
import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.SubjectType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.List;

@Entity

@Getter
@Setter

@Table(name = "user", schema = "public")
public class User implements IAttributable<SubjectType> {
  @Id
  @GeneratedValue
  protected Long id;

  @Column
  protected String email;

  @Column
  protected Long policyId;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "entity_type = 'SUBJECT'")
  @Fetch(FetchMode.SUBSELECT)
  protected List<Attribute<SubjectType>> attributes;

  @ManyToOne
  @JoinColumn(name = "policyId", insertable = false, updatable = false)
  protected Policy policy;
}
