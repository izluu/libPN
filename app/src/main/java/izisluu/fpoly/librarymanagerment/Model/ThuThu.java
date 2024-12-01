package izisluu.fpoly.librarymanagerment.Model;

public class ThuThu {
    private String maThuThu;
    private String tenThuThu;
    private String username;
    private String password;

    public ThuThu() {
    }

    public ThuThu(String maThuThu, String tenThuThu, String username, String password) {
        this.maThuThu = maThuThu;
        this.tenThuThu = tenThuThu;
        this.username = username;
        this.password = password;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
