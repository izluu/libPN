package izisluu.fpoly.librarymanagerment.Model;

public class LoaiSach {
    private String maLoaiSach;
    private String tenLoaiSach;
    private String nickname;

    public LoaiSach(String maLoaiSach, String tenLoaiSach, String nickname) {
        this.maLoaiSach = maLoaiSach;
        this.tenLoaiSach = tenLoaiSach;
        this.nickname = nickname;
    }

    public LoaiSach() {

    }

    public String getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(String maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return tenLoaiSach;
    }
}
