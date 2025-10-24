package org.example.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all",types = {Customer.class})
public interface CustomerProjection {
    Long getId();
    String getName();
 }
