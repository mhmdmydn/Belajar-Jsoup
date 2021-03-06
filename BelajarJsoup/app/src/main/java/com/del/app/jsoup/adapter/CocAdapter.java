package com.del.app.jsoup.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.del.app.jsoup.R;
import java.util.ArrayList;
import com.del.app.jsoup.model.CocModel;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.zip.Inflater;
import android.view.LayoutInflater;
import com.bumptech.glide.Glide;

public class CocAdapter extends RecyclerView.Adapter<CocAdapter.ViewHolder> {
	
	
    private Context context;
	private ArrayList<CocModel> list = new ArrayList<>();
	
	public CocAdapter(Context context, ArrayList<CocModel> list){
		this.context = context;
		this.list = list;
	}
	

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int itemType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final CocModel cm = list.get(position);
		
		Glide.with(context).load(cm.getImageUrl()).into(holder.imgThumb);
		holder.tvTitle.setText(cm.getTitle());
		
	}

	@Override
	public int getItemCount() {
		return list.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder{
		
		ImageView imgThumb;
		TextView tvTitle;
		public ViewHolder(View itemView){
			super(itemView);
			imgThumb = itemView.findViewById(R.id.image_thumb);
			tvTitle = itemView.findViewById(R.id.text_title);
			
		}
	}
}
