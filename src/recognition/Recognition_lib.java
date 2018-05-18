package recognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition_lib {
	private int age_min, age_max;
	private double age_score, gender_score;
	private String gender;
	VisualRecognition service;
	
	public Recognition_lib() {
		service = new VisualRecognition("2018-03-19");
		service.setApiKey("naisyo");
	}
	
	//顔認識を実施
	public void getFaceRecognition(String image_path) {
		DetectFacesOptions detectFacesOptions = null;
		try {
			detectFacesOptions = new DetectFacesOptions.Builder()
				.imagesFile(new File(image_path))
				.build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DetectedFaces result = service.detectFaces(detectFacesOptions).execute();
		//System.out.println(result);
		this.getJsonNode(String.valueOf(result));
	}
	
	public void getJsonNode(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(json);
			age_min = node.get("images").get(0).get("faces").get(0).get("age").get("min").asInt();
            System.out.println("age_min : " + age_min);
            age_max = node.get("images").get(0).get("faces").get(0).get("age").get("max").asInt();
            System.out.println("age_max : " + age_max);
            age_score = node.get("images").get(0).get("faces").get(0).get("age").get("score").asDouble();
            System.out.println("age_score : " + age_score);
            
            gender = node.get("images").get(0).get("faces").get(0).get("gender").get("gender").asText();
            System.out.println("Gender : " + gender);
            gender_score = node.get("images").get(0).get("faces").get(0).get("gender").get("score").asDouble();
            System.out.println("Gender_score : " + gender_score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
