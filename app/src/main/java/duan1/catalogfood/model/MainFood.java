package duan1.catalogfood.model;

public class MainFood {
    private String name, diachi, gia, dienthoai,anh;

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

    public MainFood(String name, String diachi, String gia, String dienthoai, String anh) {

        this.name = name;
        this.diachi = diachi;
        this.gia = gia;
        this.dienthoai = dienthoai;
        this.anh = anh;
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



    public MainFood() {

    }

}
