package org.example.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.billingservice.dto.Customer;

import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Bill {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Date billingDate;

    private Long customerId;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<ProductItem> productItems;

    @Transient private Customer customer;
}
