package com.writr;

public class PreferenceItem {
	private String name;
	private String description;
	private int image;

	public PreferenceItem(String i, String d, int icon) {
		this.description = d;
		this.name = i;
		this.image = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}
}