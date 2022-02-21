package com.example.bottomnavi;

public class ParseItem {
    private String imgUrl;
    private String title;
    private String price;
    private String webUrl;

    public ParseItem(){

    }

    public ParseItem(String imgUrl, String title, String price, String webUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
        this.webUrl = webUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWebUrl(){return webUrl;}

    public void setWebUrl(String webUrl){this.webUrl = webUrl;}
}
