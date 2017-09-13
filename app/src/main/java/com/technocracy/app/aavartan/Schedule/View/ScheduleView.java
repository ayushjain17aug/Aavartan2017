package com.technocracy.app.aavartan.Schedule.View;

import com.technocracy.app.aavartan.Schedule.Model.Data.Schedule;

import java.util.List;

/**
 * Created by Abhi on 01-Sep-17.
 */

public interface ScheduleView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showScheduleFromDatabase();

    void showSchedule(List<Schedule> schedule);
}
