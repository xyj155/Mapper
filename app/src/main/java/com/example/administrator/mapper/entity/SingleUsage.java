package com.example.administrator.mapper.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class SingleUsage {

    /**
     * status : 200
     * issuccess : true
     * data : [{"id":1,"userhead":"http://img5.duitang.com/uploads/item/201601/14/20160114161349_BaMCA.jpeg","username":"徐易杰","location":"湖南省株洲市攸县城关镇","content":"关于你，2012年的九月，那是我们认识的季节，在四十多平米的教室里，我认识了你。我们是同学，彼此都还不熟悉的同学。那一句个子还高嘛，是我关注你的开始，有意无意的从四组向一组瞟你，小心翼翼的眼神怕你发现，却又怕你不明白，一点点的喜欢不敢声张。后来的打过来骂过去，后来聊天的默契，互道的晚安，才明白原来就是喜欢，没有其他。确定是喜欢，可是还是不敢声张。只因当初在人群中你说了一句，从此，你便住在了心里。不敢轻易靠近打扰，是怕自己扰了你的生活步调；不敢轻易对你开口言爱，是怕那样做是一种冒犯，是一种伤害我们关系的因素。","longitude":120.722474,"latitude":30.737879,"insertdate":"2018-06-26 05:45:06","img":"http://imgsrc.baidu.com/imgad/pic/item/060828381f30e92400c7f0c546086e061c95f7e7.jpg","title":"我的爱情"}]
     */

    private int status;
    private boolean issuccess;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * userhead : http://img5.duitang.com/uploads/item/201601/14/20160114161349_BaMCA.jpeg
         * username : 徐易杰
         * location : 湖南省株洲市攸县城关镇
         * content : 关于你，2012年的九月，那是我们认识的季节，在四十多平米的教室里，我认识了你。我们是同学，彼此都还不熟悉的同学。那一句个子还高嘛，是我关注你的开始，有意无意的从四组向一组瞟你，小心翼翼的眼神怕你发现，却又怕你不明白，一点点的喜欢不敢声张。后来的打过来骂过去，后来聊天的默契，互道的晚安，才明白原来就是喜欢，没有其他。确定是喜欢，可是还是不敢声张。只因当初在人群中你说了一句，从此，你便住在了心里。不敢轻易靠近打扰，是怕自己扰了你的生活步调；不敢轻易对你开口言爱，是怕那样做是一种冒犯，是一种伤害我们关系的因素。
         * longitude : 120.722474
         * latitude : 30.737879
         * insertdate : 2018-06-26 05:45:06
         * img : http://imgsrc.baidu.com/imgad/pic/item/060828381f30e92400c7f0c546086e061c95f7e7.jpg
         * title : 我的爱情
         */

        private int id;
        private String userhead;
        private String username;
        private String location;
        private String content;
        private double longitude;
        private double latitude;
        private String insertdate;
        private String img;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserhead() {
            return userhead;
        }

        public void setUserhead(String userhead) {
            this.userhead = userhead;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getInsertdate() {
            return insertdate;
        }

        public void setInsertdate(String insertdate) {
            this.insertdate = insertdate;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
