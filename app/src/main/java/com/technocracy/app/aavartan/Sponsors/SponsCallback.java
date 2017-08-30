package com.technocracy.app.aavartan.Sponsors;

import com.technocracy.app.aavartan.Sponsors.Model.Data.SponsData;

public interface SponsorsCallback {
    void onSuccess(SponsData body);

    void onFailure();
}
