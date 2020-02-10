package net.devatom.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.devatom.App;
import net.devatom.mynetflix.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private Button btViewListe, btQuit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btViewListe = (Button) findViewById(R.id.btListSeries);
		btQuit = (Button) findViewById(R.id.btQuit);

		btViewListe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(), LstSeriesActivity.class);
				startActivity(it);
			}
		});

		btQuit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		try {
			/**
			 * Récupératin des informations de l'intent entrant
			 * Sous la forme :
			 *          mynetflix://mynetflix.devatom.net/series        -> Pour accéder à la liste des séries
			 *          mynetflix://mynetflix.devatom.net/saisons/6     -> Poyur obtenir la liste des épisodes de la série dont l'id vaut 6
			 *
			 *  Il faut tester en accédant dans le terminal mobile à la page : http://www.devatom.net/API/test_scheme.html
			 */
			Log.d(App.getAppName(), getIntent().getDataString());

			Uri data = getIntent().getData();
			String scheme = data.getScheme();
			String host = data.getHost();
			List<String> params = data.getPathSegments();
			String objet = params.get(0);
			int id = 0;
			if (params.size() == 2) {
				String val = params.get(1);
				id =  Integer.parseInt(val);
			}

			Intent it = null;
			if (objet.equals("series")){
				it = new Intent(App.getContext(), LstSeriesActivity.class);
			}else if (objet.equals("saisons") && id > 0){
				it = new Intent(App.getContext(), LstSaisonsActivity.class);
				it.putExtra("idSerie", id);
			}else{
				Toast.makeText(App.getContext(), "Les informations fournies sont incorrectes !", Toast.LENGTH_LONG).show();
			}

			if (it != null){
				startActivity(it);
			}

		} catch (Exception ex){
			ex.printStackTrace();
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
	}
}
