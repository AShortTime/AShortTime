package com.ast.www.model.bean;


import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/4 19:50
 * Title:
 * Text:
 */

public class DetailCommentBean {

    /**
     * code : 200
     * comment : [{"commentCharacterPictureMediaId":10,"commentContent":"视频很好看","commentDate":"2017-07-31 10:44:08.0","commentDictionaryValue":1,"commentId":1,"commentPid":0,"commentUserId":32,"nicenum":234,"redisnicekey":"adfaerra","type":1,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}},{"commentCharacterPictureMediaId":10,"commentContent":"视频很好看","commentDate":"2017-07-31 10:44:08.0","commentDictionaryValue":1,"commentId":5,"commentPid":0,"commentUserId":32,"nicenum":234,"redisnicekey":"adfaerra","type":2,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}},{"commentCharacterPictureMediaId":10,"commentContent":"视频真的很好看","commentDate":"2017-07-31 10:32:34.0","commentDictionaryValue":1,"commentId":2,"commentPid":1,"commentUserId":32,"nicenum":4222,"redisnicekey":"wadfqqqq","type":3,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}},{"commentCharacterPictureMediaId":10,"commentContent":"视频不好看","commentDate":"2017-08-01 10:46:31.0","commentDictionaryValue":1,"commentId":3,"commentPid":2,"commentUserId":3,"nicenum":343,"redisnicekey":"lkajoqeoru","type":3,"user":{"userHead":"","userId":3,"userName":"郭凯奇","userPassword":"123456","userPhone":"17600887015","userSex":"男","userSignature":"aaaa"}},{"commentCharacterPictureMediaId":10,"commentContent":"我的视频","commentDate":"2017-08-02 10:55:07.0","commentDictionaryValue":1,"commentId":4,"commentPid":1,"commentUserId":32,"nicenum":2345,"redisnicekey":"wwsdfwfs","type":3,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}},{"commentCharacterPictureMediaId":10,"commentContent":"我的视频","commentDate":"2017-08-02 10:55:07.0","commentDictionaryValue":1,"commentId":10,"commentPid":1,"commentUserId":32,"nicenum":2345,"redisnicekey":"zcxvcv","type":3,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}}]
     */

    private int code;
    private List<CommentBean> comment;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class CommentBean {
        /**
         * commentCharacterPictureMediaId : 10
         * commentContent : 视频很好看
         * commentDate : 2017-07-31 10:44:08.0
         * commentDictionaryValue : 1
         * commentId : 1
         * commentPid : 0
         * commentUserId : 32
         * nicenum : 234
         * redisnicekey : adfaerra
         * type : 1
         * user : {"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男","userSignature":"编辑个性签名"}
         */

        private int commentCharacterPictureMediaId;
        private String commentContent;
        private String commentDate;
        private int commentDictionaryValue;
        private int commentId;
        private int commentPid;
        private int commentUserId;
        private int nicenum;
        private String redisnicekey;
        private int type;
        private UserBean user;

        public int getCommentCharacterPictureMediaId() {
            return commentCharacterPictureMediaId;
        }

        public void setCommentCharacterPictureMediaId(int commentCharacterPictureMediaId) {
            this.commentCharacterPictureMediaId = commentCharacterPictureMediaId;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public int getCommentDictionaryValue() {
            return commentDictionaryValue;
        }

        public void setCommentDictionaryValue(int commentDictionaryValue) {
            this.commentDictionaryValue = commentDictionaryValue;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public int getCommentPid() {
            return commentPid;
        }

        public void setCommentPid(int commentPid) {
            this.commentPid = commentPid;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public int getNicenum() {
            return nicenum;
        }

        public void setNicenum(int nicenum) {
            this.nicenum = nicenum;
        }

        public String getRedisnicekey() {
            return redisnicekey;
        }

        public void setRedisnicekey(String redisnicekey) {
            this.redisnicekey = redisnicekey;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * userHead : http://169.254.1.100/ic_ss.jpg
             * userId : 32
             * userName : baobao
             * userPassword : 123456
             * userPhone : 13484741060
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
}
