package com.technocracy.app.aavartan.Schedule.Model;

import com.technocracy.app.aavartan.Schedule.EventByIdCallback;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

public interface ScheduleProvider {
    void getSchedule(String day, ScheduleCallback callback);

    void getEventById(String eventId, EventByIdCallback callback);
}
