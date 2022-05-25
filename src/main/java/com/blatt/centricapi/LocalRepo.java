package com.blatt.centricapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocalRepo extends JpaRepository<Product, Long> {

}