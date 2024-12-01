package izisluu.fpoly.librarymanagerment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Database.DBHelper;
import izisluu.fpoly.librarymanagerment.Model.PhieuMuon;

public class PhieuMuonDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public PhieuMuonDAO(Context context) {

        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public boolean insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MAPHIEUMUON, phieuMuon.getMaPhieuMuon());
        values.put(dbHelper.COLLUMN_MATHANHVIEN, phieuMuon.getMaThanhVien());
//        values.put(dbHelper.COLLUMN_MATHUTHU, phieuMuon.getMaThuThu());
        values.put(dbHelper.COLLUMN_NGAYMUON, phieuMuon.getNgayMuon());
//        values.put(dbHelper.COLLUMN_NGAYTRA, phieuMuon.getNgayTra());
        values.put(dbHelper.COLLUMN_TRANGTHAI, phieuMuon.getTrangThai());
        values.put(dbHelper.COLLUMN_THANHTIEN, phieuMuon.getThanhTien());
        values.put(dbHelper.COLLUMN_MASACH, phieuMuon.getMaSach());
        long rs = database.insert(dbHelper.TABLE_PHIEUMUON_NAME, null, values);
        return rs != -1;
    }
    public boolean update(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLLUMN_MATHANHVIEN, phieuMuon.getMaThanhVien());
//        values.put(dbHelper.COLLUMN_MATHUTHU, phieuMuon.getMaThuThu());
        values.put(dbHelper.COLLUMN_NGAYMUON, phieuMuon.getNgayMuon());
//        values.put(dbHelper.COLLUMN_NGAYTRA, phieuMuon.getNgayTra());
        values.put(dbHelper.COLLUMN_TRANGTHAI, phieuMuon.getTrangThai());
        values.put(dbHelper.COLLUMN_THANHTIEN, phieuMuon.getThanhTien());
        values.put(dbHelper.COLLUMN_MASACH, phieuMuon.getMaSach());
        long rs = database.update(dbHelper.TABLE_PHIEUMUON_NAME, values, dbHelper.COLLUMN_MAPHIEUMUON + " = ?", new String[]{phieuMuon.getMaPhieuMuon()});
        return rs != -1;
    }
    public boolean delete(String maPhieuMuon){
        long rs = database.delete(dbHelper.TABLE_PHIEUMUON_NAME, dbHelper.COLLUMN_MAPHIEUMUON + " = ?", new String[]{maPhieuMuon});
        return rs != -1;

    }
    @SuppressLint("Range")
    public ArrayList<PhieuMuon> getAll(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM " + dbHelper.TABLE_PHIEUMUON_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPhieuMuon(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MAPHIEUMUON)));
            phieuMuon.setMaThanhVien(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MATHANHVIEN)));
//            phieuMuon.setMaThuThu(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MATHUTHU)));
            phieuMuon.setNgayMuon(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_NGAYMUON)));
            phieuMuon.setTrangThai(cursor.getInt(cursor.getColumnIndex(dbHelper.COLLUMN_TRANGTHAI)));
            phieuMuon.setThanhTien(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_THANHTIEN)));
            phieuMuon.setMaSach(cursor.getString(cursor.getColumnIndex(dbHelper.COLLUMN_MASACH)));
            list.add(phieuMuon);

        }
        return list;
    }
    public double getDoanhThu(String tuNgay, String denNgay) {
        double doanhThu = 0;
        String sql = "SELECT SUM(CAST(THANHTIEN AS INTEGER)) FROM " + dbHelper.TABLE_PHIEUMUON_NAME +
                " WHERE NGAYMUON BETWEEN ? AND ?";
        Cursor cursor = database.rawQuery(sql, new String[]{tuNgay, denNgay});
        if (cursor != null && cursor.moveToFirst()) {
            doanhThu = cursor.isNull(0) ? 0 : cursor.getDouble(0);
        }
        if (cursor != null) {
            cursor.close();
        }
        return doanhThu;
    }

}
