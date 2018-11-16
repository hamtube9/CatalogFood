package duan1.catalogfood.model;

public class MainFood {
    private String name, diachi, gia, dienthoai;
    private byte[] anh;
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



    public MainFood(String name, String diachi, String gia, String dienthoai) {

        this.name = name;
        this.diachi = diachi;
        this.gia = gia;
        this.dienthoai = dienthoai;

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
