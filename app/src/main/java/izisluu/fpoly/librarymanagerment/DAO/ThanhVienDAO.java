package izisluu.fpoly.librarymanagerment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Database.DBHelper;
import izisluu.fpoly.librarymanagerment.Model.ThanhVien;

public class ThanhVienDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public ThanhVienDAO(Context context){

        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<ThanhVien> getALL(){
        database = dbHelper.getReadableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM " + dbHelper.TABLE_THANHVIEN_NAME;
        Cursor cursor = database.rawQuery(sql,null);
        while(cursor.moveToNext()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaThanhVien(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MATHANHVIEN)));
            thanhVien.setTenThanhVien(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TENTHANHVIEN)));
            thanhVien.setNgaySinh(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_NGAYSINH)));
            thanhVien.setSoDienThoai(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_SODIENTHOAI)));
            list.add(thanhVien);
        }
        return list;
    }
    public boolean insert(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MATHANHVIEN,thanhVien.getMaThanhVien());
        values.put(dbHelper.COLLUMN_TENTHANHVIEN,thanhVien.getTenThanhVien());
        values.put(dbHelper.COLLUMN_NGAYSINH,thanhVien.getNgaySinh());
        values.put(dbHelper.COLLUMN_SODIENTHOAI,thanhVien.getSoDienThoai());
        long rs = database.insert(dbHelper.TABLE_THANHVIEN_NAME,null,values);
        return rs != -1;
    }
    public boolean update(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_TENTHANHVIEN,thanhVien.getTenThanhVien());
        values.put(dbHelper.COLLUMN_NGAYSINH,thanhVien.getNgaySinh());
        values.put(dbHelper.COLLUMN_SODIENTHOAI,thanhVien.getSoDienThoai());
        int rs = database.update(dbHelper.TABLE_THANHVIEN_NAME,values,dbHelper.COLLUMN_MATHANHVIEN + "=?",new String[]{thanhVien.getMaThanhVien()});
        return rs > 0;
    }
    public boolean delete(String id){
        int rs = database.delete(dbHelper.TABLE_THANHVIEN_NAME,dbHelper.COLLUMN_MATHANHVIEN + "=?",new String[]{id});
        return rs > 0;
    }
    @SuppressLint("Range")
    public ThanhVien getTVByID(String id){
        ThanhVien tv = null;
        String sql = "SELECT * FROM " + dbHelper.TABLE_THANHVIEN_NAME + " WHERE " + dbHelper.COLLUMN_MATHANHVIEN + " = ?";
        Cursor cursor = database.rawQuery(sql,new String[]{id});
        if(cursor != null && cursor.moveToFirst()){
            tv = new ThanhVien();
            tv.setMaThanhVien(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_MATHANHVIEN)));
            tv.setTenThanhVien(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_TENTHANHVIEN)));
            tv.setNgaySinh(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_NGAYSINH)));
            tv.setSoDienThoai(cursor.getString(cursor.getColumnIndex(DBHelper.COLLUMN_SODIENTHOAI)));

        }
        return tv;
    }

}
