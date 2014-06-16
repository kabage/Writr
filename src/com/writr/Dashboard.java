package com.writr;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.tjeannin.apprate.AppRate;
import com.viewpagerindicator.TitlePageIndicator;
import com.writr.backend.InitializeConnection;
import com.writr.backend.Master;
import com.writr.backend.RetrievePublished;
import com.writr.backend.RosterRetrieval;
import com.writr.backend.WrittingData;

public class Dashboard extends BaseActivity {

	ActionMode mMode;
	ArrayList<String> userCreationId, userCreationTitleArray,
			userCreationAuthorArray, userCreationSummary, userCreationContent,
			userCreationAuthorUrl;
	ArrayList<String> publishedCreationId, publishedCreationTitleArray,
			publishedCreationAuthorArray, publishedCreationSummary,
			publishedCreationContent, publiMultiUserChatshedCreationAuthorUrl;
	ArrayList<String> friendIdArray, friendNameListArray, friendListStatus,
			friendListProfilePictureUrl, friendListId;
	ArrayList<String> favouriteId, favouritesTitleArray, favouritesSummary,
			favouritesAuthorArray, favouritesContent, favouritesAuthorUrl;
	int friendActionItemPosition;
	AlertDialog.Builder builder;
	AppRate rate;
	Handler myHandler;
	ArrayList<String> al;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dashboard);
		getSupportActionBar().setHomeButtonEnabled(true);

		final int DO_UPDATE_TEXT = 0;
		final int DO_THAT = 1;

		myHandler = new Handler() {
			public void handleMessage(Message msg) {
				final int what = msg.what;
				switch (what) {
				case DO_UPDATE_TEXT:
					Intent i = new Intent(Dashboard.this, Master.class);
					startService(i);
					// Log.i("message received", "message received ");
					break;
				case DO_THAT:
					// doThat();
					break;
				}
			}
		};
		InitializeConnection.initialize(this, myHandler);
		setAdapter();
		rateApp();

	}

	private void getData() {
		// TODO Auto-generated method stub

		getUserCreationData();
		getPublishedCreationData();
		getFriendData();
		getFavouriteData();

	}

	private void getUserCreationData() {

		// TODO Auto-generated method stub
		// Get the user creations from the local database
		// Populate userCreationArray, userCreationDescriptionArray,
		// userCreationSummary and userCreationContent
	}

	private void getPublishedCreationData() {
		// TODO Auto-generated method stub
		// Get the published creations from the online databse
		// Populate publishedCreationArray, publishedCreationDescriptionArray,
		// publishedCreationSummary, publishedCreationContent and
		// publishedCreationAuthorUrl
	}

	private void getFriendData() {
		// Get the friend data from the local database

	}

	private void getFavouriteData() {
		// TODO Auto-generated method stub
		// Get favourites from the local database
		// Populate favouritesArray, favouritesDescriptionArray,
		// favouritesSummary, favouritesContent and favouritesAuthorUrl
	}

	public void initPagerView(int position, View view) {
		ListView listView;
		switch (position) {
		case 0:
			al = WrittingData.myWrittingDetails(Dashboard.this);
			final ArrayList<UserCreationItem> userCreation = new ArrayList<UserCreationItem>();

			for (int i = 0; i < al.size(); i++) {
				String x = al.get(i);
				if (!userCreation.contains(new UserCreationItem(x))) {
					userCreation.add(new UserCreationItem(x));
				}
			}
			al.clear();

			UserCreationItemAdapter adapter = new UserCreationItemAdapter(
					getApplicationContext(), R.layout.user_creation_list_item,
					userCreation);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub

					UserCreationItem uci = userCreation.get(arg2);
					String creationName = uci.getName();
					Log.i("title ofthe creation", creationName);
					// GetDataOnWritting.getData(MaintainConnection.connection,
					// creationName);

					i = new Intent(getApplicationContext(),
							EditableCreationView.class);
					Bundle b = new Bundle();
					b.putString("title", creationName);
					i.putExtras(b);
					startActivity(i);

				}
			});
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					mMode = startActionMode(new UserCreationActionView());
					return true;
				}
			});
			break;
		case 1:
			ArrayList<Item> publishedCreation = new ArrayList<Item>();

			// for (int i = 0; i < publishedCreationTitleArray.size(); i++) {
			// publishedCreation.add(new
			// Item(publishedCreationTitleArray.get(i),
			// publishedCreationSummary.get(i)));
			// }
			
			publishedCreation.add(new Item("Published Creation",
					"Published creation summary"));
			publishedCreation.add(new Item("Published Creation",
					"Published creation summary"));
			publishedCreation.add(new Item("Published Creation",
					"Published creation summary"));

			ItemAdapter publishedAdapter = new ItemAdapter(
					getApplicationContext(), R.layout.list_item,
					publishedCreation);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(publishedAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub

					// String id = publishedCreationTitleArray.get(arg2);
					// String title = publishedCreationTitleArray.get(arg2);
					// String creation = publishedCreationContent.get(arg2);
					// String authorName =
					// publishedCreationAuthorArray.get(arg2);
					// String summary = publishedCreationSummary.get(arg2);
					// String imageUrl = publishedCreationAuthorUrl.get(arg2);

					String id = "yf78sfdf";
					String title = "A Game of Thrones";
					String creation = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely.";
					String authorName = "George R.R. Martin";
					String summary = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.*";
					String imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";

					i = new Intent(getApplicationContext(),
							NonEditableCreationView.class);
					Bundle b = new Bundle();
					b.putString("id", id);
					b.putString("title", title);
					b.putString("creation", creation);
					b.putString("authorName", authorName);
					b.putString("summary", summary);
					b.putString("imageUrl", imageUrl);
					i.putExtras(b);
					startActivity(i);
				}
			});
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					mMode = startActionMode(new PublishedActionView());
					return true;
				}
			});
			break;
		case 2:
			ListView list = (ListView) findViewById(R.id.lvListItems);
			loadingImage l = new loadingImage(this, list, i,
					friendActionItemPosition, mMode);
			l.execute();
			break;
		case 3:
			// Get array of the user's favourites from SharedPreferences
			ArrayList<Item> favourites = new ArrayList<Item>();

			// for (int i = 0; i < favouritesTitleArray.size(); i++) {
			// favourites.add(new Item(favouritesTitleArray.get(i),
			// favouritesSummary.get(i)));
			// }

			favourites.add(new Item("Favourite", "Favourite summary"));
			favourites.add(new Item("Favourite", "Favourite summary"));
			favourites.add(new Item("Favourite", "Favourite summary"));

			ItemAdapter favouritesAdapter = new ItemAdapter(
					getApplicationContext(), R.layout.list_item, favourites);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(favouritesAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub

					// String id = favouritesTitleArray.get(arg2);
					// String title = favouritesTitleArray.get(arg2);
					// String creation = favouritesContent.get(arg2);
					// String authorName =
					// favouritesAuthorArray.get(arg2);
					// String summary = favouritesSummary.get(arg2);
					// String imageUrl = favouritesAuthorUrl.get(arg2);

					String id = "f7s8rfr";
					String title = "The Deathly Hallows";
					String creation = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely.";
					String authorName = "George R.R. Martin";
					String summary = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.*";
					String imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";

					i = new Intent(getApplicationContext(),
							FavouritesCreationView.class);
					Bundle b = new Bundle();
					b.putString("id", id);
					b.putString("title", title);
					b.putString("creation", creation);
					b.putString("authorName", authorName);
					b.putString("summary", summary);
					b.putString("imageUrl", imageUrl);
					i.putExtras(b);
					startActivity(i);
				}
			});
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					mMode = startActionMode(new FavouritesActionView());
					return true;
				}
			});
			break;

		}
	}

	private void setAdapter() {

		// TODO Auto-generated method stub
		FragmentAdapter adapter = new FragmentAdapter(Dashboard.this);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);

		mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}

	private void rateApp() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(Dashboard.this);
		rate = new AppRate(Dashboard.this);
		builder.setTitle("Rate Writr!")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage("Please rate the app. Tell us what you think.")
				.setPositiveButton("Yes", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent i = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://play.google.com/store/apps/details?=com.writr"));
						startActivity(i);
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(120);
					}
				}).setNeutralButton("Later", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(3);
					}
				}).setNegativeButton("No", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(10);
					}
				});

		rate.setShowIfAppHasCrashed(false).setMinLaunchesUntilPrompt(20)
				.setCustomDialog(builder).init();
	}

	private final class UserCreationActionView implements ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add(0, 76658, 0, "Edit")
					.setIcon(android.R.drawable.ic_menu_edit)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.add(1, 545678, 1, "Delete")
					.setIcon(android.R.drawable.ic_menu_delete)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case 76658:
				// Get data about selected item
				String id = "65878";
				String title = "A Song of Ice and Fire";
				String creation = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely. You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.* matches every file name. 7-Zip treats *.* as matching only a file name with an extension. To process all files, you must use a * wildcard or just omit the wildcard entirely.";
				String authorName = "George R.R. Martin";
				String summary = "You probably used a *.* wildcard. 7-Zip doesn't use the Windows system wildcard parser; so, 7-Zip doesn't follow the archaic rule by which *.*";
				String imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";

				Intent i = new Intent(getApplicationContext(),
						ContentEditor.class);
				Bundle b = new Bundle();
				b.putString("id", id);
				b.putString("title", title);
				b.putString("authorName", authorName);
				b.putString("summary", summary);
				b.putString("content", creation);
				b.putString("imageUrl", imageUrl);
				i.putExtras(b);
				startActivity(i);
				break;
			case 545678:
				// Delete selected item
				new AlertDialog.Builder(Dashboard.this)
						.setTitle("Delete?")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("You sure?")
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										LayoutInflater inflater = getLayoutInflater();
										View layout = inflater
												.inflate(
														R.layout.toast,
														(ViewGroup) findViewById(R.id.toast_layout_root));
										TextView text = (TextView) layout
												.findViewById(R.id.text);
										text.setText("Deleted");
										Toast toast = new Toast(
												getApplicationContext());
										toast.setGravity(
												Gravity.CENTER_VERTICAL, 0, 0);
										toast.setDuration(Toast.LENGTH_SHORT);
										toast.setView(layout);
										toast.show();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();
				break;
			}
			mode.finish();
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}
	}

	private final class PublishedActionView implements ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add("Favourite").setIcon(android.R.drawable.btn_star)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// Add to favoutites
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast,
					(ViewGroup) findViewById(R.id.toast_layout_root));
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("New favourite added");
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);
			toast.show();
			mode.finish();
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}
	}

	private final class FriendsActionView implements ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add(1, 234559, 1, "View")
					.setIcon(android.R.drawable.ic_menu_view)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.add(2, 988876, 2, "Message")
					.setIcon(android.R.drawable.ic_menu_send)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// Get data about the friend selected
			String id, friendName, imageUrl;
			Bundle b;

			switch (item.getItemId()) {
			case 234559:
				// String friendId =
				// friendIdArray.get(friendActionItemPosition);
				// String friendName = friendNameListArray
				// .get(friendActionItemPosition);
				// String friendStatus = friendListStatus
				// .get(friendActionItemPosition);
				// String imageUrl = friendListProfilePictureUrl
				// .get(friendActionItemPosition);

				id = "cuuiydvcxd";
				friendName = "Frodo Baggins";
				imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";

				i = new Intent(getApplicationContext(), FriendProfile.class);
				b = new Bundle();
				b.putString("id", id);
				b.putString("friendName", friendName);
				b.putString("profilePicture", imageUrl);
				i.putExtras(b);
				startActivity(i);
				break;
			case 988876:
				// String friendId =
				// friendIdArray.get(friendActionItemPosition);
				// String friendName = friendNameListArray
				// .get(friendActionItemPosition);
				// String friendStatus = friendListStatus
				// .get(friendActionItemPosition);
				// String imageUrl = friendListProfilePictureUrl
				// .get(friendActionItemPosition);

				id = "cuuiydvcxd";
				friendName = "Frodo Baggins";
				imageUrl = "http://www.red967fm.com/wp-content/uploads/2014/02/profileholder-150x150.gif";

				i = new Intent(getApplicationContext(), MessagingPlatform.class);
				b = new Bundle();
				b.putString("id", id);
				b.putString("friendName", friendName);
				b.putString("profilePicture", imageUrl);
				i.putExtras(b);
				startActivity(i);
				break;
			}
			mode.finish();
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}
	}

	private final class FavouritesActionView implements ActionMode.Callback {
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add("Delete").setIcon(android.R.drawable.ic_menu_delete)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// Delete selected item
			new AlertDialog.Builder(Dashboard.this)
					.setTitle("Delete?")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setMessage("You sure?")
					.setPositiveButton("Delete",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									LayoutInflater inflater = getLayoutInflater();
									View layout = inflater
											.inflate(
													R.layout.toast,
													(ViewGroup) findViewById(R.id.toast_layout_root));
									TextView text = (TextView) layout
											.findViewById(R.id.text);
									text.setText("Deleted");
									Toast toast = new Toast(
											getApplicationContext());
									toast.setGravity(Gravity.CENTER_VERTICAL,
											0, 0);
									toast.setDuration(Toast.LENGTH_SHORT);
									toast.setView(layout);
									toast.show();
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).show();
			mode.finish();
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
		}
	}

}

class loadingImage extends AsyncTask<String, Void, String> {
	ArrayList<String> friendNames;
	ArrayList<Bitmap> bmp = new ArrayList<Bitmap>();
	Context context;
	ListView listView;
	Intent i;
	int friendActionItemPosition;
	ActionMode mMode;

	public loadingImage(Context mcontext, ListView list, Intent in,
			int friendItemPosition, ActionMode mode) {
		friendActionItemPosition = friendItemPosition;
		mMode = mode;
		context = mcontext;
		listView = list;
		i = in;
	}

	@Override
	protected String doInBackground(String... params) {
		friendNames = RosterRetrieval.retrieve();
		bmp = RosterRetrieval.pictures();
		return null;
	}

	@Override
	protected void onPostExecute(String result) {

		super.onPostExecute(result);

		ArrayList<FriendItem> friendList = new ArrayList<FriendItem>();

		for (int i = 0; i < friendNames.size(); i++) {
			friendList.add(new FriendItem(friendNames.get(i), "online", bmp
					.get(i)));
		}
		// friendNames.clear();
		// bmp.clear();
		FriendItemAdapter mAdapter = new FriendItemAdapter(context,
				R.layout.icon_list_item, friendList);

		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				// String friendId = friendListId.get(arg2);

				i = new Intent(context, FriendCreationView.class);
				Bundle b = new Bundle();
				b.putString("friendId", RosterRetrieval.jids.get(arg2));
				i.putExtras(b);
				context.startActivity(i);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				friendActionItemPosition = arg2;
				// mMode = startActionMode(new FriendsActionView());
				return true;
			}
		});

	}

}