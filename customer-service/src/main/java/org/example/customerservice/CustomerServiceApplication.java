package org.example.customerservice;

import org.example.customerservice.config.CustomerConfigParams;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
          customerRepository.save(Customer.builder().name("ayoub")
                          .email("ayoub@gmail.Com")
                  .build());

          customerRepository.save(Customer.builder()
                  .name("zakaria")
                  .email("zakaria@gmail.com")
                  .build());

            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
