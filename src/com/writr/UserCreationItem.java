package com.writr;

public class UserCreationItem {
	private String name;
	private String description;
	private int creationStatus;

	public UserCreationItem(String i) {
		
		this.name = i;
	
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

	public int getCreationStatus() {
		return creationStatus;
	}

	public void setCreationStatus(int creationStatus) {
		this.creationStatus = creationStatus;
	}

}