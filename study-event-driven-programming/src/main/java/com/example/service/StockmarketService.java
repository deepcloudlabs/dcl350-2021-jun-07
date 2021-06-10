package com.example.service;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Stock;
import com.example.event.StockPriceChangedEvent;
import com.example.repository.StockRepository;

@Service
public class StockmarketService {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	// Warning: ApplicationEventPublisher has sync. implementation!
	private ApplicationEventPublisher publisher;
	@Autowired
	private ExecutorService executorService;
	
	@Transactional
	public Stock updateStockPrice(String symbol,double price) {
		var stock = stockRepository.findById(symbol);
		if (stock.isPresent()) {
			var managedStock = stock.get();
			var oldPrice = managedStock.getPrice();
			managedStock.setPrice(price);
			/*
			stockRepository.save(managedStock);
			stockRepository.flush();
			*/
			var event = new StockPriceChangedEvent(symbol,oldPrice,price);
			// Warning: publishEvent is a synchronous method!
			executorService.submit(() -> publisher.publishEvent(event));
			System.err.println(Thread.currentThread().getName()+": updateStockPrice() is returning!");
			return managedStock;
		} 
		return new Stock();
	}
}
