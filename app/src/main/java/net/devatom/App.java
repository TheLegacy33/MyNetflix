package net.devatom;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import net.devatom.mynetflix.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App extends Application{

	private static Context mContext;
	private static String mAppName;
	private static final String baseUrl = "https://www.devatom.net/API/api.php";

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		mAppName = getContext().getResources().getString(R.string.app_name);
	}

	public static Context getContext(){
		return mContext;
	}

	public static String getAppName(){
		return mAppName;
	}

	public static String getBaseUrl() { return baseUrl; }

	public static String parseFromUrl(String remoteApi){
		String response = "";
		HttpURLConnection urlConnection = null;
		try {

			URL url = new URL(remoteApi);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream iStream = urlConnection.getInputStream();
			InputStreamReader iStreamReader = new InputStreamReader(iStream);

			StringBuilder strB = new StringBuilder("");

			int data = iStreamReader.read();
			while (data != -1){
				strB.append((char) data);
				data = iStreamReader.read();
			}

			response = strB.toString();
			Log.d(App.getAppName(), response);

			if (!isValidJson(response)){
				response = "[]";
			}
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}
		return null;
	}

	public static boolean isValidJson(String response) {
		boolean retVal = true;
		try{
			JSONArray test = new JSONArray(response);
		}catch (JSONException ex){
			retVal = false;
		}finally {
			return retVal;
		}
	}


}