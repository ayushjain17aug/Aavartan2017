package com.technocracy.app.aavartan.Schedule.Api;

import com.technocracy.app.aavartan.Schedule.Model.Data.ScheduleData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ScheduleApi {
    @GET(App.SCHEDULE)
    Call<ScheduleData> getSchedule(@Field("day") String day);
}