package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class LegacyOberverPatternAppliation {

	public static void main(String[] args) {
		ObservableTrade observable = new ObservableTrade();
		Observer slowObserver = (o,event) -> {
			try{TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
			System.err.println("Slow Observer: "+event);
		};
		Observer fastObserver = (o,event) -> {
			System.err.println("Fast Observer: "+event);
		};
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		var events = List.of(
				new TradeEvent("orcl", 100, 10),
				new TradeEvent("ibm", 200, 20),
				new TradeEvent("msft", 300, 30),
				new TradeEvent("orcl", 101, 11),
				new TradeEvent("ibm", 200, 22),
				new TradeEvent("msft", 302, 32)
		);
		events.forEach(observable::notifyObservers);
	}

}

class ObservableTrade extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}