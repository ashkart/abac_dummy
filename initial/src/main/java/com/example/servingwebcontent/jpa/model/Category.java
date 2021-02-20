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
public class Category implements IAttributable<EntityType> {
  @Id
  protected Long id;

  @Column
  protected String name;

  @Column
  protected Long countryId;

  @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
  @Fetch(FetchMode.SUBSELECT)
  protected List<Customer> customers;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "resource_type = 'CATEGORY'")
  @Fetch(FetchMode.SUBSELECT)
  private List<Attribute<EntityType>> attributes;
}
