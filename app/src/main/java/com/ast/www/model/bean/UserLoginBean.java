package com.ast.www.model.bean;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/21 10:35
 * Title:
 * Text:
 */

public class UserLoginBean {

    /**
     * code : 200
     * user : {"userHead":"emmm","userId":3,"userName":"郭凯奇","userPassword":"123456","userPhone":"17600887015","userSex":"男"}
     */

    private String code;
    private UserBean user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        @Override
        public String toString() {
            return "UserBean{" +
                    "userHead='" + userHead + '\'' +
                    ", userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", userPassword='" + userPassword + '\'' +
                    ", userPhone='" + userPhone + '\'' +
                    ", userSex='" + userSex + '\'' +
                    '}';
        }

        /**
         * userHead : emmm
         * userId : 3
         * userName : 郭凯奇
         * userPassword : 123456
         * userPhone : 17600887015
         * userSex : 男
         */

        private String userHead;
        private int userId;
        private String userName;
        private String userPassword;
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

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
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

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "code='" + code + '\'' +
                ", user=" + user +
                '}';
    }
}
