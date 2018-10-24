package org.elsys.netprog.rest;

public class Answer {
	String keyWord;
	String value;
	boolean correctionRate;
	
	public Answer(String value, boolean correctRate){
		keyWord = "answer";
		this.value = value;
		correctionRate = correctRate;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public String getValue() {
		return value;
	}

	public boolean isCorrectionRate() {
		return correctionRate;
	}
}