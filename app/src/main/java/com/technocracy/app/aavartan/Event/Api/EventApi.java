package com.technocracy.app.aavartan.Event.Api;


import com.technocracy.app.aavartan.Event.Model.Data.EventData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventApi {
    @GET(App.FUN_EVENT)
    Call<EventData> getFunEvent();

    @GET(App.MANAGERIAL_EVENT)
    Call<EventData> getManagerialEvent();

    @GET(App.TECHNICAL_EVENT)
    Call<EventData> getTechEvent();

    @GET(App.ROBOTICS_EVENT)
    Call<EventData> getRoboEvent();
}
