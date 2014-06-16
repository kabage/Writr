package com.writr;

import android.graphics.Bitmap;

public class FriendItem {
	private String name;
	private String presence;
	private Bitmap image;

	public FriendItem(String Name,String Presence, Bitmap im) {
		this.presence = Presence;
		this.name = Name;
		this.image = im;
	}

	public String getPresence() {
		return presence ;
	}

	public void setPresence(String presence) {
		this.presence = presence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImageUrl(Bitmap image) {
		this.image = image;
	}
}