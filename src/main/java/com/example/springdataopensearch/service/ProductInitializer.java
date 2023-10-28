/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.springdataopensearch.service;

import com.example.springdataopensearch.model.Product;
import com.example.springdataopensearch.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductInitializer implements InitializingBean {
    private final ProductRepository repository;

    public ProductInitializer(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        repository.save(new Product(
                "1",
                "Utopia Bedding Bed Pillows",
                new BigDecimal("39.99"),
                2,
                "These professionally finished pillows, with high thread counts, provide great comfort against your skin along with added durability "
                        + "that easily resists wear and tear to ensure a finished look for your bedroom.",
                "Utopia Bedding"));

        repository.save(new Product(
                "2",
                "Echo Dot Smart speaker",
                new BigDecimal("34.99"),
                10,
                "Our most popular smart speaker with a fabric design. It is our most compact smart speaker that fits perfectly into small spaces.",
                "Amazon"));
    }
}
