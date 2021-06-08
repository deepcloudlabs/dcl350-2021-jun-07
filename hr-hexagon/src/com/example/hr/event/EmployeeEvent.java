package com.example.hr.event;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	protected String eventId;
	protected long sequenceNo;
	protected TcKimlikNo tcKimlikNo;
	
	public EmployeeEvent(TcKimlikNo tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public String getEventId() {
		return eventId;
	}

	public long getSequenceNo() {
		return sequenceNo;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}
	
}
