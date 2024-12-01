package izisluu.fpoly.librarymanagerment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Database.DBHelper;
import izisluu.fpoly.librarymanagerment.Model.LoaiSach;

public class LoaiSachDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public LoaiSachDAO(Context context) {

        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<LoaiSach> getAll() {
        database = dbHelper.getReadableDatabase();
        ArrayList<LoaiSach> list = new ArrayList<>();
        String sql = "SELECT * FROM " + dbHelper.TABLE_LOAISACH_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoaiSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MALOAISACH)));
            loaiSach.setTenLoaiSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TENLOAISACH)));
            loaiSach.setNickname(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_NICKNAME)));
            list.add(loaiSach);
        }
        return list;
    }
    public boolean insert(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MALOAISACH, loaiSach.getMaLoaiSach());
        values.put(dbHelper.COLLUMN_TENLOAISACH, loaiSach.getTenLoaiSach());
        values.put(dbHelper.COLLUMN_NICKNAME, loaiSach.getNickname());
        long rs = database.insert(dbHelper.TABLE_LOAISACH_NAME, null, values);
        return rs != -1;
    }
    public boolean update(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_TENLOAISACH, loaiSach.getTenLoaiSach());
        values.put(dbHelper.COLLUMN_NICKNAME, loaiSach.getNickname());
        int rs = database.update(dbHelper.TABLE_LOAISACH_NAME, values, dbHelper.COLLUMN_MALOAISACH + "=?", new String[]{loaiSach.getMaLoaiSach()});
        return rs > 0;
    }
    public boolean delete(String id) {
        int rs = database.delete(dbHelper.TABLE_LOAISACH_NAME, dbHelper.COLLUMN_MALOAISACH + "=?", new String[]{id});
        return rs > 0;
    }

}
