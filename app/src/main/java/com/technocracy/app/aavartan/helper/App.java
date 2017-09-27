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
    public static final String ATTRACTIONS_URL = "app.android.attractions";

    public static final String SCHEDULE_DAY1_URL = "http://aavartan.org/appApi_2Ksixteen/3vScheduleDay1.php";
    public static final String SCHEDULE_DAY2_URL = "http://aavartan.org/appApi_2Ksixteen/3vScheduleDay2.php";

    //public static final String SCHEDULE_DAY1_URL = "http://aavartan.org/appApi_2Ksixteen/ScheduleDay1.php";
    //public static final String SCHEDULE_DAY2_URL = "http://aavartan.org/appApi_2Ksixteen/ScheduleDay2.php";

    public static final String INITIATIVES_IMG_URL = "http://www.aavartan.org/images/52.JPG";


    //Vigyaan
    public static final String ArchiPDF = "https://aavartan.org/vigyaan-assets/archi.pdf";
    public static final String BioMedPDF = "https://aavartan.org/vigyaan-assets/biomedical.pdf";
    public static final String BioTechPDF = "https://aavartan.org/vigyaan-assets/biotech.PDF";
    public static final String ChemPDF = "https://aavartan.org/vigyaan-assets/chemical.pdf";
    public static final String CivilPDF = "https://aavartan.org/vigyaan-assets/civil.pdf";
    public static final String CSEPDF = "https://aavartan.org/vigyaan-assets/cse.pdf";
    public static final String ElecPDF = "https://aavartan.org/vigyaan-assets/electrical.pdf";
    public static final String ElexPDF = "https://aavartan.org/vigyaan-assets/etc.pdf";
    public static final String ITPDF = "https://aavartan.org/vigyaan-assets/it.pdf";
    public static final String MechPDF = "https://aavartan.org/vigyaan-assets/mechanical.pdf";
    public static final String MetaPDF = "https://aavartan.org/vigyaan-assets/meta.pdf";
    public static final String MiningPDF = "https://aavartan.org/vigyaan-assets/mining.pdf";
    public static final String MCAPDF = "https://aavartan.org/vigyaan-assets/mca.pdf";
    public static final String EcellPDF = "https://aavartan.org/vigyaan-assets/ecell.pdf";
    public static final String GoGreenPDF = "https://aavartan.org/vigyaan-assets/green.pdf";


    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final int SWIPE_REFRESH_COLORS[] = {R.color.colorPrimary, R.color.colorAccent};
    public static final String GALLERY = "app.android.gallery";
    public static final String EVENT = "app.android.event/";
    public static final String SCHEDULE1 = "app.android.schedule/7";
    public static final String CONTACT = "app.android.contacts";
    public static final String EVENT_BY_ID = "";//this is for schedule onClick item
    public static final String SCHEDULE2 = "app.android.schedule/7";
    public static final String FUN_EVENT = "app.android.events/1";
    public static final String MANAGERIAL_EVENT = "app.android.events/1";
    public static final String TECHNICAL_EVENT = "app.android.events/1";
    public static final String ROBOTICS_EVENT = "app.android.events/1";
    public static final String SPONSORS_URL = "app.android.sponsors";
    public static final String EVENT_REGISTER = "app.android.event.register";
    public static final String CONTACT_APP = "app.android.android.team";
    public static String LinkPDF;
    public static String Base_Url = "https://beta.aavartan.org/";//TODO : add urlshttps://beta.aavartan.org/app.android.schedule/7

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
