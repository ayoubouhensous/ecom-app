package org.example.billingservice;

import org.example.billingservice.dto.Customer;
import org.example.billingservice.dto.Product;
import org.example.billingservice.entities.Bill;
import org.example.billingservice.entities.ProductItem;
import org.example.billingservice.feign.CustomerRestCLient;
import org.example.billingservice.feign.ProductIRestClient;
import org.example.billingservice.repository.BillRepository;
import org.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository  billRepository, ProductItemRepository  productItemRepository, CustomerRestCLient  customerRestCLient, ProductIRestClient productIRestClient) {
        return args -> {

            Collection<Customer> customers = customerRestCLient.getAllCustomers().getContent();

            Collection<Product> products = productIRestClient.getAllProducts().getContent();

            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build();

                billRepository.save(bill);
                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(new Random().nextInt(10)+1)
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });
    


        };
    }

}
