package com.del.app.jsoup.helper;
import android.os.AsyncTask;
import java.util.ArrayList;
import android.content.Context;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import java.io.IOException;
import android.util.Log;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import com.del.app.jsoup.model.CocModel;

public class ScrapingTask extends AsyncTask<String, Void, ArrayList<CocModel>> {
	
	private Context con;
	private Listener listen;
	private ArrayList<CocModel> list = new ArrayList<>();
	private CocModel  coc;
	private Document doc;
	
	
	public ScrapingTask(Context con, Listener listen){
		this.con = con;
		this.listen = listen;
	}
	
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("Task :", "Running..");
		
		
	}
	
	@Override
	protected ArrayList<CocModel> doInBackground(String... url) {
		try {
			doc = Jsoup.connect(url[0]).get();
			Log.d("Task", doc.title());
			
		} catch (IOException e) {
			
		}
		Elements els = doc.select("div.select_plans");
		
		for (Element el : els.select("div.base_image")) {
			coc = new CocModel();
			coc.setTitle(el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("alt"));
			coc.setImageUrl("https://clashofclans-layouts.com/" + el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src"));
			coc.setNextContent("https://clashofclans-layouts.com/plans/th_3/" + el.select("a[href]").attr("href"));
			
			Log.d("COC", "Url Next Page" + el.select("a[href]").attr("href"));
			Log.d("COC", "Image Source: " + el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src"));
			Log.d("COC: ", "Image Height: "+ el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("height"));
			Log.d("COC: ", "Image Width: " + el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("width"));
			Log.d("COC", "Image Alt Text: "  + el.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("alt"));
			list.add(coc);
		}
		
		return list;
	}

	@Override
	protected void onProgressUpdate(Void[] values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(ArrayList<CocModel> result) {
		super.onPostExecute(result);
		listen.onResultArray(result);
		Log.d("Task : ", " done..." + result);
	}
	
	
	public interface Listener{
		public void onResultArray(ArrayList<CocModel> list);
		public void onResultString(String result);
		public void onError(String error);
	}
}
