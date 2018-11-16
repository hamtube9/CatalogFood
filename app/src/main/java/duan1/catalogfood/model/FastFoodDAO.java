package duan1.catalogfood.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import duan1.catalogfood.database.DatabaseHelper;

public class FastFoodDAO {
    private SQLiteDatabase db;
    public static final String TABLE_FAST_FOOD = "FastFood";
    public static final String FF_COLUMN_NAME = "Ten";
    public static final String FF_COLUMN_DIACHI = "DiaChi";
    public static final String FF_COLUMN_DIENTHOAI = "DienThoai";
    public static final String FF_COLUMN_GIA = "Gia";
    public static final String FF_COLUMN_ANH="Image";
    public static final String CREATE_TABLE_FASTFOOD = "Create table " + TABLE_FAST_FOOD + "(" + FF_COLUMN_NAME + " VARCHAR(50)PRIMARY KEY NOT NULL, "
            + FF_COLUMN_DIACHI + " VARCHAR(50), "
            + FF_COLUMN_DIENTHOAI + " int, "
            + FF_COLUMN_GIA + " INT, "+FF_COLUMN_ANH  +" BLOB )";
    public static final String TAG = "Fast Food Dao";


    public FastFoodDAO(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertFastFood(FastFood ff) {
        ContentValues values = new ContentValues();
        values.put(FF_COLUMN_NAME, ff.getName());
        values.put(FF_COLUMN_DIACHI, ff.getDiachi());
        values.put(FF_COLUMN_DIENTHOAI, ff.getDienthoai());
        values.put(FF_COLUMN_GIA, ff.getGia());
        values.put(FF_COLUMN_ANH,ff.getAnh());
        try {
            if (db.insert(TABLE_FAST_FOOD, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateFastFood(FastFood ff) {
        ContentValues values = new ContentValues();
        values.put(FF_COLUMN_NAME, ff.getName());
        values.put(FF_COLUMN_DIACHI, ff.getDiachi());
        values.put(FF_COLUMN_DIENTHOAI, ff.getDienthoai());
        values.put(FF_COLUMN_GIA, ff.getGia());
        values.put(FF_COLUMN_ANH,ff.getAnh());
        int result = db.update(TABLE_FAST_FOOD, values, "id=?", new String[]{ff.getName()});
        if (result == 0) {
            return -1;
        }
        return 1;


    }

    public List<FastFood> getFastFood() {
        List<FastFood> dsFasrfood = new ArrayList<>();
        Cursor c = db.query(TABLE_FAST_FOOD,null, null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            FastFood ff = new FastFood();
            ff.setName(c.getString(0));
            ff.setDiachi(c.getString(1));
            ff.setDienthoai(c.getString(2));
            ff.setGia(c.getString(3));
            ff.setAnh(c.getBlob(4));
            dsFasrfood.add(ff);
            Log.d("//=====", ff.toString());
            c.moveToNext();
        }
        c.close();
        return dsFasrfood;
    }
    public int deleteFastFood(String name) {
        int result = db.delete(TABLE_FAST_FOOD, "id=?", new String[]{name});
        if (result == 0)
            return -1;
        return 1;
    }

}
