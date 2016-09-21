package com.technocracy.app.aavartan.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String EVENT_ID = "event_id";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String IMAGE_URL = "image_url";
    private static final String CREATED_AT = "created_at";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTIFICATIONS_TABLE = "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + ID + " INTEGER PRIMARY KEY," + TYPE + " TEXT,"
                + EVENT_ID + " INTEGER," + TITLE + " TEXT,"
                + MESSAGE + " TEXT," + IMAGE_URL + " TEXT," + CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_NOTIFICATIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        // Create tables again
        onCreate(db);
    }

    public void addNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, notification.getId());
        values.put(TYPE, notification.getType());
        values.put(EVENT_ID, notification.getEventId());
        values.put(TITLE, notification.getTitle());
        values.put(MESSAGE, notification.getMessage());
        values.put(IMAGE_URL, notification.getImageUrl());
        values.put(CREATED_AT, notification.getCreatedAt());
        // Inserting Row
        db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close(); // Closing database connection
        Log.e("Notif :","added in db :*");
    }
    public Notifications getNotification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTIFICATIONS, new String[]{ID,
                        TYPE, EVENT_ID,
                        TITLE, MESSAGE,
                        IMAGE_URL, CREATED_AT,}, ID + "=?",
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
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS + " ORDER BY " + CREATED_AT + " DESC";

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
        values.put(ID, notification.getId());
        values.put(TYPE, notification.getType());
        values.put(EVENT_ID, notification.getEventId());
        values.put(TITLE, notification.getTitle());
        values.put(MESSAGE, notification.getMessage());
        values.put(IMAGE_URL, notification.getImageUrl());
        values.put(CREATED_AT, notification.getCreatedAt());

        // updating row
        return db.update(TABLE_NOTIFICATIONS, values, ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
    }
    public void deleteAllNotifications() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTIFICATIONS);
    }
    public void deleteNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATIONS, ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
        db.close();
    }
    public boolean notificationAlreadyPresent(Notifications notification) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NOTIFICATIONS + " WHERE "
                + ID + " = " + notification.getId();
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