package duan1.catalogfood.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import duan1.catalogfood.database.DatabaseHelper;

public class MainFoodDAO {

    private SQLiteDatabase db;
    public static final String TABLE_MAIN_FOOD = "MainFood";
    public static final String MF_COLUMN_NAME = "Ten";
    public static final String MF_COLUMN_DIACHI = "DiaChi";
    public static final String MF_COLUMN_DIENTHOAI = "DienThoai";
    public static final String MF_COLUMN_GIA = "Gia";
    public static final String MF_COLUMN_ANH="Image";
    public static final String CREATE_TABLE_MAINFOOD = "Create table " + TABLE_MAIN_FOOD + "(" + MF_COLUMN_NAME + " VARCHAR(50)PRIMARY KEY NOT NULL, "
            + MF_COLUMN_DIACHI + " VARCHAR(50), "
            + MF_COLUMN_DIENTHOAI + " int, "
            + MF_COLUMN_GIA + " INT," +MF_COLUMN_ANH+" BLOB)";
    public static final String TAG = "Main Food Dao";


    public MainFoodDAO(Context context) {
        DatabaseHelper helper=new DatabaseHelper(context);
        db=helper.getWritableDatabase();
    }

    public int insertMainFood(MainFood mf) {
        ContentValues values = new ContentValues();

        values.put(MF_COLUMN_NAME, mf.getName());
        values.put(MF_COLUMN_DIACHI, mf.getDiachi());
        values.put(MF_COLUMN_DIENTHOAI, mf.getDienthoai());
        values.put(MF_COLUMN_GIA, mf.getGia());
        values.put(MF_COLUMN_ANH,mf.getAnh());

        try{
            if (db.insert(TABLE_MAIN_FOOD,null,values)==-1){
                return -1;
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;

    }

    public long updateMainFood(String name, String diachi,String gia, String dienthoai, byte[] anh) {

        ContentValues values = new ContentValues();
        values.put(MF_COLUMN_NAME, name);
        values.put(MF_COLUMN_DIACHI, diachi);
        values.put(MF_COLUMN_GIA, gia);
        values.put(MF_COLUMN_DIENTHOAI, dienthoai);

        values.put(MF_COLUMN_ANH,anh);
        int result = db.update(TABLE_MAIN_FOOD,values,"Ten=?",new String[]{name});
        if (result==0){
            return -1;
        }
        return 1;


    }


    public List<MainFood> getMainFood(){
        List<MainFood> mainFoodList=new ArrayList<>();

        Cursor cursor=db.query(TABLE_MAIN_FOOD,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            MainFood mf=new MainFood();
            mf.setName(cursor.getString(0));
            mf.setDiachi(cursor.getString(1));
            mf.setDienthoai(cursor.getString(2));
            mf.setGia(cursor.getString(3));
            mf.setAnh(cursor.getBlob(4));
            mainFoodList.add(mf);
            Log.d("//=====", mf.toString());
            cursor.moveToNext();
        }
        cursor.close();




        return mainFoodList;
    }

    public int deleteMainFood(String name){
        int result= db.delete(TABLE_MAIN_FOOD,"Ten=?",new String[]{name});
        if (result==0){
            return -1;
        }
        return 1;
    }

}
