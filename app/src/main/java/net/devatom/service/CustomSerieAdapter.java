package net.devatom.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.devatom.App;
import net.devatom.activities.LstSaisonsActivity;
import net.devatom.model.Serie;
import net.devatom.mynetflix.R;

import java.util.ArrayList;

public class CustomSerieAdapter extends ArrayAdapter<Serie> {

	public CustomSerieAdapter(Context context, ArrayList<Serie> series) {
		super(context, 0, series);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		//Vérifier la réutilisation d'une vue sinon la créer
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_serie, parent, false);
		}

		//Récupère les données de l'élément à la position donnée
		final Serie serie = getItem(position);

		//Liaison avec les données de la vue
		TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
		TextView tvNomOriginal = (TextView) convertView.findViewById(R.id.tvNomOriginal);
		TextView tvAnnee = (TextView) convertView.findViewById(R.id.tvAnnee);

		//Remplir les données dans le modèle de la vue en utilisant l'objet
		tvNom.setText(serie.getNom());
		tvNomOriginal.setText(serie.getNomOriginal());
		tvAnnee.setText(new StringBuilder().append(" (").append(serie.getAnneeParution()).append(")"));

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(App.getContext(), LstSaisonsActivity.class);
				it.putExtra("idSerie", serie.getId());
				it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				App.getContext().startActivity(it);
			}
		});

		//Renvoyer la vue complétée pour la générer à l'écran
		return convertView;
	}
}
