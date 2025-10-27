package org.example.billingservice.feign;

import org.example.billingservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductIRestClient {

    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable String id);

    @GetMapping("/products")
    PagedModel<Product> getAllProducts();
}
