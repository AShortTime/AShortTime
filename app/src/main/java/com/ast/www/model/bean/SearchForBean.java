package com.ast.www.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class SearchForBean {


    /**
     * code : 200
     * user : [{"userId":99,"userName":"wd","userPhone":"15711470934","userSex":"男","userSignature":"编辑个性签名"}]
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
         * userId : 99
         * userName : wd
         * userPhone : 15711470934
         * userSex : 男
         * userSignature : 编辑个性签名
         */

        private int userId;
        private String userName;
        private String userPhone;
        private String userSex;
        private String userSignature;

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

        public String getUserSignature() {
            return userSignature;
        }

        public void setUserSignature(String userSignature) {
            this.userSignature = userSignature;
        }
    }
}
