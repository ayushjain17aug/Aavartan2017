package com.technocracy.app.aavartan.Event.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Event.EventCallback;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.Model.Data.EventData;

import java.util.ArrayList;
import java.util.List;

public class MockEventProvider implements EventProvider {
    private EventData mockData;


    public void getEvents(String eventSetId, final EventCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventData());
            }
        }, 500);
    }

    private EventData getMockEventData() {
        List<Event> eventList = new ArrayList<>();
        String type ="fun",image_url="urls.com";
        eventList.add(new Event("1", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("2", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("3", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("4", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("5", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("6", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("7", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("8", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("9", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("10", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("11", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("12", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("13", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("14", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("15", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("16", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        eventList.add(new Event("17", type, "Horoscope Aavartan17", "This is the best event!!", "7th Oct 2017", "9:30 a.m.", "E-Hall", image_url, "", ""));
        mockData = new EventData(true, "Success!", eventList);
        return mockData;
    }

    @Override
    public void getFunEvent(EventCallback callback) {

    }

    @Override
    public void getManagerialEvent(EventCallback callback) {

    }

    @Override
    public void getTechEvent(EventCallback callback) {

    }

    @Override
    public void getRoboEvent(EventCallback callback) {

    }
}