package com.writr;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.PageIndicator;

public abstract class BaseActivity extends SherlockFragmentActivity {

	FragmentAdapter mAdapter;
	ViewPager mPager;
	Intent i;
	PageIndicator mIndicator;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.dashboard_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			i = new Intent(getApplicationContext(), About.class);
			startActivity(i);
			break;
		case R.id.mShare:
			i = new Intent(android.content.Intent.ACTION_SEND);
			i.setType("text/plain");
			i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Writr");
			i.putExtra(
					android.content.Intent.EXTRA_TEXT,
					"Hey! Let's make stuff together on Writr!\n\nhttps://play.google.com/store/apps/details?=com.writr");
			startActivity(Intent.createChooser(i, "Share using..."));
			break;
		case R.id.mPreferences:
			i = new Intent(getApplicationContext(), Settings.class);
			startActivity(i);
			break;
		case R.id.mCreateNew:
			i = new Intent(getApplicationContext(), CreateNew.class);
			startActivity(i);
			break;
		case R.id.mDiscover:
			i = new Intent(getApplicationContext(), Discover.class);
			startActivity(i);
			break;
		case R.id.mSearch:
			i = new Intent(getApplicationContext(), Search.class);
			startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
