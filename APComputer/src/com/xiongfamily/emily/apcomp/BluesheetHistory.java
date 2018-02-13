package com.xiongfamily.emily.apcomp;

public class BluesheetHistory {

	String casenumber;
	String startDate;
	String endDate;
	String reqType;  //cusipdate or tickerdate
	String reqOrg;   // S for SEC or F for FINRA

	String processingStart;
	String processingEnd;
	String cusip;
	String ticker;
	String sentTime;
	String sendTo;

	
	public BluesheetHistory(String casenumber, String startDate, String endDate, String reqType, String reqOrg,
			String processingStart, String processingEnd, String cusip, String ticker, String sentTime) {
		this.casenumber = casenumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reqOrg = reqOrg;
		this.processingStart = processingStart;
		this.processingEnd = processingEnd;
		this.cusip = cusip;
		this.ticker = ticker;
		this.sentTime = sentTime;
	}
	
	public BluesheetHistory() {
		
	}

	public String getCasenumber() {
		return casenumber;
	}
	public void setCasenumber(String casenumber) {
		this.casenumber = casenumber;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endEate) {
		this.endDate = endEate;
	}
	public String getReqType() {
		if (reqType == null){
			if(cusip != null && !cusip.isEmpty()){
				this.reqType = "cusipdate";
			}
			else{
				this.reqType = "tickerdate";
			}
		}
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getReqOrg() {
		return reqOrg;
	}
	public void setReqOrg(String reqOrg) {
		this.reqOrg = reqOrg;
	}
	public String getProcessingStart() {
		return processingStart;
	}
	public void setProcessingStart(String processingStart) {
		this.processingStart = processingStart;
	}
	public String getProcessingEnd() {
		return processingEnd;
	}
	public void setProcessingEnd(String processingEnd) {
		this.processingEnd = processingEnd;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getSentTime() {
		return sentTime;
	}
	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	
	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	@Override
	public String toString() {
		return "BluesheetHistory [casenumber=" + casenumber + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", reqType=" + reqType + ", reqOrg=" + reqOrg + ", processingStart=" + processingStart
				+ ", processingEnd=" + processingEnd + ", cusip=" + cusip + ", ticker=" + ticker + ", sentTime="
				+ sentTime + ", sendTo=" + sendTo + "]";
	}

}
