package com.technocracy.app.aavartan.Event.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.Event.EventCallback;
import com.technocracy.app.aavartan.Event.Model.Data.EventData;
import com.technocracy.app.aavartan.Event.Model.EventProvider;
import com.technocracy.app.aavartan.Event.View.EventView;
import com.technocracy.app.aavartan.R;

public class EventPresenterImpl implements EventPresenter {

    private EventView view;
    private EventProvider provider;
    private Context context;

    public EventPresenterImpl(EventView view, EventProvider provider, Context context) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }

    @Override
    public void getEvents(String eventSetId) {
        view.showProgressBar(true);
        provider.getEvents(eventSetId, new EventCallback() {
            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showEventsFromDatabase();
                view.showMessage(context.getResources().getString(R.string.Connection_Error));

            }

            @Override
            public void onSuccess(EventData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showEvents(body.getEventList());
                } else {
                    view.showEventsFromDatabase();
                    view.showMessage(body.getMessage());
                }
            }
        });
    }
}