package com.example.servingwebcontent.jpa.model;

import com.example.servingwebcontent.jpa.model.abac.attribute.Attribute;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
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
public class Customer implements IAttributable<EntityType> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private Long categoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoryId", insertable = false, updatable = false)
  private Category category;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "entity_type = 'CUSTOMER'")
  @Fetch(FetchMode.SUBSELECT)
  private List<Attribute<EntityType>> attributes;

  @Override
  public String toString() {
    return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s']",
            id, firstName, lastName);
  }
}
