package com.del.app.jsoup.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.del.app.jsoup.R;
import com.del.app.jsoup.helper.ScrapingTask;
import java.util.ArrayList;
import android.widget.Toast;
import com.del.app.jsoup.model.CocModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.del.app.jsoup.adapter.CocAdapter;


public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
	private static final String URL = "https://clashofclans-layouts.com/plans/th_3/";
	
	private RecyclerView recyclerView;
	private CocAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        initLogic();
        initListener();
		new ScrapingTask(MainActivity.this, new ScrapingTask.Listener(){

				@Override
				public void onResultArray(ArrayList<CocModel> list) {
					recyclerView.setHasFixedSize(true);
					//recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
					recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
					adapter = new CocAdapter(MainActivity.this, list);
					recyclerView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}

				@Override
				public void onResultString(String result) {
					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				}

				@Override
				public void onError(String error) {
				}
			}).execute(URL);
		
   } 
   
    @Override
    public void setupView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
		recyclerView = (RecyclerView)findViewById(R.id.recycler);
    }

    @Override
    public void initLogic() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
		
		
    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View _v) {
                    onBackPressed();
                }
         });
    }
}
