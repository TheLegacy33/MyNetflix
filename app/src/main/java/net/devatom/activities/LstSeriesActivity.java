package net.devatom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import net.devatom.App;
import net.devatom.model.Serie;
import net.devatom.mynetflix.R;
import net.devatom.service.CustomSerieAdapter;
import net.devatom.service.MyNetflixAsyncTask;

import org.json.JSONArray;

import java.util.Comparator;

public class LstSeriesActivity extends Activity {

	private String params = "?data=series";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lst_series);
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateList();
	}

	public void populateList() {
		new SerieAsyncTask().execute(App.getBaseUrl()+params);
	}

	private class SerieAsyncTask extends MyNetflixAsyncTask {
		private TextView tvSize;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			tvSize = findViewById(R.id.tvSize);
			tvSize.setText("Calculating ...");
		}

		@Override
		protected void onPostExecute(JSONArray response) {

			CustomSerieAdapter ad = new CustomSerieAdapter(App.getContext(), Serie.getFromJson(response));

			//Je trie par nom de série
			ad.sort(new Comparator<Serie>() {
				@Override
				public int compare(Serie o1, Serie o2) {
					return o1.getNom().compareTo(o2.getNom());
				}
			});

			//J'affecte l'adapter au listView
			ListView lstV = (ListView) findViewById(R.id.lstSaisons);
			lstV.setAdapter(ad);

			//J'update la textview
			tvSize.setText("nb : " + ad.getCount());
		}
	}
}