package net.devatom.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Saison {
	private int id, numero, anneeDiffusion;

	public Saison(int id, int numero, int anneeDiffusion){
		this.setId(id);
		this.setNumero(numero);
		this.setAnneeDiffusion(anneeDiffusion);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAnneeDiffusion() {
		return anneeDiffusion;
	}

	public void setAnneeDiffusion(int anneeDiffusion) {
		this.anneeDiffusion = anneeDiffusion;
	}

	public static ArrayList<Saison> getFromJson(JSONArray src) {
		ArrayList<Saison> saisons = new ArrayList<Saison>();

		if (src != null) {
			for (int i = 0; i < src.length(); i++) {
				try {
					saisons.add(
							new Saison(
									src.getJSONObject(i).getInt("id"),
									src.getJSONObject(i).getInt("numero"),
									src.getJSONObject(i).getInt("annee_diffusion")
							)
					);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		return saisons;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}
}
