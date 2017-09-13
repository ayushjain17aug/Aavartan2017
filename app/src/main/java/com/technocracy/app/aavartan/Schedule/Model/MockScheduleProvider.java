package com.technocracy.app.aavartan.Schedule.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Schedule.Model.Data.Schedule;
import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.Schedule.ScheduleCallback;

import java.util.ArrayList;
import java.util.List;

public class MockScheduleProvider implements ScheduleProvider {

    private ScheduleData mockScheduleData;

    @Override
    public void getSchedule(final String day, final ScheduleCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockScheduleData(day));
            }
        }, 500);
    }

    public ScheduleData getMockScheduleData(String day) {
        List<Schedule> list = new ArrayList<>();
        if (day.equals("1")) {
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));
            list.add(new Schedule(1, "Horoscope", "9:30 - 11:45 am", "F-42", "https://pbs.twimg.com/profile_images/511776727610052608/-WFs_3Wu_400x400.png"));

        } else {
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
            list.add(new Schedule(1, "Mario", "2:30-5:15 pm", "Ground me hoga mere bhai", "http://knowafest.com/files/uploads/aavartan.-2017032813.jpg"));
        }
        mockScheduleData = new ScheduleData(true, "Success", list);
        return mockScheduleData;
    }
}
