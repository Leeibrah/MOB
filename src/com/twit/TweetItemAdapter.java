package com.twit;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.twit.TweetViewActivity.Tweet;

public class TweetItemAdapter extends ArrayAdapter<Tweet> {
	private ArrayList<Tweet> tweets;
	private Activity activity;
	public ImageManager imageManager;

	public TweetItemAdapter(Activity a, int textViewResourceId, ArrayList<Tweet> tweets) {
		super(a, textViewResourceId, tweets);
		this.tweets = tweets;
		activity = a;
		
		imageManager = 
			new ImageManager(activity.getApplicationContext(), textViewResourceId);
	}

	public static class ViewHolder{
		public TextView username;
		public TextView message;
		public ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if (v == null) {		
			LayoutInflater vi = 
				(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.listitem, null);
			holder = new ViewHolder();
			holder.username = (TextView) v.findViewById(R.id.username);
			holder.message = (TextView) v.findViewById(R.id.message);
			holder.image = (ImageView) v.findViewById(R.id.avatar);
			v.setTag(holder);
		}
		else
			holder=(ViewHolder)v.getTag();

		final Tweet tweet = tweets.get(position);
		if (tweet != null) {
			holder.username.setText(tweet.username);
			holder.message.setText(tweet.message);
			holder.image.setTag(tweet.image_url);
			imageManager.displayImage(tweet.image_url, activity, holder.image);
		}
		return v;
	}
}