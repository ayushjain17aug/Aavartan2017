package com.technocracy.app.aavartan.helper;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.R;


/**
 * Created by MOHIT on 18-Sep-16.
 */
public class App {
    //EndPoints
    public static final String REGISTER_FCM_GENERAL_TOKEN_URL = "http://aavartan.org/appApi_2Ksixteen/saveGeneralFCMToken.php";
    public static final String REGISTER_FCM_TOKEN_URL = "http://aavartan.org/appApi_2Ksixteen/registerFCMToken.php";

    public static final String REGISTER_USER_URL = "http://aavartan.org/appApi_2Ksixteen/register.php";
    public static final String LOGIN_URL = "http://aavartan.org/appApi_2Ksixteen/login.php";
    public static final String FORGOT_PASSWORD_URL = "http://aavartan.org/appApi_2Ksixteen/forgetPassword.php";

    public static final String MY_EVENTS_URL = "http://aavartan.org/appApi_2Ksixteen/myEvents.php";
    public static final String REGISTER_EVENT = "http://aavartan.org/appApi_2Ksixteen/registerEvent.php";
    public static final String GET_NOTIFICATION = "http://aavartan.org/appApi_2Ksixteen/getNotifications.php";

    public static final String CONTACTS_URL = "http://aavartan.org/appApi_2Ksixteen/contacts.php";
    public static final String GALLERY_URL = "http://aavartan.org/appApi_2Ksixteen/gallery.php";
    public static final String TECHNICAL_EVENTS_URL = "http://aavartan.org/appApi_2Ksixteen/TechnicalEvents.php";
    public static final String FUN_EVENTS_URL = "http://aavartan.org/appApi_2Ksixteen/FunEvents.php";
    public static final String MANAGERIAL_EVENTS_URL = "http://aavartan.org/appApi_2Ksixteen/ManagerialEvents.php";
    public static final String ROBOTICS_URL = "http://aavartan.org/appApi_2Ksixteen/Robotics.php";
    public static final String ATTRACTIONS_URL = "http://aavartan.org/appApi_2Ksixteen/Attraction.php";

    public static final String SCHEDULE_DAY1_URL = "http://aavartan.org/appApi_2Ksixteen/3vScheduleDay1.php";
    public static final String SCHEDULE_DAY2_URL = "http://aavartan.org/appApi_2Ksixteen/3vScheduleDay2.php";

    //public static final String SCHEDULE_DAY1_URL = "http://aavartan.org/appApi_2Ksixteen/ScheduleDay1.php";
    //public static final String SCHEDULE_DAY2_URL = "http://aavartan.org/appApi_2Ksixteen/ScheduleDay2.php";

    public static final String INITIATIVES_IMG_URL = "http://www.aavartan.org/images/52.JPG";
    public static final String SPONSORS_URL = "http://aavartan.org/appApi_2Ksixteen/3vSponsors.php";


    //Vigyaan
    public static final String ArchiPDF="http://aavartan.org/ps/archi.pdf";
    public static final String BioMedPDF = "http://aavartan.org/ps/biomed.pdf";
    public static final String BioTechPDF = "http://aavartan.org/ps/biotech.pdf";
    public static final String ChemPDF = "http://www.aavartan.org/ps/chemical.pdf";
    public static final String CivilPDF = "http://www.aavartan.org/ps/civil.pdf";
    public static final String CSEPDF = "http://www.aavartan.org/ps/cse.pdf";
    public static final String ElecPDF = "http://www.aavartan.org/ps/electrical.pdf";
    public static final String ElexPDF = "http://www.aavartan.org/ps/electronics.pdf";
    public static final String ITPDF = "http://www.aavartan.org/ps/it.pdf";
    public static final String MechPDF = "http://www.aavartan.org/ps/mechanical.pdf";
    public static final String MetaPDF = "http://www.aavartan.org/ps/meta.pdf";
    public static final String MiningPDF = "http://www.aavartan.org/ps/mining.pdf";
    public static final String MCAPDF = "http://www.aavartan.org/ps/mca.pdf";
    public static final String EcellPDF = "http://www.aavartan.org/ps/e-cell.pdf";
    public static final String GoGreenPDF = "http://www.aavartan.org/ps/go_green.pdf";

    public static String LinkPDF;
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final int SWIPE_REFRESH_COLORS[] = {R.color.colorPrimary, R.color.colorAccent};

    public static float getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static void showProgressBar(ProgressBar progressBar) {
        if (progressBar.getVisibility() != View.VISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    public static void hideProgressBar(ProgressBar progressBar) {
        if (progressBar.getVisibility() != View.GONE
                || progressBar.getVisibility() != View.INVISIBLE)
            progressBar.setVisibility(View.GONE);
    }

}
