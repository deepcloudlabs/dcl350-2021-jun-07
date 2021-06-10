package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.domain.Stock;
import com.example.service.StockmarketService;

@RestController
@RequestScope
@RequestMapping("stocks")
public class StockmarketController {
	@Autowired
	private StockmarketService stockmarketSrv;
	
	@PutMapping("{symbol}")
	public Stock updatePrice(@PathVariable String symbol, @RequestParam double price) {
		return stockmarketSrv.updateStockPrice(symbol, price);
	}
	
}
