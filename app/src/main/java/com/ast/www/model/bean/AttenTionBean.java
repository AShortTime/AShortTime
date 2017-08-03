package com.ast.www.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class AttenTionBean {

    /**
     * code : 200
     * user : [{"userHead":"aaa","userId":0,"userName":"aa","userPhone":"123456","userSex":"男"}]
     */

    private String code;
    private List<UserBean> user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * userHead : aaa
         * userId : 0
         * userName : aa
         * userPhone : 123456
         * userSex : 男
         */

        private String userHead;
        private int userId;
        private String userName;
        private String userPhone;
        private String userSex;

        public String getUserHead() {
            return userHead;
        }

        public void setUserHead(String userHead) {
            this.userHead = userHead;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }
    }
}
