package net.devatom.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.devatom.model.Saison;
import net.devatom.mynetflix.R;

import java.util.ArrayList;

public class CustomSaisonAdapter extends ArrayAdapter<Saison> {

	public CustomSaisonAdapter(Context context, ArrayList<Saison> saisons) {
		super(context, 0, saisons);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		//Vérifier la réutilisation d'une vue sinon la créer
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_saison, parent, false);
		}

		//Récupère les données de l'élément à la position donnée
		final Saison saison = getItem(position);

		//Liaison avec les données de la vue
		TextView tvNumero = (TextView) convertView.findViewById(R.id.tvNumero);
		TextView tvAnnee = (TextView) convertView.findViewById(R.id.tvAnnee);

		//Remplir les données dans le modèle de la vue en utilisant l'objet
		tvAnnee.setText(new StringBuilder().append(" (").append(saison.getAnneeDiffusion()).append(")"));
		tvNumero.setText(new StringBuilder("Saison ").append(saison.getNumero()));

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), saison.toString(), Toast.LENGTH_SHORT).show();
			}
		});

		//Renvoyer la vue complétée pour la générer à l'écran
		return convertView;
	}
}
