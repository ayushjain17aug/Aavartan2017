package com.technocracy.app.aavartan.Schedule.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Schedule.EventByIdCallback;
import com.technocracy.app.aavartan.Schedule.Model.Data.Schedule;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

import java.util.ArrayList;
import java.util.List;

public class MockScheduleProvider implements ScheduleProvider {

    private ScheduleData mockScheduleData;
    private Event event;
    @Override
    public void getSchedule(final String day, final ScheduleCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockScheduleData(day));
            }
        }, 500);
    }

    @Override
    public void getEventById(final String eventId, final EventByIdCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockEventByIdData(eventId));
            }
        }, 500);
    }

    private Event getMockEventByIdData(String eventId) {
        event = new Event("1", "Robo", "robo",
                "The best Event Ever!", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png",
                "7-th Oct", "7:30pm", "F-40");
        return event;
    }

    public ScheduleData getMockScheduleData(String day) {
        List<Schedule> list = new ArrayList<>();
        if (day.equals("1")) {
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule("1", "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));

        } else {
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule("1", "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
        }
        mockScheduleData = new ScheduleData(true, "Success", list);
        return mockScheduleData;
    }
}
