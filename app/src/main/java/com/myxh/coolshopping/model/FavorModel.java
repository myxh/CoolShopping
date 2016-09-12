package com.myxh.coolshopping.model;

/**
 * Created by asus on 2016/9/11.
 */
public class FavorModel extends BaseModel {

    private String userId;
    private String goodsId;
    private String imageUrl;
    private String product;
    private String description;
    private String price;
    private String value;
    private String sevenRefund;
    private int timeRefund;
    private int bought;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSevenRefund() {
        return sevenRefund;
    }

    public void setSevenRefund(String sevenRefund) {
        this.sevenRefund = sevenRefund;
    }

    public int getTimeRefund() {
        return timeRefund;
    }

    public void setTimeRefund(int timeRefund) {
        this.timeRefund = timeRefund;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }
}
