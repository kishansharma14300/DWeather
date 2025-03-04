package com.aswdc_Dweather.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_Dweather.POJOS.yahho_weather_reponse.Location;
import com.aswdc_Dweather.utility.Constants;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper_City extends SQLiteAssetHelper {
    SQLiteDatabase db;
    public DBHelper_City(Context context) {
        super(context, Constants.DBName, null, Constants.DBVersion);
    }

    public void insert(Location location) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("city", location.getCity());
        cv.put("long", location.getJsonMemberLong());
        cv.put("lat", location.getLat());
        cv.put("woeid", location.getWoeid());
        cv.put("country", location.getCountry());
        cv.put("region", location.getRegion());
        cv.put("timezone_id", location.getTimezoneId());
            db.insert("cityDetails", null, cv);
        db.close();

    }
//
//    public double getLat(String city){
//        SQLiteDatabase db = getReadableDatabase();
//        String query = "select latItemClick from cityDetails where latItemClick=?";
//        Cursor cursor = db.rawQuery(query, new String[]{new String(city)});
//        int keyRowIdColumnIndex;
//        double latItemClick = 0;
//        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
//            keyRowIdColumnIndex = cursor.getColumnIndex("latItemClick");
//            latItemClick=cursor.getDouble(keyRowIdColumnIndex);
//        }
//        return latItemClick;
//    }
//
//    public double getLong(String city){
//        SQLiteDatabase db = getReadableDatabase();
//        String query = "select long from cityDetails where long=?";
//        Cursor cursor = db.rawQuery(query, new String[]{new String(city)});
//        int keyRowIdColumnIndex;
//        double lonItemClick = 0;
//        cursor.moveToFirs0t();
//        while (cursor.moveToNext()) {
//            keyRowIdColumnIndex = cursor.getColumnIndex("long");
//            lonItemClick=cursor.getDouble(keyRowIdColumnIndex);
//        }
//        return lonItemClick;
//    }

    public void deleteLocation(String city) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("cityDetails", " city=?", new String[]{city});
        db.close();
    }

    public List<String> getLikedCity() {
        List<String> cityList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "select city from cityDetails";
        Cursor cursor = db.rawQuery(query, null);
        int keyRowIdColumnIndex;

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            keyRowIdColumnIndex = cursor.getColumnIndex("city");
            cityList.add(cursor.getString(keyRowIdColumnIndex));
        }
        return cityList;
    }


}
