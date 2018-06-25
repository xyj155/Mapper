package com.example.administrator.mapper.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class Usage {

    /**
     * status : 200
     * issuccess : true
     * data : [{"id":1,"userhead":"fsdf","username":"dsa","location":"ad","content":"da"},{"id":2,"userhead":"asd","username":"dasd","location":"sd","content":"dad"},{"id":3,"userhead":"dsad","username":"da","location":"a","content":"da"}]
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
         * userhead : fsdf
         * username : dsa
         * location : ad
         * content : da
         */

        private int id;
        private String userhead;
        private String username;
        private String location;
        private String content;

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
    }
}
