package com.technocracy.app.aavartan.Event.Model;

import com.technocracy.app.aavartan.Event.EventCallback;

public interface EventProvider {

    void getFunEvent(EventCallback callback);

    void getManagerialEvent(EventCallback callback);

    void getTechEvent(EventCallback callback);

    void getRoboEvent(EventCallback callback);
}
