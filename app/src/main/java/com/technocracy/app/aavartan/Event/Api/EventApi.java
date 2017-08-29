package com.technocracy.app.aavartan.Event.Api;


import com.technocracy.app.aavartan.Event.Model.Data.EventData;
import com.technocracy.app.aavartan.helper.App;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface EventApi {
    @GET(App.EVENT1)
    Call<EventData> getEvents(@Field("event_set_id") String eventSetId);
}
