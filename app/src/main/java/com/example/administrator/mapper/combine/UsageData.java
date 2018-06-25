package com.example.administrator.mapper.combine;

import com.example.administrator.mapper.entity.Usage;

/**
 * Created by Administrator on 2018/6/25.
 */

public class UsageData {
    private Usage entities;
//    private User users;

    public UsageData(Usage entities) {
        this.entities = entities;
//        this.users = users;
    }

    public Usage getEntities() {
        if (entities == null) {
            return new Usage();
        }
        return entities;
    }
//    public User getUsers() {
//        if (users == null) {
//            return new User();
//        }
//        return users;
//    }
}
