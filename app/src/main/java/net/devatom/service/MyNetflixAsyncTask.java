package net.devatom.service;

import android.os.AsyncTask;

import net.devatom.App;

import org.json.JSONArray;
import org.json.JSONException;

public abstract class MyNetflixAsyncTask extends AsyncTask<String, Void, JSONArray> {
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected JSONArray doInBackground(String... urls) {
		try {
			return new JSONArray(App.parseFromUrl(urls[0]));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(JSONArray jsonArray) {
		super.onPostExecute(jsonArray);
	}
}
