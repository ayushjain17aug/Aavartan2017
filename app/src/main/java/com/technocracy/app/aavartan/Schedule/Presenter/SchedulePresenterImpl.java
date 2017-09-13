package com.technocracy.app.aavartan.Schedule.Presenter;

import android.content.Context;

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
}