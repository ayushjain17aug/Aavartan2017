package com.technocracy.app.aavartan.Event.Model;

import com.technocracy.app.aavartan.Event.EventCallback;

public interface EventProvider {

    void getEvents(String eventSetId, EventCallback callback);
}
