package com.example.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.boundary.Ticker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveRestClientService {
	private static final String binanceHttpBaseUrl = "https://api.binance.com/api/v3";
	private static final List<String> SYMBOLS = List.of(
			"BTCUSDT","LTCBTC","ETHBTC","BNBBTC","NEOBTC","EOSETH",
			"SNTETH","BNTETH","BCCBTC","SALTBTC","SALTETH","XVGETH",
			"XVGBTC", "SUBETH","EOSBTC","MTHBTC","ETCETH","DNTBTC","ENGBTC"			
	);
	private WebClient webClient = WebClient.builder().baseUrl(binanceHttpBaseUrl).build();
	
	@Scheduled(fixedRate = 5_000)
	public void callBinance() {
		System.err.println(String.format("Calling binance %d times...",SYMBOLS.size()));
		Comparator<Ticker> descOrderByNumericPrice = 
				(t1,t2) -> {
					var price1 = Double.parseDouble(t1.getPrice());
					var price2 = Double.parseDouble(t2.getPrice());
					return price2<price1 ? -1 : +1 ;
				};
		Flux.fromIterable(SYMBOLS)
		    .parallel()
		    .runOn(Schedulers.boundedElastic())
		    .flatMap(this::getTicker)
		    .ordered(descOrderByNumericPrice)
		    .subscribe(System.err::println);
		System.err.println(String.format("Calling binance %d times...done.",SYMBOLS.size()));
	}
	
	public Mono<Ticker> getTicker(String symbol){
		return webClient.get()
				        .uri(uriBuilder -> uriBuilder.path("/ticker/price")
				        		                     .queryParam("symbol", symbol).build())
				        .retrieve()
				        .bodyToMono(Ticker.class);
	}
}
