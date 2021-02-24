package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.jpa.model.Customer;
import com.example.servingwebcontent.jpa.model.abac.annotaion.AccessControl;
import com.example.servingwebcontent.jpa.model.abac.annotaion.property.Id;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.ActionType;
import com.example.servingwebcontent.jpa.model.abac.attribute.type.EntityType;
import com.example.servingwebcontent.jpa.repository.CustomerRepository;
import com.example.servingwebcontent.jpa.repository.RuleRepository;
import com.example.servingwebcontent.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository customerRepository;
  private final UserRepository userRepository;
  private final RuleRepository ruleRepository;

  @GetMapping("/customer")
  public String createCustomer(String firstName, String lastName, Long categoryId, Model model) {
    var customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setCategoryId(categoryId);

    customerRepository.save(customer);

    model.addAttribute("name", firstName);
    return "customer";
  }

  @AccessControl(action = ActionType.READ_ANY_CUSTOMER, resourceType = EntityType.CUSTOMER)
  @GetMapping("/customer/{id}")
  public String getCustomer(
          @Id
          @PathVariable Long id,
          Model model
  ) {
    var customer = customerRepository.findById(id).orElseThrow();

    var user = userRepository.findById(1L).orElseThrow();
    var attrs = user.getAttributes();

    var rule = ruleRepository.findByPolicyId(user.getPolicyId());

    model.addAttribute("name", customer.getFirstName());
    return "customer";
  }
}