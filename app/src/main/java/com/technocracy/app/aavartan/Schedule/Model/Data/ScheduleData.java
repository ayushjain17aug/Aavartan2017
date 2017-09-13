package com.technocracy.app.aavartan.Schedule.Model.Data;

import java.util.List;

/**
 * Created by Abhi on 01-Sep-17.
 */

public class ScheduleData {
    private boolean success;
    private String message;
    private List<Schedule> schedule;

    public ScheduleData(boolean success, String message, List<Schedule> schedule) {
        this.success = success;
        this.message = message;
        this.schedule = schedule;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }
}
