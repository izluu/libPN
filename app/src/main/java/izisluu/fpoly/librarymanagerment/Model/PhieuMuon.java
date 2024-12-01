package izisluu.fpoly.librarymanagerment.Model;

public class PhieuMuon {
    private String maPhieuMuon;
    private String maThanhVien;
    private String tenTV;
//    private String maThuThu;
//    private String tenTT;
    private String ngayMuon;
    private int trangThai;
    private String thanhTien;
    private String maSach;
    private String tenSach;

    public PhieuMuon(String maPhieuMuon, String maThanhVien, String tenTV, String ngayMuon, int trangThai, String thanhTien, String maSach, String tenSach) {
        this.maPhieuMuon = maPhieuMuon;
        this.maThanhVien = maThanhVien;
        this.tenTV = tenTV;

        this.ngayMuon = ngayMuon;
//        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.thanhTien = thanhTien;
        this.maSach = maSach;
        this.tenSach = tenSach;
    }


    public PhieuMuon() {

    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }



    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }


    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

//    public String getNgayTra() {
//        return ngayTra;
//    }
//
//    public void setNgayTra(String ngayTra) {
//        this.ngayTra = ngayTra;
//    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
}
