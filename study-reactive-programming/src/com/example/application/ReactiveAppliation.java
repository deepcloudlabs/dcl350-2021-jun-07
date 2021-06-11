package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class ReactiveAppliation {

	public static void main(String[] args) {
		SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();
		var slowSubscriber = new AlgoTrader();
		var fastSubscriber = new SignalProcessor();
		publisher.subscribe(slowSubscriber);
		publisher.subscribe(fastSubscriber);
		var events = List.of(
				new TradeEvent("orcl", 100, 10),
				new TradeEvent("ibm", 200, 20),
				new TradeEvent("msft", 300, 30),
				new TradeEvent("orcl", 101, 11),
				new TradeEvent("ibm", 200, 22),
				new TradeEvent("msft", 302, 32)
		);
		events.forEach(publisher::submit);
		try{TimeUnit.SECONDS.sleep(30);}catch(Exception e) {}
		publisher.close();
		System.err.println("Application is done!");
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try{TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
		System.err.println("AlgoTrader: "+event);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader is done.");
	}
	
}

class SignalProcessor implements Flow.Subscriber<TradeEvent> {
	
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);		
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProcessor: "+event);
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable throwable) {
		
	}
	
	@Override
	public void onComplete() {
		System.err.println("SignalProcessor is done.");
	}
	
}
