package com.technocracy.app.aavartan.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.technocracy.app.aavartan.api.Attraction;
import com.technocracy.app.aavartan.api.GalleryItem;
import com.technocracy.app.aavartan.api.Notifications;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "LocalDatabase";

    // Notifications table name
    private static final String TABLE_NOTIFICATIONS = "notifications";
    // Notifications Table Columns names
    private static final String NOTIFICATION_ID = "id";
    private static final String NOTIFICATION_TYPE = "type";
    private static final String NOTIFICATION_EVENT_ID = "event_id";
    private static final String NOTIFICATION_TITLE = "title";
    private static final String NOTIFICATION_MESSAGE = "message";
    private static final String NOTIFICATION_IMAGE_URL = "image_url";
    private static final String NOTIFICATION_CREATED_AT = "created_at";

    // Notifications table name
    private static final String TABLE_ATTRACTIONS = "attractions";
    // Notifications Table Columns names
    private static final String ATTRACTION_ID = "id";
    private static final String ATTRACTION_NAME = "name";
    private static final String ATTRACTION_DESCRIPTION = "description";
    private static final String ATTRACTION_IMAGE_URL = "image";

    // Notifications table name
    private static final String TABLE_GALLERY = "gallery";
    // Notifications Table Columns names
    private static final String GALLERY_ID = "id";
    private static final String GALLERY_TITLE = "title";
    private static final String GALLERY_RATIO = "ratio";
    private static final String GALLERY_IMAGE_URL = "image";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTIFICATIONS_TABLE = "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + NOTIFICATION_ID + " INTEGER PRIMARY KEY," + NOTIFICATION_TYPE + " TEXT,"
                + NOTIFICATION_EVENT_ID + " INTEGER," + NOTIFICATION_TITLE + " TEXT,"
                + NOTIFICATION_MESSAGE + " TEXT," + NOTIFICATION_IMAGE_URL + " TEXT,"
                + NOTIFICATION_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_NOTIFICATIONS_TABLE);

        String CREATE_ATTRACTIONS_TABLE = "CREATE TABLE " + TABLE_ATTRACTIONS + "("
                + ATTRACTION_ID + " INTEGER PRIMARY KEY," + ATTRACTION_NAME + " TEXT,"
                + ATTRACTION_DESCRIPTION + " TEXT," + ATTRACTION_IMAGE_URL  + " TEXT" + ")";
        db.execSQL(CREATE_ATTRACTIONS_TABLE);

        String CREATE_GALLEY_TABLE = "CREATE TABLE " + TABLE_GALLERY + "("
                + GALLERY_ID + " INTEGER PRIMARY KEY," + GALLERY_TITLE + " TEXT,"
                + GALLERY_RATIO + " TEXT," + GALLERY_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_GALLEY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        // Create tables again
        onCreate(db);
    }
    public void addAttraction(Attraction attraction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRACTION_ID, attraction.getId());
        values.put(ATTRACTION_NAME, attraction.getName());
        values.put(ATTRACTION_DESCRIPTION, attraction.getDescription());
        values.put(ATTRACTION_IMAGE_URL, attraction.getImgUrl());
        // Inserting Row
        db.insert(TABLE_ATTRACTIONS, null, values);
        db.close(); // Closing database connection
        Log.e("Attraction:","stored in db.");
    }
    public ArrayList<Attraction> getAllAttractions() {
        ArrayList<Attraction> attractionList = new ArrayList<Attraction>();
        String selectQuery = "SELECT  * FROM " + TABLE_ATTRACTIONS + " ORDER BY " + ATTRACTION_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Attraction attraction = new Attraction(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                attractionList.add(attraction);
            } while (cursor.moveToNext());
        }

        return attractionList;
    }
    public void deleteAllAttractions() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ATTRACTIONS);
    }

    public void addGalleryItem(GalleryItem galleryItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GALLERY_ID, galleryItem.getId());
        values.put(GALLERY_TITLE, galleryItem.getTitle());
        values.put(GALLERY_RATIO, String.valueOf(galleryItem.getRatio()));
        values.put(GALLERY_IMAGE_URL, galleryItem.getUrl());
        // Inserting Row
        db.insert(TABLE_GALLERY, null, values);
        db.close(); // Closing database connection
        Log.e("Gallery Item:","stored in db.");
    }
    public ArrayList<GalleryItem> getAllGalleryItems() {
        ArrayList<GalleryItem> galleryItemList = new ArrayList<GalleryItem>();
        String selectQuery = "SELECT * FROM " + TABLE_GALLERY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                GalleryItem galleryItem = new GalleryItem(cursor.getInt(0), cursor.getString(1),
                        Double.parseDouble(cursor.getString(2)), cursor.getString(3));
                galleryItemList.add(galleryItem);
            } while (cursor.moveToNext());
        }

        return galleryItemList;
    }
    public void deleteAllGalleryItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GALLERY);
    }

    public void addNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, notification.getId());
        values.put(NOTIFICATION_TYPE, notification.getType());
        values.put(NOTIFICATION_EVENT_ID, notification.getEventId());
        values.put(NOTIFICATION_TITLE, notification.getTitle());
        values.put(NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(NOTIFICATION_IMAGE_URL, notification.getImageUrl());
        values.put(NOTIFICATION_CREATED_AT, notification.getCreatedAt());
        // Inserting Row
        db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close(); // Closing database connection
    }
    public Notifications getNotification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTIFICATIONS, new String[]{NOTIFICATION_ID,
                        NOTIFICATION_TYPE, NOTIFICATION_EVENT_ID,
                        NOTIFICATION_TITLE, NOTIFICATION_MESSAGE,
                        NOTIFICATION_IMAGE_URL, NOTIFICATION_CREATED_AT,}, NOTIFICATION_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notifications notifications = new Notifications(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));

        return notifications;
    }
    public ArrayList<Notifications> getAllNotifications() {
        ArrayList<Notifications> notificationsList = new ArrayList<Notifications>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS + " ORDER BY " + NOTIFICATION_CREATED_AT + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Notifications notification = new Notifications(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));

                // Adding notification to list
                notificationsList.add(notification);
            } while (cursor.moveToNext());
        }
        // return notification list
        return notificationsList;
    }
    public int getNotificationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
    public void refreshNotifications(ArrayList<Notifications> notificationsArrayList) {
        deleteAllNotifications();
        for (Notifications notification : notificationsArrayList)
            addNotification(notification);
    }
    public int updateNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, notification.getId());
        values.put(NOTIFICATION_TYPE, notification.getType());
        values.put(NOTIFICATION_EVENT_ID, notification.getEventId());
        values.put(NOTIFICATION_TITLE, notification.getTitle());
        values.put(NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(NOTIFICATION_IMAGE_URL, notification.getImageUrl());
        values.put(NOTIFICATION_CREATED_AT, notification.getCreatedAt());

        // updating row
        return db.update(TABLE_NOTIFICATIONS, values, NOTIFICATION_ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
    }
    public void deleteAllNotifications() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTIFICATIONS);
    }
    public void deleteNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATIONS, NOTIFICATION_ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
        db.close();
    }
    public boolean notificationAlreadyPresent(Notifications notification) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NOTIFICATIONS + " WHERE "
                + NOTIFICATION_ID + " = " + notification.getId();
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public void dropDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        onCreate(db);
    }
}