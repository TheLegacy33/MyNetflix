package net.devatom.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Serie {

	private int id, anneeParution;
	private String nom, nomOriginal;

	public Serie(int id, String nom, String nomOriginal, int anneeParution){
		this.setId(id);
		this.setNom(nom);
		this.setNomOriginal(nomOriginal);
		this.setAnneeParution(anneeParution);
	}


	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getAnneeParution() {
		return anneeParution;
	}

	public void setAnneeParution(int anneeParution) {
		this.anneeParution = anneeParution;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomOriginal() {
		return nomOriginal;
	}

	public void setNomOriginal(String nomOriginal) {
		this.nomOriginal = nomOriginal;
	}

	public static Serie getOneFromJson(JSONArray src, int idSerie) {
		Serie serie = null;

		for (int i = 0; i < src.length(); i++) {
			try {
				if (src.getJSONObject(i).getInt("id") == idSerie){
					serie = new Serie(
							src.getJSONObject(i).getInt("id"),
							src.getJSONObject(i).getString("nom"),
							src.getJSONObject(i).getString("nomoriginal"),
							src.getJSONObject(i).getInt("anneeparution")
					);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return serie;
	}

	public static ArrayList<Serie> getFromJson(JSONArray src) {
		ArrayList<Serie> series = new ArrayList<Serie>();

		for (int i = 0; i < src.length(); i++) {
			try {
				series.add(
						new Serie(
								src.getJSONObject(i).getInt("id"),
								src.getJSONObject(i).getString("nom"),
								src.getJSONObject(i).getString("nomoriginal"),
								src.getJSONObject(i).getInt("anneeparution")
						)
				);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return series;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}
}