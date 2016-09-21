package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 19-09-2016.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {


    private static int COUNT;
    private final Context mContext;
    private final List<Integer> mItems;
    private int mCurrentItemId = 0;
    String url1[], url2[], url3[];

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ProgressBar pBAr;

        public SimpleViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.spons);
            pBAr = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    public SimpleAdapter(Context context, String url1[], String[] url2, String url3[]) {
        mContext = context;
        COUNT = url1.length + url2.length + url3.length;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        mItems = new ArrayList<Integer>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {
        String[] url = null;
        if (position < url1.length)
            url = url1;
        else if (position >= url1.length && position < url1.length + url2.length) {
            url = url2;
            position = position - url1.length;
        } else if ((position >= (url1.length + url2.length)) && (position < (url1.length + url2.length + url3.length))) {
            url = url3;
            position = position - url1.length - url2.length;
        }
        App.showProgressBar(holder.pBAr);

        int width = (int) App.getScreenWidth(mContext);
        holder.imageView.getLayoutParams().height = width/2;
        holder.imageView.getLayoutParams().width = width;
        holder.imageView.requestLayout();

        Picasso.with(mContext).load(url[position]).placeholder(R.drawable.aavartan_logo).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                App.hideProgressBar(holder.pBAr);
            }

            @Override
            public void onError() {
                App.hideProgressBar(holder.pBAr);
            }
        });
    }


    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}