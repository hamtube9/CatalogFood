package duan1.catalogfood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import duan1.catalogfood.model.FastFoodDAO;
import duan1.catalogfood.model.MainFoodDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_Name="catalog";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FastFoodDAO.CREATE_TABLE_FASTFOOD);
        db.execSQL(MainFoodDAO.CREATE_TABLE_MAINFOOD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+FastFoodDAO.TABLE_FAST_FOOD);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+MainFoodDAO.TABLE_MAIN_FOOD);
        onCreate(db);
    }
}
