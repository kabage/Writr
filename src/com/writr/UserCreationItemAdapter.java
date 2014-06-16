package com.writr;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserCreationItemAdapter extends ArrayAdapter<UserCreationItem> {
	private ArrayList<UserCreationItem> objects;

	public UserCreationItemAdapter(Context context, int textViewResourceId,
			ArrayList<UserCreationItem> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.user_creation_list_item, null);
		}

		UserCreationItem i = objects.get(position);
		if (i != null) {

			TextView tvLabel = (TextView) v.findViewById(R.id.label);
			TextView tvDescription = (TextView) v
					.findViewById(R.id.itemDescription);
			ImageView ivCreationStatus = (ImageView) v
					.findViewById(R.id.ivCreationStatus);

			if (tvLabel != null) {
				tvLabel.setText(i.getName());
			}
			if (tvDescription != null) {
				tvDescription.setText(i.getDescription());
			}
			if (ivCreationStatus != null) {
				ivCreationStatus.setImageResource(i.getCreationStatus());
			}

		}
		return v;
	}
}