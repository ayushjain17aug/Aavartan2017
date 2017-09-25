package com.technocracy.app.aavartan.Schedule.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Schedule.EventByIdCallback;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.Model.ScheduleProvider;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;
import com.technocracy.app.aavartan.Schedule.View.ScheduleView;

/**
 * Created by Abhi on 01-Sep-17.
 */

public class SchedulePresenterImpl implements SchedulePresenter {
    private ScheduleProvider provider;
    private ScheduleView view;
    private Context context;

    public SchedulePresenterImpl(ScheduleProvider provider, ScheduleView view, Context context) {
        this.provider = provider;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getSchedule(String day) {
        view.showProgressBar(true);
        provider.getSchedule(day, new ScheduleCallback() {
            @Override
            public void onSuccess(ScheduleData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showSchedule(body.getSchedule());
                } else {
                    view.showScheduleFromDatabase();
                    view.showMessage(body.getMessage());
                }
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showScheduleFromDatabase();
            }
        });
    }

    @Override
    public void getEventById(String eventId) {
        view.showProgressBar(true);
        provider.getEventById(eventId, new EventByIdCallback() {
            @Override
            public void onSuccess(Event body) {
                view.showProgressBar(false);
                view.showEventDetail(body);
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage("Internet Connection not present!");
            }
        });
    }
}