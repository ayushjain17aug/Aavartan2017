
package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.Attraction;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;

import java.util.ArrayList;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    public Context context;
    ArrayList<Attraction> item;

    public AttractionAdapter(Context context, ArrayList<Attraction> item) {
        inflater = LayoutInflater.from(context);
        this.item = item;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_attraction, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Attraction i = item.get(position);
        App.showProgressBar(holder.progressBar);
        Picasso.with(context).load(i.imgUrl).placeholder(R.drawable.aavartan_logo).into(holder.img, new com.squareup.picasso.Callback() {

            @Override
            public void onSuccess() {
                App.hideProgressBar(holder.progressBar);
            }

            @Override
            public void onError() {
                App.hideProgressBar(holder.progressBar);
                Toast.makeText(AppController.getInstance().getApplicationContext(), "Error Loading Image!", Toast.LENGTH_LONG).show();
            }
        });
        holder.name.setText(i.name);
        holder.des.setText(i.description);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name, des;
        public final ImageView img;
        public final ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.event_name);
            img = (ImageView) itemView.findViewById(R.id.attractn_img);
            des = (TextView) itemView.findViewById(R.id.descrptn);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}