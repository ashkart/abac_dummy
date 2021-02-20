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
public class Country implements IAttributable<EntityType> {
  @Id
  protected Long id;

  @Column
  protected String name;

  @OneToMany(mappedBy = "countryId", fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  protected List<Category> categories;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "entity_attribute",
          joinColumns = @JoinColumn(name = "entityId"),
          inverseJoinColumns = @JoinColumn(name = "attributeId")
  )
  @WhereJoinTable(clause = "resource_type = 'COUNTRY'")
  @Fetch(FetchMode.SUBSELECT)
  private List<Attribute<EntityType>> attributes;
}
