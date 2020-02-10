package net.devatom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import net.devatom.App;
import net.devatom.model.Saison;
import net.devatom.model.Serie;
import net.devatom.mynetflix.R;
import net.devatom.service.CustomSaisonAdapter;
import net.devatom.service.CustomSerieAdapter;
import net.devatom.service.MyNetflixAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Comparator;

public class LstSaisonsActivity extends Activity {
	private String params = "?data=saisons";
	private int idSerie = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lst_saisons);
	}

	@Override
	protected void onResume() {
		super.onResume();
		idSerie = getIntent().getIntExtra("idSerie", 0);

		new SerieAsyncTask().execute(App.getBaseUrl()+"?data=series&id=" + idSerie);

		populateList(idSerie);
	}
	public void populateList(int idSerie) {

		params += "&idserie=" + idSerie;
		new SaisonAsyncTask().execute(App.getBaseUrl()+params);
	}

	private class SaisonAsyncTask extends MyNetflixAsyncTask {

		@Override
		protected void onPostExecute(JSONArray response) {

			CustomSaisonAdapter ad = new CustomSaisonAdapter(App.getContext(), Saison.getFromJson(response));

			//Je trie par numéro de saison
			ad.sort(new Comparator<Saison>() {
				@Override
				public int compare(Saison o1, Saison o2) {
					if (o1.getNumero() < o2.getNumero()){
						return -1;
					}else if (o1.getNumero() > o2.getNumero()){
						return 1;
					}else {
						return 0;
					}
				}
			});

			//J'affecte l'adapter au listView
			ListView lstV = (ListView) findViewById(R.id.lstSaisons);
			lstV.setAdapter(ad);

			//J'update la textview
			TextView tvSize = findViewById(R.id.tvSize);
			tvSize.setText("Calculating ...");
			tvSize.setText("nb : " + ad.getCount());
		}
	}

	private class SerieAsyncTask extends MyNetflixAsyncTask {
		private TextView tvTitre;

		@Override
		protected void onPostExecute(JSONArray response) {

			Serie serie = Serie.getOneFromJson(response, idSerie);

			//J'update la textview
			tvTitre = findViewById(R.id.tvTitreSerie);
			tvTitre.setText(serie.getNom());
		}
	}
}
