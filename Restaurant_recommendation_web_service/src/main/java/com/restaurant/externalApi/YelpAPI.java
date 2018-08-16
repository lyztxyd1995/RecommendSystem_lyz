package com.restaurant.externalApi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.restaurant.entity.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YelpAPI {
    private static final String API_HOST = "https://api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final int SEARCH_LIMIT = 20;
    private static final String SEARCH_PATH = "/v3/businesses/search";

    private static final String TOKEN_HOST = "https://api.yelp.com/oauth2/token";
    private static final String CLIENT_ID = "3Hsp7BIXo4--49AVgU6mFw";
    private static final String API_KEY = "LqLMb4lWEXY3f8LDsii29uHGGgeCdiToNGseoC4RAKi68AL65jpjrQMM-wMeukFgD_YxuaNg5pY44JjM0H88IN-CJTRBFccvBfW9Vgov9X85agqOT4jDTi93mEfKWnYx";
    private static final String GRANT_TYPE = "client_credentials";
    private static final String TOKEN_TYPE = "Bearer";

    // Cache your access token so you get faster access to our data by eliminating an extra request
    // for each API call.
    private static String accessToken;

    public YelpAPI() {}
    private String getAccessToken() {
        if (accessToken != null) {
            return accessToken;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(TOKEN_HOST).openConnection();

            // set HTTP POST method
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            // add request body
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            String query = String.format("grant_type=%s&client_id=%s", GRANT_TYPE,
                    CLIENT_ID);
            wr.write(query.getBytes());

            // get response
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + TOKEN_HOST);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            accessToken = (new JSONObject(response.toString())).getString("access_token");
            System.out.println("Get access token : " + accessToken);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> getItemList(JSONArray array) throws JSONException {
        List<Item> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            // Parse json object fetched from Yelp API specifically.
            Item item = new Item();
            // Builder pattern gives us flexibility to construct an item.
            item.setItemId(object.getString("id"));
            JSONArray jsonArray = (JSONArray) object.get("categories");
            Set<String> categories = new HashSet<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject subObejct = jsonArray.getJSONObject(j);
                categories.add(subObejct.getString("title"));
            }
            item.setCategories(categories);
            item.setName(object.getString("name"));
            item.setImageUrl(object.getString("image_url"));
            item.setRating(object.getDouble("rating"));
            JSONObject coordinates = (JSONObject) object.get("coordinates");
            item.setLatitude(coordinates.getDouble("latitude"));
            item.setLongitude(coordinates.getDouble("longitude"));
            JSONObject location = (JSONObject) object.get("location");
            item.setCity(location.getString("city"));
            item.setState(location.getString("state"));
            item.setZipcode(location.getString("zip_code"));
            JSONArray addresses = (JSONArray) location.get("display_address");
            String fullAddress = addresses.join(",");
            item.setAddress(fullAddress);
            item.setImageUrl(object.getString("image_url"));
            item.setUrl(object.getString("url"));
            list.add(item);
        }
        return list;
    }


    private String urlEncodeHelper(String term) {
        try {
            term = java.net.URLEncoder.encode(term, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    /**
     * Queries the Search API based on the command line arguments and takes the first result to query
     * the Business API.
     */
    private void queryAPI(double lat, double lon) {
        List<Item> list = search(lat, lon, null);
        try {
            for (Item item : list) {
                // This is a thin version of the original JSONObject fetched from Yelp.
                JSONObject jsonObject = item.toJSONObject();
                System.out.println(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Item> search(double lat, double lon, String term, int limit){
        String latitude = lat + "";
        String longitude = lon + "";
        if (term == null || term.isEmpty()) {
            term = DEFAULT_TERM;
        }
        term = urlEncodeHelper(term);
        String query = String.format("term=%s&latitude=%s&longitude=%s&limit=%s", term, latitude,
                longitude, limit);
        String url = API_HOST + SEARCH_PATH;
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(url + "?" + query).openConnection();

            // optional default is GET
            connection.setRequestMethod("GET");

            // add request header
            connection.setRequestProperty("Authorization","Bearer LqLMb4lWEXY3f8LDsii29uHGGgeCdiToNGseoC4RAKi68AL65jpjrQMM-wMeukFgD_YxuaNg5pY44JjM0H88IN-CJTRBFccvBfW9Vgov9X85agqOT4jDTi93mEfKWnYx");

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url + "?" + query);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Get businesses array only.
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray array = (JSONArray) jsonObject.get("businesses");

            // Convert it to list of items.
            return getItemList(array);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> search(double lat, double lon, String term) {
        return search(lat,lon,term,SEARCH_LIMIT);
    }

    /**
     * Main entry for sample Yelp API requests.
     */
    public static void main(String[] args) {
        YelpAPI yelpApi = new YelpAPI();
        List<Item>itemList = yelpApi.search(37.38, -122.08,"dinner");
    }
}
