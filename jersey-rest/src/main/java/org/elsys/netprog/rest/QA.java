package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Path("/Servlet")
public class QA {
	List<Question> questions;
	
	public List<Answer> createAnswers(List<String> answersNames, List<Boolean> correctRate){
		List<Answer> list = new ArrayList<Answer>();
		int i=0;
		for(String name : answersNames){
			list.add(new Answer(name, correctRate.get(i)));
			i++;
		}
		
		return list;
	}
	
	public List<Question> createQuestions(List<String> questionsNames){
		List<Question> questions = new ArrayList<Question>();
		for(String name : questionsNames){
			questions.add(new Question(name));
		}
		
		return questions;
	}
	
	public QA(){
		questions = new ArrayList<Question>(this.createQuestions(Arrays.asList("2+2=?", "2*3=?", "5-5=?", "9*0=?", "How are U?")));
		questions.get(0).setAnswers(createAnswers(Arrays.asList("3","2", "90", "4"), Arrays.asList(false, false, false, true)));
		questions.get(1).setAnswers(createAnswers(Arrays.asList("2","88", "60", "6"), Arrays.asList(false, false, false, true)));
		questions.get(2).setAnswers(createAnswers(Arrays.asList("0","1", "10", "15"), Arrays.asList(true, false, false, false)));
		questions.get(3).setAnswers(createAnswers(Arrays.asList("9","0", "4", "11"), Arrays.asList(false, true, false, true)));
		questions.get(4).setAnswers(createAnswers(Arrays.asList("good","bad", "so so", "great"), Arrays.asList(false, false, true, false)));
	} 

	private Object getRandomQuestion(List<Question> questions) {
		Random rnd = new Random();	
		int i = rnd.nextInt(questions.size());
		return questions.toArray()[i];
	}


	@GET
	@Path("/random")
	@Produces(value={MediaType.APPLICATION_JSON})
	public String getQuestionAndAnswers() {
		Question luckyQuestion = (Question) getRandomQuestion(questions);
		JSONObject result = new JSONObject();
		JSONArray answers = new JSONArray();
		
		try {
			result.put(luckyQuestion.getKeyWord(), luckyQuestion.getName());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		for(Answer ans : luckyQuestion.getAnswers()){
			JSONObject ansAsJSON = new JSONObject();
			try {
				ansAsJSON.put(ans.getKeyWord(), ans.getValue());
				ansAsJSON.put("correct", ans.isCorrectionRate());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			answers.put(ansAsJSON);
		}
		
		try {
			result.put("answers", answers);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String formattedResult = System.lineSeparator() + result.toString() + System.lineSeparator();
		return formattedResult;
//		return Response.ok().entity(result).build();
	}
}
