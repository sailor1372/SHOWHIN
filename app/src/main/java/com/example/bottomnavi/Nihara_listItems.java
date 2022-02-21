package com.example.bottomnavi;

import android.graphics.Bitmap;

public class Nihara_listItems {

    private String mimgUrl = null;
    private Bitmap mLogo = null;
    private Bitmap mShare = null;
    private Bitmap mFavorite = null;
    private String mDate = null;
    private String mJancode = null;
    private String mName = null;
    private String mPrice = null;
    private String mWebUrl = null;


    // 空のコンストラクタ
    public Nihara_listItems() {};

    /**
     * コンストラクタ
     * @param imgUrl 商品画像
     * @param logo ロゴ画像
     * @param share 共有画像
     * @param favorite ハート画像
     * @param date 日付
     * @param name 名前
     * @param jancode JANコード
     * @param price 値段
     */
    public Nihara_listItems(String imgUrl, Bitmap logo, Bitmap share, Bitmap favorite,
                     String date, String jancode, String name, String price, String webUrl) {
        mimgUrl = imgUrl;
        mLogo = logo;
        mShare = share;
        mFavorite = favorite;
        mDate = date;
        mJancode = jancode;
        mName = name;
        mPrice = price;
        mWebUrl = webUrl;
    }

    /**
     * 商品画像を設定
     * @param imgUrl 商品画像
     */
     public void setImgURL(String imgUrl) {
       mimgUrl = imgUrl;
     }

    /**
     * ロゴ画像を設定
     * @param logo ロゴ画像
     */
    public void setLogo(Bitmap logo) {
        mLogo = logo;
    }

    /**
     * 共有画像を設定
     * @param share 共有画像
     */
    public void setShare(Bitmap share) {
        mShare = share;
    }

    /**
     * ハート画像を設定
     * @param favorite ハート画像
     */
    public void setFavorite(Bitmap favorite) {
        mFavorite = favorite;
    }

    /**
     * 日付を設定
     * @param date 日付
     */
    public void setDate(String date) {
        mDate = date;
    }

    /**
     * JANコードを設定
     * @param jancode JANコード
     */
    public void setJancode(String jancode) {
        mJancode = jancode;
    }

    /**
     * 名前を設定
     * @param name 名前
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * 値段を設定
     * @param price 値段
     */
    public void setPrice(String price) {
        mPrice = price;
    }

    public void setmWebUrl(String webUrl) {mWebUrl = webUrl; }

    /**
     * 商品画像を取得
     * @return  商品画像
     */
    public String getImgUrl(){
       return mimgUrl;
    }

    /**
     * ロゴ画像を取得
     * @return  ロゴ画像
     */
    public Bitmap getLogo(){
        return mLogo;
    }

    /**
     * 共有画像を取得
     * @return  共有画像
     */
    public Bitmap getShare(){
        return mShare;
    }

    /**
     * ハート画像を取得
     * @return  ハート画像
     */
    public Bitmap getFavorite(){
        return mFavorite;
    }

    /**
     * 日付を取得
     * @return  date 日付
     */
    public String getDate() {
        return mDate;
    }

    /**
     * JANコードを取得
     * @return  jancode JANコード
     */
    public String getJancode() {
        return mJancode;
    }

    /**
     * 名前を取得
     * @return  name 名前
     */
    public String getName() {
        return mName;
    }

    /**
     * 値段を取得
     * @return  price 値段
     */
    public String getPrice() {
        return mPrice;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

}
