package com.station.cff.webservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebserviceCall {

	private static ObjectMapper MAPPER = new ObjectMapper();
	private static LoginOutput loginOutput =null;

	public static LoginOutput login(){
		try {
			String parameter = "username=" + URLEncoder.encode("orders@hulamx.net", "UTF-8")  
					+ "&password=" + URLEncoder.encode("bVLYbOrJ", "UTF-8");
			String response=callPOST("https://portal.javelindelivers.com/login.builtin", parameter);
			System.out.println(""+response);
			loginOutput=MAPPER.readValue(response, LoginOutput.class);
			return loginOutput;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;   
	}

	public static String callPOST(String url,String parameter){


		URL urlObject;
		HttpsURLConnection connection = null;
		try {
			urlObject = new URL(url);
			connection = (HttpsURLConnection) urlObject.openConnection();
			connection.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			connection.setRequestProperty("Content-Length","" + Integer.toString(parameter.getBytes().length)); 
			connection.setRequestProperty("Content-Language", "en-US");  
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(parameter);
			wr.flush();
			wr.close();
			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			//sessionResponse = MAPPER.readValue(response.toString(),SessionResponse.class);
			return response.toString();
		} catch (IOException e) {
			//e.printStackTrace();
			//InfoLogger.printExceptionLog("JavelinTrafficDelivery", "executeLogin", e);
			return null;
		} catch (Exception e) {
			//e.printStackTrace();
			//	InfoLogger.printExceptionLog("JavelinTrafficDelivery", "executeLogin", e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				connection=null;
			}
		}







	}


}
