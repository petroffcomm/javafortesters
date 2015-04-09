package com.smpp.tests.submits;

public class SubmitSmData {
	
	public String serviceType;
	public String sourceAddr;
	public String destAddr;
	public byte replaceIfPresentFlag;
	public String shortMessage;
	public String scheduleDeliveryTime;
	public String validityPeriod;
	public byte esmClass;
	public byte protocolId;
	public byte priorityFlag;
	public byte registeredDelivery;
	public byte dataCoding;
	public byte smDefaultMsgId;
	
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public void setSourceAddr(String sourceAddress) {
		this.sourceAddr = sourceAddress;
	}
	
	public void setDestAddr(String destAddress) {
		this.destAddr = destAddress;
	}
	
	public void setReplaceIfPresentFlag(byte replaceIfPresentFlag) {
		this.replaceIfPresentFlag = replaceIfPresentFlag;
	}
	
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	
	public void setScheduleDeliveryTime(String scheduleDeliveryTime) {
		this.scheduleDeliveryTime = scheduleDeliveryTime;
	}
	
	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	
	public void setEsmClass(byte esmClass) {
		this.esmClass = esmClass;
	}
	
	public void setProtocolId(byte protocolId) {
		this.protocolId = protocolId;
	}
	
	public void setPriorityFlag(byte priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	
	public void setRegisteredDelivery(byte registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}
	
	public void setDataCoding(byte dataCoding) {
		this.dataCoding = dataCoding;
	}
	
	public void setSmDefaultMsgId(byte smDefaultMsgId) {
		this.smDefaultMsgId = smDefaultMsgId;
	}	
	
}
