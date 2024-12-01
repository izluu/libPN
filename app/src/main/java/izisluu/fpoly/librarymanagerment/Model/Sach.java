package izisluu.fpoly.librarymanagerment.Model;

public class Sach {
    private String maSach;
    private String maLoaiSach;
    private String tenSach;
    private String tacGia;
    private String giaTien;
    private String tenLoaiSach;

    public Sach(String maSach, String maLoaiSach, String tenSach, String tacGia, String giaTien) {
        this.maSach = maSach;
        this.maLoaiSach = maLoaiSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.giaTien = giaTien;
    }

    public Sach(String maSach, String maLoaiSach, String tenSach, String tacGia, String giaTien, String tenLoaiSach) {
        this.maSach = maSach;
        this.maLoaiSach = maLoaiSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.giaTien = giaTien;
        this.tenLoaiSach = tenLoaiSach;
    }


    public Sach() {

    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(String maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }
    public String toString(){
        return this.tenSach;
    }
}
