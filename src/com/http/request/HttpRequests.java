package com.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.rest.User;

public class HttpRequests {
	
	public static void getRequest() throws IOException {
		URL getRequest = new URL("http://localhost:8080/RestTest/rest/UserService/json/all");
		String readLine = null;
		HttpURLConnection connection = (HttpURLConnection) getRequest.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		int responseCode = connection.getResponseCode();
		
		if(responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((readLine = in.readLine()) != null) {
				response.append(readLine);
			}in.close();
			System.out.println("JSON String result " + response.toString());
			/*Genson genson = new GensonBuilder().useConstructorWithArguments(true).create();
			User person = genson.deserialize(response.toString(), User.class);
			System.out.println("USER: " + person);*/
		}else {
			System.out.println("Get NOT WORKING!");
		}
	}
	
	public static void postRequset() throws IOException {
		String POST_PARAMS = "{\"id\":10,\"name\":\"Bob\",\"profession\":\"Singer\"}";
		System.out.println(POST_PARAMS);
		URL getRequest = new URL("http://localhost:8080/RestTest/rest/UserService/json/add/users");
		String readLine = null;
		HttpURLConnection postConnection = (HttpURLConnection) getRequest.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Accept", "application/json");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		
		int responseCode = postConnection.getResponseCode();
		System.out.println(responseCode);
	}
	
	public static void main(String[] args) throws IOException {
		//getRequest();
		postRequset();
		getRequest();
	}
}
