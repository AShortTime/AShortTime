package com.ast.www.model.bean;

/**
 * Created by Administrator on 2017/7/31.
 */

public class RegisteredBean {


    /**
     * code : 200
     * user : {"userHead":"","userId":90,"userName":"a123456","userPassword":"123456","userPhone":"18410177108","userSex":"男","userSignature":"编辑个性签名"}
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
        /**
         * userHead :
         * userId : 90
         * userName : a123456
         * userPassword : 123456
         * userPhone : 18410177108
         * userSex : 男
         * userSignature : 编辑个性签名
         */

        private String userHead;
        private int userId;
        private String userName;
        private String userPassword;
        private String userPhone;
        private String userSex;
        private String userSignature;

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

        public String getUserSignature() {
            return userSignature;
        }

        public void setUserSignature(String userSignature) {
            this.userSignature = userSignature;
        }
    }
}
