package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{

}
