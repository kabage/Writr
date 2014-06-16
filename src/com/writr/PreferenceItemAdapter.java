package com.writr;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PreferenceItemAdapter extends ArrayAdapter<PreferenceItem> {
	private ArrayList<PreferenceItem> objects;

	public PreferenceItemAdapter(Context context, int textViewResourceId,
			ArrayList<PreferenceItem> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.icon_list_item, null);
		}

		PreferenceItem i = objects.get(position);
		if (i != null) {

			TextView tvLabel = (TextView) v.findViewById(R.id.label);
			TextView tvDescription = (TextView) v
					.findViewById(R.id.itemDescription);
			ImageView ivImage = (ImageView) v.findViewById(R.id.ivThumbnail);

			if (tvLabel != null) {
				tvLabel.setText(i.getName());
			}
			if (tvDescription != null) {
				tvDescription.setText(i.getDescription());
			}
			if (ivImage != null) {
				ivImage.setImageResource(i.getImage());
			}
		}
		return v;
	}
}