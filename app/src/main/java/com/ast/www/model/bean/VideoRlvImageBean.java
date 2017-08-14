package com.ast.www.model.bean;

import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/11 21:16
 * Title:
 * Text:
 */

public class VideoRlvImageBean {

    /**
     * code : 200
     * media : [{"list":[],"mediaBadNum":2,"mediaCommentNum":0,"mediaDescription":"haha","mediaDictionaryValue":1,"mediaForwardNum":0,"mediaId":11,"mediaName":"jiatao.mp4","mediaPictureSrc":"http://192.168.1.100/qw.jpeg","mediaPlaytimes":0,"mediaSrc":"http://192.168.1.100/jiatao.mp4","mediaUptime":"2017-08-01 10:32:03.0","mediaUserId":32,"niceNum":288,"redisMediaBadKey":"55MFs5658262oM864M5o35aa88M88aa7bad","redisMediaCommentKey":"55MFs5658262oM864M5o35aa88M88aa7comment","redisMediaForwardKey":"55MFs5658262oM864M5o35aa88M88aa7forward","redisMediaNiceKey":"55MFs5658262oM864M5o35aa88M88aa7nice","redisMediaPlaytimesKey":"55MFs5658262oM864M5o35aa88M88aa7play","status":1,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userRole":0,"userSex":"男","userSignature":"编辑个性签名"}},{"list":[],"mediaBadNum":0,"mediaCommentNum":0,"mediaDescription":"nihaoma","mediaDictionaryValue":1,"mediaForwardNum":0,"mediaId":14,"mediaName":"jiatao.mp4","mediaPictureSrc":"http://192.168.1.100/qw.jpeg","mediaPlaytimes":0,"mediaSrc":"http://192.168.1.100/zhangyuanayng.mp4","mediaUptime":"2017-08-01 11:23:04.0","mediaUserId":32,"niceNum":0,"redisMediaBadKey":"7s63sas5Maa1f0M44s125ff120411fs6bad","redisMediaCommentKey":"7s63sas5Maa1f0M44s125ff120411fs6comment","redisMediaForwardKey":"7s63sas5Maa1f0M44s125ff120411fs6forward","redisMediaNiceKey":"7s63sas5Maa1f0M44s125ff120411fs6nice","redisMediaPlaytimesKey":"7s63sas5Maa1f0M44s125ff120411fs6play","status":1,"user":{"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userRole":0,"userSex":"男","userSignature":"编辑个性签名"}}]
     */

    private String code;
    private List<MediaBean> media;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MediaBean> getMedia() {
        return media;
    }

    public void setMedia(List<MediaBean> media) {
        this.media = media;
    }

    public static class MediaBean {
        /**
         * list : []
         * mediaBadNum : 2
         * mediaCommentNum : 0
         * mediaDescription : haha
         * mediaDictionaryValue : 1
         * mediaForwardNum : 0
         * mediaId : 11
         * mediaName : jiatao.mp4
         * mediaPictureSrc : http://192.168.1.100/qw.jpeg
         * mediaPlaytimes : 0
         * mediaSrc : http://192.168.1.100/jiatao.mp4
         * mediaUptime : 2017-08-01 10:32:03.0
         * mediaUserId : 32
         * niceNum : 288
         * redisMediaBadKey : 55MFs5658262oM864M5o35aa88M88aa7bad
         * redisMediaCommentKey : 55MFs5658262oM864M5o35aa88M88aa7comment
         * redisMediaForwardKey : 55MFs5658262oM864M5o35aa88M88aa7forward
         * redisMediaNiceKey : 55MFs5658262oM864M5o35aa88M88aa7nice
         * redisMediaPlaytimesKey : 55MFs5658262oM864M5o35aa88M88aa7play
         * status : 1
         * user : {"userHead":"http://169.254.1.100/ic_ss.jpg","userId":32,"userName":"baobao","userPassword":"123456","userPhone":"13484741060","userRole":0,"userSex":"男","userSignature":"编辑个性签名"}
         */

        private int mediaBadNum;
        private int mediaCommentNum;
        private String mediaDescription;
        private int mediaDictionaryValue;
        private int mediaForwardNum;
        private int mediaId;
        private String mediaName;
        private String mediaPictureSrc;
        private int mediaPlaytimes;
        private String mediaSrc;
        private String mediaUptime;
        private int mediaUserId;
        private int niceNum;
        private String redisMediaBadKey;
        private String redisMediaCommentKey;
        private String redisMediaForwardKey;
        private String redisMediaNiceKey;
        private String redisMediaPlaytimesKey;
        private int status;
        private UserBean user;
        private List<?> list;

        public int getMediaBadNum() {
            return mediaBadNum;
        }

        public void setMediaBadNum(int mediaBadNum) {
            this.mediaBadNum = mediaBadNum;
        }

        public int getMediaCommentNum() {
            return mediaCommentNum;
        }

        public void setMediaCommentNum(int mediaCommentNum) {
            this.mediaCommentNum = mediaCommentNum;
        }

        public String getMediaDescription() {
            return mediaDescription;
        }

        public void setMediaDescription(String mediaDescription) {
            this.mediaDescription = mediaDescription;
        }

        public int getMediaDictionaryValue() {
            return mediaDictionaryValue;
        }

        public void setMediaDictionaryValue(int mediaDictionaryValue) {
            this.mediaDictionaryValue = mediaDictionaryValue;
        }

        public int getMediaForwardNum() {
            return mediaForwardNum;
        }

        public void setMediaForwardNum(int mediaForwardNum) {
            this.mediaForwardNum = mediaForwardNum;
        }

        public int getMediaId() {
            return mediaId;
        }

        public void setMediaId(int mediaId) {
            this.mediaId = mediaId;
        }

        public String getMediaName() {
            return mediaName;
        }

        public void setMediaName(String mediaName) {
            this.mediaName = mediaName;
        }

        public String getMediaPictureSrc() {
            return mediaPictureSrc;
        }

        public void setMediaPictureSrc(String mediaPictureSrc) {
            this.mediaPictureSrc = mediaPictureSrc;
        }

        public int getMediaPlaytimes() {
            return mediaPlaytimes;
        }

        public void setMediaPlaytimes(int mediaPlaytimes) {
            this.mediaPlaytimes = mediaPlaytimes;
        }

        public String getMediaSrc() {
            return mediaSrc;
        }

        public void setMediaSrc(String mediaSrc) {
            this.mediaSrc = mediaSrc;
        }

        public String getMediaUptime() {
            return mediaUptime;
        }

        public void setMediaUptime(String mediaUptime) {
            this.mediaUptime = mediaUptime;
        }

        public int getMediaUserId() {
            return mediaUserId;
        }

        public void setMediaUserId(int mediaUserId) {
            this.mediaUserId = mediaUserId;
        }

        public int getNiceNum() {
            return niceNum;
        }

        public void setNiceNum(int niceNum) {
            this.niceNum = niceNum;
        }

        public String getRedisMediaBadKey() {
            return redisMediaBadKey;
        }

        public void setRedisMediaBadKey(String redisMediaBadKey) {
            this.redisMediaBadKey = redisMediaBadKey;
        }

        public String getRedisMediaCommentKey() {
            return redisMediaCommentKey;
        }

        public void setRedisMediaCommentKey(String redisMediaCommentKey) {
            this.redisMediaCommentKey = redisMediaCommentKey;
        }

        public String getRedisMediaForwardKey() {
            return redisMediaForwardKey;
        }

        public void setRedisMediaForwardKey(String redisMediaForwardKey) {
            this.redisMediaForwardKey = redisMediaForwardKey;
        }

        public String getRedisMediaNiceKey() {
            return redisMediaNiceKey;
        }

        public void setRedisMediaNiceKey(String redisMediaNiceKey) {
            this.redisMediaNiceKey = redisMediaNiceKey;
        }

        public String getRedisMediaPlaytimesKey() {
            return redisMediaPlaytimesKey;
        }

        public void setRedisMediaPlaytimesKey(String redisMediaPlaytimesKey) {
            this.redisMediaPlaytimesKey = redisMediaPlaytimesKey;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }

        public static class UserBean {
            /**
             * userHead : http://169.254.1.100/ic_ss.jpg
             * userId : 32
             * userName : baobao
             * userPassword : 123456
             * userPhone : 13484741060
             * userRole : 0
             * userSex : 男
             * userSignature : 编辑个性签名
             */

            private String userHead;
            private int userId;
            private String userName;
            private String userPassword;
            private String userPhone;
            private int userRole;
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

            public int getUserRole() {
                return userRole;
            }

            public void setUserRole(int userRole) {
                this.userRole = userRole;
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
