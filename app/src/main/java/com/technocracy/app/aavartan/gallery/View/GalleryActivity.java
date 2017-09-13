package com.technocracy.app.aavartan.gallery.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.gallery.Model.MockGalleryProvider;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import java.util.ArrayList;
import java.util.List;

import com.technocracy.app.aavartan.gallery.Model.Data.Image;
import com.technocracy.app.aavartan.gallery.Presenter.GalleryPresenter;
import com.technocracy.app.aavartan.gallery.Presenter.GalleryPresenterImpl;
import com.technocracy.app.aavartan.helper.PicassoImageLoader;
import com.technocracy.app.aavartan.activity.MainActivity;
import com.technocracy.app.aavartan.R;

public class GalleryActivity extends AppCompatActivity implements GalleryView {

    private ProgressBar progressBar;
    private GalleryPresenter presenter;
    private ScrollGalleryView scrollGalleryView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
     //   presenter = new GalleryPresenterImpl(this, new RetrofitGalleryProvider(), this);
           presenter = new GalleryPresenterImpl(this,new MockGalleryProvider(),this);
        presenter.getImages();

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
//        setupWindowAnimations();
    }

    /*private void setupWindowAnimations() {
        try {
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setEnterTransition(explode);
            getWindow().setExitTransition(explode);
        } catch (Exception e) {
            showMessage("Some Error!!");
        }
    }*/
    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.gallery_rel_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    public void loadGallery(List<Image> imageList) {
        List<MediaInfo> infos = new ArrayList<>(imageList.size());
        for (Image image : imageList)
            infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(image.getImg_url())));

        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(infos);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}