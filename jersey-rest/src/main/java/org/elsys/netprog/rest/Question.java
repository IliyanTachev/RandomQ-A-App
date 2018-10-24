package org.elsys.netprog.rest;

import java.util.ArrayList;
import java.util.List;

public class Question {
	String keyWord;
	String name;
	List<Answer> answers;
	
	public Question(String name){
		keyWord = "question";
		this.name = name;
		answers = new ArrayList<Answer>();
	}
	
	public String getKeyWord() {
		return keyWord;
	}

	public String getName() {
		return name;
	}
	
	public void setAnswers(List<Answer> list){
		answers = list;
	}
	
	public List<Answer> getAnswers(){
		return answers;
	}
}
