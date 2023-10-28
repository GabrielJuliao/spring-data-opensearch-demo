/*
 * Copyright OpenSearch Contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.springdataopensearch.controller;

import com.example.springdataopensearch.model.Product;
import com.example.springdataopensearch.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }

    @GetMapping("{name}")
    public ResponseEntity<Product> getAll(@PathVariable String name) {
        return ResponseEntity.ok(repository.findByName(name));
    }


}
