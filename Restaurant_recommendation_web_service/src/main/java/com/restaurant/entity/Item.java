package com.restaurant.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class Item {
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
        return result;
    }


    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("item_id", itemId);
            obj.put("name", name);
            obj.put("rating", rating);
            obj.put("address", address);
            obj.put("city", city);
            obj.put("country", country);
            obj.put("state", state);
            obj.put("zipcode", zipcode);
            obj.put("latitude", latitude);
            obj.put("longitude", longitude);
            obj.put("description", description);
            obj.put("categories", new JSONArray(categories));
            obj.put("image_url", imageUrl);
            obj.put("url", url);
            obj.put("snippet_url", snippetUrl);
            obj.put("snippet", snippet);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

        @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        if (zipcode == null) {
            if (other.zipcode != null)
                return false;
        } else if (!zipcode.equals(other.zipcode))
            return false;
        return true;
    }

    private String itemId;
    private String name;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private double rating;
    private String address;
    private double latitude;
    private double longitude;
    private String description;
    private String snippet;
    private String snippetUrl;
    private String imageUrl;
    private String url;
    private Set<String> categories;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSnippetUrl() {
        return snippetUrl;
    }

    public void setSnippetUrl(String snippetUrl) {
        this.snippetUrl = snippetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

        @Override
        public String toString() {
            return "Item{" +
                    "itemId='" + itemId + '\'' +
                    ", name='" + name + '\'' +
                    ", rating=" + rating +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", state='" + state + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", description='" + description + '\'' +
                    ", categories=" + categories +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", url='" + url + '\'' +
                    ", snippet='" + snippet + '\'' +
                    ", snippetUrl='" + snippetUrl + '\'' +
                    '}';
        }
    }
