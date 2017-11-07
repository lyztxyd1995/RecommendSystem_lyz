package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import entity.Item;
public class RpcHelper {
	//use for test
	public static JSONArray getJSONArray(List<Item> items) {
	    JSONArray result = new JSONArray();
	    try {
	      for (Item item : items) {
	        result.put(item.toJSONObject());
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

	 // Writes a JSONObject to http response.
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) {
		try {
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


		// Writes a JSONArray to http response.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) {
		try {
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(obj);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //read JSONObject from http request
	public static JSONObject readJsonObject(HttpServletRequest request) {
		try {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
			reader.close();
		JSONObject input = new JSONObject(sb.toString());
		return input;
	}catch(Exception e) {
		e.printStackTrace();
	}
		return null;
}
}
