package izisluu.fpoly.librarymanagerment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Database.DBHelper;
import izisluu.fpoly.librarymanagerment.Model.Sach;

public class SachDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public SachDAO(Context context) {

        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Sach> getAll() {
        database = dbHelper.getReadableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        String sql = "SELECT S.*, L." + dbHelper.COLLUMN_TENLOAISACH + " " +
                "FROM " + dbHelper.TABLE_SACH_NAME + " S " +
                "INNER JOIN " + dbHelper.TABLE_LOAISACH_NAME + " L " +
                "ON S." + dbHelper.COLLUMN_MALOAI + " = L." + dbHelper.COLLUMN_MALOAISACH;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MASACH)));
            sach.setMaLoaiSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MALOAI)));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TENSACH)));
            sach.setTacGia(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TACGIA)));
            sach.setGiaTien(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_GIATIEN)));
            sach.setTenLoaiSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TENLOAISACH)));
            list.add(sach);
//            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public boolean insert(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MASACH, sach.getMaSach());
        values.put(dbHelper.COLLUMN_MALOAI, sach.getMaLoaiSach());
        values.put(dbHelper.COLLUMN_TENSACH, sach.getTenSach());
        values.put(dbHelper.COLLUMN_TACGIA, sach.getTacGia());
        values.put(dbHelper.COLLUMN_GIATIEN, sach.getGiaTien());
        long rs = database.insert(dbHelper.TABLE_SACH_NAME, null, values);
        return rs != -1;
    }
    public boolean update(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MALOAI, sach.getMaLoaiSach());
        values.put(dbHelper.COLLUMN_TENSACH, sach.getTenSach());
        values.put(dbHelper.COLLUMN_TACGIA, sach.getTacGia());
        values.put(dbHelper.COLLUMN_GIATIEN, sach.getGiaTien());
        int rs = database.update(dbHelper.TABLE_SACH_NAME, values, dbHelper.COLLUMN_MASACH + "=?", new String[]{sach.getMaSach()});
        return rs > 0;
    }
    public boolean delete(String id) {
        int rs = database.delete(dbHelper.TABLE_SACH_NAME, dbHelper.COLLUMN_MASACH + "=?", new String[]{id});
        return rs > 0;
    }
    @SuppressLint("Range")
    public Sach getSachByID(String id) {
        Sach sach = null;
        String sql = "SELECT * FROM " + dbHelper.TABLE_SACH_NAME + " WHERE " + dbHelper.COLLUMN_MASACH + " = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{id});
        if (cursor != null && cursor.moveToFirst()) {
            sach = new Sach();
            sach.setMaSach(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_MASACH)));
            sach.setMaLoaiSach(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_MALOAI)));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_TENSACH)));
            sach.setTacGia(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_TACGIA)));
            sach.setGiaTien(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_GIATIEN)));
        }
        return sach;
    }
}
