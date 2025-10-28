package org.example.billingservice.controller;

import jakarta.persistence.Id;
import org.example.billingservice.entities.Bill;
import org.example.billingservice.feign.CustomerRestCLient;
import org.example.billingservice.feign.ProductIRestClient;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    @Autowired
    private BillRepository  billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestCLient  customerRestCLient;
    @Autowired
    private ProductIRestClient  productIRestClient;

    @GetMapping("/api/bill/{id}")
    public Bill getBillById(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestCLient.findCustomerById(id));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productIRestClient.findProductById(productItem.getProductId()));
        });
        return bill;
    }






}
