package com.technocracy.app.aavartan.Attraction.Model;

import com.technocracy.app.aavartan.Attraction.AttractionCallback;
import com.technocracy.app.aavartan.Attraction.Model.Data.Attraction;
import com.technocracy.app.aavartan.Attraction.Model.Data.AttractionData;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MockAttractionProvider implements AttractionProvider{

    private AttractionData mockData;

    @Override
    public void getAttractions(final AttractionCallback callback) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        },500);
    }

    public AttractionData getMockData() {
        List<Attraction> list = new ArrayList<>();
        list.add(new Attraction(1,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        list.add(new Attraction(2,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        list.add(new Attraction(3,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        list.add(new Attraction(4,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        list.add(new Attraction(5,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        list.add(new Attraction(6,"Bike Stunts","Bikers to do stunts","http://www.aavartan.org/img/thumb-04.jpg"));
        mockData = new AttractionData(true,"Success",list);
        return mockData;
    }
}
