package duan1.catalogfood.model;

public class FastFood {
    private String name,diachi,anh;
    private String gia,dienthoai;
    public FastFood() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public FastFood(String name, String diachi, String anh, String gia, String dienthoai) {

        this.name = name;
        this.diachi = diachi;
        this.anh = anh;
        this.gia = gia;
        this.dienthoai = dienthoai;
    }
}
