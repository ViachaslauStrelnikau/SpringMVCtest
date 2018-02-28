package model;

public class Goods {
    private String goodsCode;
    private String goodsName;
    private int goodsQuan;
    private String goodsED;
    private int goodsW_brutto;
    private String goodsInvoice;

    public String getGoodsCode() {
        return goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public int getGoodsQuan() {
        return goodsQuan;
    }

    public String getGoodsED() {
        return goodsED;
    }

    public int getGoodsW_brutto() {
        return goodsW_brutto;
    }

    public String getGoodsInvoice() {
        return goodsInvoice;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsQuan(int goodsQuan) {
        this.goodsQuan = goodsQuan;
    }

    public void setGoodsED(String goodsED) {
        this.goodsED = goodsED;
    }

    public void setGoodsW_brutto(int goodsW_brutto) {
        this.goodsW_brutto = goodsW_brutto;
    }

    public void setGoodsInvoice(String goodsInvoice) {
        this.goodsInvoice = goodsInvoice;
    }
}
