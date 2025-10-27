package org.example.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.billingservice.dto.Product;

@Entity @Data  @NoArgsConstructor
@AllArgsConstructor @Builder
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String productId;

    @ManyToOne
    private Bill bill ;

    private int quantity ;

    private double unitPrice ;

    @Transient private Product product ;
}
