package com.ast.www.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/1 10:38
 * Title:
 * Text:
 */

public class RecommendHotBean implements Serializable{

    /**
     * code : 200
     * resource : [{"badKey":"M4154071s220654F7o4s74a2sF1a4f26","badNum":434,"commentKey":"M4154071s220654F7o4s74a2sF1a4f26","commentList":[{"commentCharacterPictureMediaId":2,"commentContent":"图片好看","commentDate":"2017-07-31 10:44:08.0","commentDictionaryValue":3,"commentId":6,"commentPid":0,"commentUserId":3,"nicenum":234,"redisnicekey":"adfa","user":{"userHead":"","userId":3,"userName":"郭凯奇","userPassword":"123456","userPhone":"17600887015","userSex":"男"}}],"commentNum":434,"content":"","description":"jiji","dictionaryValue":"1","forwardKey":"M4154071s220654F7o4s74a2sF1a4f26","forwardNum":0,"id":2,"name":"ic_cc.png","niceKey":"M4154071s220654F7o4s74a2sF1a4f26","niceNum":434,"pictureSrc":"","playTimeKey":"","playtimes":"","src":"http://169.254.1.100/ic_cc.png","uptime":"2017-07-31 11:58:19.0","user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"},"userId":0}]
     */

    private String code;
    private List<ResourceBean> resource;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ResourceBean> getResource() {
        return resource;
    }

    public void setResource(List<ResourceBean> resource) {
        this.resource = resource;
    }

    public static class ResourceBean implements Serializable{
        /**
         * badKey : M4154071s220654F7o4s74a2sF1a4f26
         * badNum : 434
         * commentKey : M4154071s220654F7o4s74a2sF1a4f26
         * commentList : [{"commentCharacterPictureMediaId":2,"commentContent":"图片好看","commentDate":"2017-07-31 10:44:08.0","commentDictionaryValue":3,"commentId":6,"commentPid":0,"commentUserId":3,"nicenum":234,"redisnicekey":"adfa","user":{"userHead":"","userId":3,"userName":"郭凯奇","userPassword":"123456","userPhone":"17600887015","userSex":"男"}}]
         * commentNum : 434
         * content :
         * description : jiji
         * dictionaryValue : 1
         * forwardKey : M4154071s220654F7o4s74a2sF1a4f26
         * forwardNum : 0
         * id : 2
         * name : ic_cc.png
         * niceKey : M4154071s220654F7o4s74a2sF1a4f26
         * niceNum : 434
         * pictureSrc :
         * playTimeKey :
         * playtimes :
         * src : http://169.254.1.100/ic_cc.png
         * uptime : 2017-07-31 11:58:19.0
         * user : {"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userSex":"男"}
         * userId : 0
         */
        private boolean isnice=false;
        private boolean isbade=false;

        public boolean isbade() {
            return isbade;
        }

        public void setIsbade(boolean isbade) {
            this.isbade = isbade;
        }

        public void setIsnice(boolean isnice) {
            this.isnice = isnice;
        }

        public boolean isnice() {
            return isnice;
        }

        private String badKey;
        private int badNum;
        private String commentKey;
        private int commentNum;
        private String content;
        private String description;
        private String dictionaryValue;
        private String forwardKey;
        private int forwardNum;
        private int id;
        private String name;
        private String niceKey;
        private int niceNum;
        private String pictureSrc;
        private String playTimeKey;
        private String playtimes;
        private String src;
        private String uptime;
        private UserBean user;
        private int userId;
        private List<CommentListBean> commentList;

        public String getBadKey() {
            return badKey;
        }

        public void setBadKey(String badKey) {
            this.badKey = badKey;
        }

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public String getCommentKey() {
            return commentKey;
        }

        public void setCommentKey(String commentKey) {
            this.commentKey = commentKey;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDictionaryValue() {
            return dictionaryValue;
        }

        public void setDictionaryValue(String dictionaryValue) {
            this.dictionaryValue = dictionaryValue;
        }

        public String getForwardKey() {
            return forwardKey;
        }

        public void setForwardKey(String forwardKey) {
            this.forwardKey = forwardKey;
        }

        public int getForwardNum() {
            return forwardNum;
        }

        public void setForwardNum(int forwardNum) {
            this.forwardNum = forwardNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNiceKey() {
            return niceKey;
        }

        public void setNiceKey(String niceKey) {
            this.niceKey = niceKey;
        }

        public int getNiceNum() {
            return niceNum;
        }

        public void setNiceNum(int niceNum) {
            this.niceNum = niceNum;
        }

        public String getPictureSrc() {
            return pictureSrc;
        }

        public void setPictureSrc(String pictureSrc) {
            this.pictureSrc = pictureSrc;
        }

        public String getPlayTimeKey() {
            return playTimeKey;
        }

        public void setPlayTimeKey(String playTimeKey) {
            this.playTimeKey = playTimeKey;
        }

        public String getPlaytimes() {
            return playtimes;
        }

        public void setPlaytimes(String playtimes) {
            this.playtimes = playtimes;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class UserBean implements Serializable{
            /**
             * userHead : http://169.254.1.100/ic_ss.jpg
             * userId : 32
             * userName : baobao
             * userPassword : 123456
             * userPhone : 13484741060
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

        public static class CommentListBean implements Serializable{
            /**
             * commentCharacterPictureMediaId : 2
             * commentContent : 图片好看
             * commentDate : 2017-07-31 10:44:08.0
             * commentDictionaryValue : 3
             * commentId : 6
             * commentPid : 0
             * commentUserId : 3
             * nicenum : 234
             * redisnicekey : adfa
             * user : {"userHead":"","userId":3,"userName":"郭凯奇","userPassword":"123456","userPhone":"17600887015","userSex":"男"}
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
            private UserBeanX user;

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

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public static class UserBeanX implements Serializable{
                /**
                 * userHead :
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
        }
    }
}
