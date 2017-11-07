package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
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
	private double rating;
	private String address;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	private double latitude;
	private double longitude;
	private String description;
	private Set<String> categories;
	private String imageUrl;
	private String url;
	private String snippet;
	private String snippetUrl;
	public static class ItemBuilder {
		private String itemId;
		private String name;
		private double rating;
		private String address;
		private String city;
		private String country;
		private String state;
		private String zipcode;
		private double latitude;
		private double longitude;
		private String description;
		private Set<String> categories;
		private String imageUrl;
		private String url;
		private String snippet;
		private String snippetUrl;

		public ItemBuilder setItemId(String itemId) {
			this.itemId = itemId;
			return this;
		}

		public ItemBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ItemBuilder setRating(double rating) {
			this.rating = rating;
			return this;
		}

		public ItemBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public ItemBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public ItemBuilder setCountry(String country) {
			this.country = country;
			return this;
		}

		public ItemBuilder setState(String state) {
			this.state = state;
			return this;
		}

		public ItemBuilder setZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}

		public ItemBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public ItemBuilder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public ItemBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public ItemBuilder setCategories(Set<String> categories) {
			this.categories = categories;
			return this;
		}

		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public ItemBuilder setUrl(String url) {
			this.url = url;
			return this;
		}

		public ItemBuilder setSnippet(String snippet) {
			this.snippet = snippet;
			return this;
		}

		public ItemBuilder setSnippetUrl(String snippetUrl) {
			this.snippetUrl = snippetUrl;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}
	private Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.rating = builder.rating;
		this.address = builder.address;
		this.city = builder.city;
		this.country = builder.country;
		this.state = builder.state;
		this.zipcode = builder.zipcode;
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.description = builder.description;
		this.categories = builder.categories;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.snippet = builder.snippet;
		this.snippetUrl = builder.snippetUrl;
	}


	public String getItemId() {
		return itemId;
	}
	public String getName() {
		return name;
	}
	public double getRating() {
		return rating;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getDescription() {
		return description;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public String getSnippet() {
		return snippet;
	}
	public String getSnippetUrl() {
		return snippetUrl;
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
	
	// Convert JSONArray to a list of item objects.
		private List<Item> getItemList(JSONArray events) throws JSONException {
			List<Item> itemList = new ArrayList<>();

			for (int i = 0; i < events.length(); i++) {
				JSONObject event = events.getJSONObject(i);
				ItemBuilder builder = new ItemBuilder();
				builder.setItemId(getStringFieldOrNull(event, "id"));
				builder.setName(getStringFieldOrNull(event, "name"));
				builder.setDescription(getDescription(event));
				builder.setCategories(getCategories(event));
				builder.setImageUrl(getImageUrl(event));
				builder.setUrl(getStringFieldOrNull(event, "url"));
				JSONObject venue = getVenue(event);
				if (venue != null) {
					if (!venue.isNull("address")) {
						JSONObject address = venue.getJSONObject("address");
						StringBuilder sb = new StringBuilder();
						if (!address.isNull("line1")) {
							sb.append(address.getString("line1"));
						}
						if (!address.isNull("line2")) {
							sb.append(address.getString("line2"));
						}
						if (!address.isNull("line3")) {
							sb.append(address.getString("line3"));
						}
						builder.setAddress(sb.toString());
					}
					if (!venue.isNull("city")) {
						JSONObject city = venue.getJSONObject("city");
						builder.setCity(getStringFieldOrNull(city, "name"));
					}
					if (!venue.isNull("country")) {
						JSONObject country = venue.getJSONObject("country");
						builder.setCountry(getStringFieldOrNull(country, "name"));
					}
					if (!venue.isNull("state")) {
						JSONObject state = venue.getJSONObject("state");
						builder.setState(getStringFieldOrNull(state, "name"));
					}
					builder.setZipcode(getStringFieldOrNull(venue, "postalCode"));
					if (!venue.isNull("location")) {
						JSONObject location = venue.getJSONObject("location");
						builder.setLatitude(getNumericFieldOrNull(location, "latitude"));
						builder.setLongitude(getNumericFieldOrNull(location, "longitude"));
					}
				}

				// Uses this builder pattern we can freely add fields.
				Item item = builder.build();
				itemList.add(item);
			}

			return itemList;
		}

	              // return the first venue of an event object
		private JSONObject getVenue(JSONObject event) throws JSONException {
			if (!event.isNull("_embedded")) {
				JSONObject embedded = event.getJSONObject("_embedded");
				if (!embedded.isNull("venues")) {
					JSONArray venues = embedded.getJSONArray("venues");
					if (venues.length() >= 1) {
						return venues.getJSONObject(0);
					}
				}
			}
			return null;
		}
		private String getImageUrl(JSONObject event) throws JSONException {
			//...
			return null;
		}

		private Set<String> getCategories(JSONObject event) throws JSONException {
			Set<String> categories = new HashSet<>();
			JSONArray classifications = (JSONArray) event.get("classifications");
			//...
			return categories;
		}

		private String getDescription(JSONObject event) throws JSONException {
			if (!event.isNull("description")) {
				return event.getString("description");
			} else if (!event.isNull("additionalInfo")) {
				return event.getString("additionalInfo");
			} else if (!event.isNull("info")) {
				return event.getString("info");
			} else if (!event.isNull("pleaseNote")) {
				return event.getString("pleaseNote");
			}
			return null;
		}

		private String getStringFieldOrNull(JSONObject event, String field) throws JSONException {
			return event.isNull(field) ? null : event.getString(field);
		}

		private double getNumericFieldOrNull(JSONObject event, String field) throws JSONException {
			return event.isNull(field) ? 0.0 : event.getDouble(field);
		}


}
