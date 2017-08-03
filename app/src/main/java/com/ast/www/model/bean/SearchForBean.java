package com.ast.www.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class SearchForBean {

    /**
     * code : 200
     * user : [{"userHead":"","userId":0,"userName":"aa","userPhone":"123456","userSex":"男"},{"userHead":"","userId":0,"userName":"bb","userPhone":"12345","userSex":"女"},{"userHead":"","userId":0,"userName":"zhang","userPhone":"123456","userSex":"男"},{"userHead":"","userId":0,"userName":"a123456","userPhone":"18410177108","userSex":"123"},{"userHead":"","userId":0,"userName":"123","userPhone":"15910488411","userSex":"男"},{"userHead":"","userId":0,"userName":"123","userPhone":"15910488415","userSex":"女"}]
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
         * userHead :
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
