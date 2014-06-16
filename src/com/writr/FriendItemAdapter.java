package com.writr;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendItemAdapter extends ArrayAdapter<FriendItem> {
	private ArrayList<FriendItem> objects;
	HttpURLConnection connection;
	Bitmap bmp = null;

	public FriendItemAdapter(Context context, int textViewResourceId,
			ArrayList<FriendItem> objects) {
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

		final FriendItem i = objects.get(position);
		if (i != null) {

			TextView tvLabel = (TextView) v.findViewById(R.id.label);
			TextView tvDescription = (TextView) v
					.findViewById(R.id.itemDescription);
			ImageView ivThumbnail = (ImageView) v
					.findViewById(R.id.ivThumbnail);

			if (tvLabel != null) {
				tvLabel.setText(i.getName());
			}
			if (tvDescription != null) {
				tvDescription.setText(i.getPresence());
			}

			if (ivThumbnail != null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
					
						try {
							
							bmp = i.getImage();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
				ivThumbnail.setImageBitmap(bmp);
			}
		}
		return v;
	}
}