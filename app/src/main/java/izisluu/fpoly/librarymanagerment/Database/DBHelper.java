package izisluu.fpoly.librarymanagerment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Lib.db";
    private static final int DB_VERSION = 6;
    public static final String TABLE_THUTHU_NAME = "ThuThu";
    public static final String TABLE_THANHVIEN_NAME = "ThanhVien";
    public static final String TABLE_LOAISACH_NAME = "LoaiSach";
    public static final String TABLE_SACH_NAME = "Sach";
    public static final String TABLE_PHIEUMUON_NAME = "PhieuMuon";
    //ThuThu
    public static final String COLLUMN_MATHUTHU = "MaThuThu";
    public static final String COLLUMN_TENTHUTHU = "TenThuThu";
    public static final String COLLUMN_USERNAME = "Username";
    public static final String COLLUMN_PASSWORD = "Password";
    //ThanhVien
    public static final String COLLUMN_MATHANHVIEN = "MaThanhVien";
    public static final String COLLUMN_TENTHANHVIEN = "TenThanhVien";
    public static final String COLLUMN_NGAYSINH = "NgaySinh";
    public static final String COLLUMN_SODIENTHOAI = "SoDienThoai";
    //LoaiSach
    public static final String COLLUMN_MALOAISACH = "MaLoaiSach";
    public static final String COLLUMN_TENLOAISACH = "TenLoaiSach";
    public static final String COLLUMN_NICKNAME = "Nickname";
    //Sach
    public static final String COLLUMN_MASACH = "MaSach";
    public static final String COLLUMN_TENSACH = "TenSach";
    public static final String COLLUMN_GIATIEN = "GiaTien";
    public static final String COLLUMN_TACGIA = "TacGia";
    public static final String COLLUMN_MALOAI = "MaLoai";
//    public static final String COLLUMN_TENLOAI = "TenLoai";
    //PhieuMuon
    public static final String COLLUMN_MAPHIEUMUON = "MaPhieuMuon";
    public static final String COLLUMN_MATHANHVIEN1 = "MaThanhVien";
//    public static final String COLLUMN_MATHUTHU1 = "MaThuThu";
    public static final String COLLUMN_NGAYMUON = "NgayMuon";
//    public static final String COLLUMN_AVATAR = "Avatar";
    public static String COLLUMN_TRANGTHAI = "TrangThai";
    public static final String COLLUMN_THANHTIEN = "ThanhTien";



    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ThuThu
        String sql = "CREATE TABLE " + TABLE_THUTHU_NAME + "("
                + COLLUMN_MATHUTHU + " TEXT PRIMARY KEY NOT NULL,"
                + COLLUMN_TENTHUTHU + " TEXT NOT NULL,"
                + COLLUMN_USERNAME + " TEXT NOT NULL,"
                + COLLUMN_PASSWORD + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
        String insert = "INSERT INTO " + TABLE_THUTHU_NAME
                + " VALUES('luuhpph56774','Hoàng Phong Lưu','admin','admin')";
        db.execSQL(insert);
        //ThanhVien
        String sql1 = "CREATE TABLE " + TABLE_THANHVIEN_NAME + "("
                + COLLUMN_MATHANHVIEN + " TEXT PRIMARY KEY NOT NULL,"
                + COLLUMN_TENTHANHVIEN + " TEXT NOT NULL,"
                + COLLUMN_NGAYSINH + " TEXT NOT NULL,"
                + COLLUMN_SODIENTHOAI + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql1);
        String insert2 = "INSERT INTO " + TABLE_THANHVIEN_NAME + " VALUES('tv01', 'Luu','08/06/2004','0868734935')";
        db.execSQL(insert2);
        //LoaiSach
        String sql2 = "CREATE TABLE " + TABLE_LOAISACH_NAME + "("
                + COLLUMN_MALOAISACH + " TEXT PRIMARY KEY NOT NULL,"
                + COLLUMN_TENLOAISACH + " TEXT NOT NULL,"
                + COLLUMN_NICKNAME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql2);
        String insert3 = "INSERT INTO " + TABLE_LOAISACH_NAME + " VALUES('ls01','Truyen Tranh','TT')";
        db.execSQL(insert3);
        //Sach
        String sql3 = "CREATE TABLE " + TABLE_SACH_NAME + "("
                + COLLUMN_MASACH + " TEXT PRIMARY KEY NOT NULL,"
                + COLLUMN_TENSACH + " TEXT NOT NULL,"
                + COLLUMN_TACGIA + " TEXT NOT NULL,"
                + COLLUMN_GIATIEN + " TEXT NOT NULL,"
                + COLLUMN_MALOAI + " TEXT NOT NULL REFERENCES " + TABLE_LOAISACH_NAME + "(" + COLLUMN_MALOAISACH + ")"
                + ")";
        db.execSQL(sql3);
        String insert4 = "INSERT INTO " + TABLE_SACH_NAME + " VALUES('s01','Truyen Kieu','Nguyen Du','100000','ls01')";
        db.execSQL(insert4);
        //PhieuMuon
        String sql4 = "CREATE TABLE " + TABLE_PHIEUMUON_NAME + "("
                + COLLUMN_MAPHIEUMUON + " TEXT PRIMARY KEY NOT NULL,"
                + COLLUMN_NGAYMUON + " TEXT NOT NULL,"
                + COLLUMN_TRANGTHAI + " INTEGER NOT NULL,"
                + COLLUMN_THANHTIEN + " TEXT NOT NULL,"
                + COLLUMN_MATHANHVIEN1 + " TEXT REFERENCES " + TABLE_THANHVIEN_NAME + "(" + COLLUMN_MATHANHVIEN + "),"
                + COLLUMN_MASACH + " TEXT REFERENCES " + TABLE_SACH_NAME + "(" + COLLUMN_MASACH + ")"
                + ")";
        db.execSQL(sql4);
        String insert5 = "INSERT INTO " + TABLE_PHIEUMUON_NAME + " VALUES('pm01','08/06/2023',1,'100000','tv01','s01')";
        db.execSQL(insert5);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THUTHU_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THANHVIEN_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAISACH_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SACH_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHIEUMUON_NAME);
            onCreate(db);
        }
    }
}
