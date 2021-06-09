package com.example.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

// {"e":"trade","E":1623245652367,"s":"BTCUSDT","t":899016981,"p":"34926.87000000","q":"0.00611900","b":6381712772,"a":6381712756,"T":1623245652367,"m":false,"M":true}
public class Trade {
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("b")
	private long bid;
	@JsonProperty("a")
	private long ask;
	@JsonProperty("T")
	private long epoch;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public long getAsk() {
		return ask;
	}
	public void setAsk(long ask) {
		this.ask = ask;
	}
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + ", bid=" + bid + ", ask="
				+ ask + ", epoch=" + epoch + "]";
	}
	
}
