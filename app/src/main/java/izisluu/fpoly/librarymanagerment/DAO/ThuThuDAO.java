package izisluu.fpoly.librarymanagerment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Database.DBHelper;
import izisluu.fpoly.librarymanagerment.Model.ThuThu;

public class ThuThuDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<ThuThu> getData(String sql, String... selectionArgs){
        ArrayList<ThuThu> lst = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaThuThu(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MATHUTHU)));
            thuThu.setTenThuThu(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_TENTHUTHU)));
            thuThu.setUsername(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_USERNAME)));
            thuThu.setPassword(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_PASSWORD)));
            lst.add(thuThu);
        }
        return lst;
    }
    public ArrayList<ThuThu> getAll(){
        String sql = "SELECT * FROM " + dbHelper.TABLE_THUTHU_NAME;
        return getData(sql);
    }
    public ArrayList<ThuThu> getThuThuByID(String id){
        String sql = "SELECT * FROM " + dbHelper.TABLE_THUTHU_NAME + " WHERE " + dbHelper.COLLUMN_MATHUTHU + " = ?";
        return getData(sql, id);
    }
    public Boolean insert(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MATHUTHU,thuThu.getMaThuThu());
        values.put(dbHelper.COLLUMN_TENTHUTHU,thuThu.getTenThuThu());
        values.put(dbHelper.COLLUMN_USERNAME,thuThu.getUsername());
        values.put(dbHelper.COLLUMN_PASSWORD,thuThu.getPassword());
        long rs = database.insert(dbHelper.TABLE_THUTHU_NAME,null,values);
        return rs != -1;
    }
    public Boolean update(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_TENTHUTHU,thuThu.getTenThuThu());
        values.put(dbHelper.COLLUMN_USERNAME,thuThu.getUsername());
        values.put(dbHelper.COLLUMN_PASSWORD,thuThu.getPassword());
        int rs = database.update(dbHelper.TABLE_THUTHU_NAME,values,dbHelper.COLLUMN_MATHUTHU + "=?",new String[]{thuThu.getMaThuThu()});
        return rs  > 0;
    }
    public Boolean delete(String id){
        int rs = database.delete(dbHelper.TABLE_THUTHU_NAME,dbHelper.COLLUMN_MATHUTHU + "=?",new String[]{id});
        return rs > 0;
    }
    public Boolean checkLogin(String username, String password){
        String sql = "SELECT * FROM " + dbHelper.TABLE_THUTHU_NAME +
                " WHERE " + dbHelper.COLLUMN_MATHUTHU + " = ? AND " + dbHelper.COLLUMN_PASSWORD + " = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{username, password});

        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }


}
