package izisluu.fpoly.librarymanagerment.Model;

public class ThanhVien {
    private String maThanhVien;
    private String tenThanhVien;
    private String ngaySinh;
    private String soDienThoai;

    public ThanhVien(String maThanhVien, String tenThanhVien, String ngaySinh, String soDienThoai) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
    }

    public ThanhVien() {

    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public String toString(){
        return this.tenThanhVien;
    }
}
