package com.writr;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {
	private ArrayList<Item> objects;
	
	public ItemAdapter(Context context, int textViewResourceId,
			ArrayList<Item> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
		}

		Item i = objects.get(position);
		if (i != null) {

			TextView tvLabel = (TextView) v.findViewById(R.id.label);
			TextView tvDescription = (TextView) v.findViewById(R.id.itemDescription);

			if (tvLabel != null) {
				tvLabel.setText(i.getName());
			}
			if (tvDescription != null) {
				tvDescription.setText(i.getDescription());
			}
		}
		return v;
	}
}