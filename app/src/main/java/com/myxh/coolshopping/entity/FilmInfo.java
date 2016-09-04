package com.myxh.coolshopping.entity;

import java.util.List;

/**
 * Created by asus on 2016/9/3.
 */
public class FilmInfo {

    /**
     * ret : 10000
     * msg : 成功
     * result : [{"filmId":"60310","filmName":"陆垚知马俐","brief":"","short_brief":"文章处女导包贝尔恋宋佳","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"6.0","duration":"90","status":0,"showCinemasCount":7,"showSchedulesCount":100,"have_schedule":1,"media":"XMTU3MzcwNDk1Mg","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/29/60310-13496.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/29/60310-13496.jpg"},{"filmId":"60309","filmName":"快手枪手快枪手","brief":"","short_brief":"林更新腾格尔化身\"大丈夫\" 能屈能伸变\"","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"6.7","duration":"90","status":0,"showCinemasCount":9,"showSchedulesCount":100,"have_schedule":1,"media":"XMTU5NDUwNDc2MA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/29/60309-54040.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/29/60309-54040.jpg"},{"filmId":"60307","filmName":"惊天大逆转","brief":"","short_brief":"钟汉良李政宰对战《惊天大逆转》","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"7.0","duration":"90","status":0,"showCinemasCount":8,"showSchedulesCount":100,"have_schedule":1,"media":"XMTYwNjQ2MjMyNA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/29/60307-44322.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/29/60307-44322.jpg"},{"filmId":"60283","filmName":"大鱼海棠","brief":"","short_brief":"中国动画电影大制作十二年神坑终于被填","dimensional":"1","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"7.9","duration":"105","status":0,"showCinemasCount":9,"showSchedulesCount":100,"have_schedule":1,"media":"XMTU5MzU0NjY5Mg","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/17/60283-46324.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/17/60283-46324.jpg"},{"filmId":"60284","filmName":"寒战2","brief":"","short_brief":"郭富城梁家辉针锋相对 \u201c内斗\u201d升级","dimensional":"1","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"7.8","duration":"110","status":0,"showCinemasCount":6,"showSchedulesCount":100,"have_schedule":1,"media":"XMTUzMzA0OTA4NA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/20/60284-94753.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/20/60284-94753.jpg"},{"filmId":"60282","filmName":"忍者神龟2：破影而出","brief":"","short_brief":"神龟组最强伐木累战队","dimensional":"1","imax":"0","releaseDate":"2016-07-02","wekDate":"今天","starCode":"3","grade":"6.5","duration":"112","status":0,"showCinemasCount":11,"showSchedulesCount":32,"have_schedule":1,"media":"XMTU1NzYyNDc4NA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/17/60282-14778.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/17/60282-14778.jpg"},{"filmId":"60225","filmName":"路边野餐","brief":"","short_brief":" 文艺类型片又一新作 囊括多项国际大奖","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"8.1","duration":"113","status":0,"showCinemasCount":7,"showSchedulesCount":11,"have_schedule":1,"media":"XMTM1Mjk5MjE0MA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201605/13/60225-60462.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201605/13/60225-60462.jpg"},{"filmId":"60308","filmName":"超级保镖","brief":"","short_brief":"洪金宝成龙弟子集结 打造最狠功夫片","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"6.4","duration":"90","status":0,"showCinemasCount":3,"showSchedulesCount":7,"have_schedule":1,"media":"XMTYwODg2OTY4NA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/29/60308-86492.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/29/60308-86492.jpg"},{"filmId":"60290","filmName":"致青春·原来你还在这里","brief":"","short_brief":"吴亦凡刘亦菲组\u201c非凡CP\u201d","dimensional":"0","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"7.1","duration":"97","status":0,"showCinemasCount":14,"showSchedulesCount":81,"have_schedule":1,"media":"XMTYwNTQwMjA1Ng","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/20/60290-32407.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/20/60290-32407.jpg"},{"filmId":"60289","filmName":"发条城市","brief":"","short_brief":"王宁王自健修睿三贱客搞笑上演跑酷大逃亡","dimensional":"0","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"6.8","duration":"113","status":0,"showCinemasCount":2,"showSchedulesCount":3,"have_schedule":1,"media":"XMTU5NjE4NDc4MA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/20/60289-74656.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/20/60289-74656.jpg"},{"filmId":"60286","filmName":"摇滚藏獒","brief":"","short_brief":"好莱坞制作定造中国故事，藏獒波弟暖萌励志","dimensional":"1","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"7.0","duration":"85","status":0,"showCinemasCount":2,"showSchedulesCount":6,"have_schedule":1,"media":"XMTYwMTg4NjE2NA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/17/60286-89372.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/17/60286-89372.jpg"},{"filmId":"60252","filmName":"独立日：卷土重来","brief":"","short_brief":"时隔20年，重启经典电影续集","dimensional":"1","imax":"0","releaseDate":"2016-06-24","wekDate":"今天","starCode":"3","grade":"6.7","duration":"120","status":0,"showCinemasCount":3,"showSchedulesCount":7,"have_schedule":1,"media":"XMTU4NDc1ODA4OA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201605/30/60252-89316.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201605/30/60252-89316.jpg"},{"filmId":"60254","filmName":"惊天魔盗团2","brief":"","short_brief":"全新炫技上演中国大秀 哈利波特正邪莫辨","dimensional":"1","imax":"0","releaseDate":"2016-06-24","wekDate":"今天","starCode":"3","grade":"7.0","duration":"115","status":0,"showCinemasCount":6,"showSchedulesCount":16,"have_schedule":1,"media":"XMTM5MDE3MDgyNA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201605/30/60254-41881.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201605/30/60254-41881.jpg"},{"filmId":"60244","filmName":"海底总动员2：多莉去哪儿","brief":"","short_brief":"蓝藻鱼多莉千里寻亲","dimensional":"1","imax":"0","releaseDate":"2016-06-17","wekDate":"今天","starCode":"3","grade":"7.5","duration":"97","status":0,"showCinemasCount":4,"showSchedulesCount":9,"have_schedule":1,"media":"XMTU4MjgxOTQyNA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201605/25/60244-89125.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201605/25/60244-89125.jpg"},{"filmId":"60281","filmName":"赏金猎人","brief":"","short_brief":"李敏镐钟汉良携手抢金","dimensional":"0","imax":"0","releaseDate":"2016-07-01","wekDate":"今天","starCode":"3","grade":"7.0","duration":"105","status":0,"showCinemasCount":1,"showSchedulesCount":2,"have_schedule":1,"media":"XMTU4NTQxMDE0OA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/17/60281-83191.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/17/60281-83191.jpg"},{"filmId":"60319","filmName":"泰山归来：险战丛林","brief":"","short_brief":"泰山、小丑女上演人猿虐恋","dimensional":"1","imax":"0","releaseDate":"2016-07-19","wekDate":"07月19日上映","starCode":"3","grade":"6.4","duration":"109","status":0,"showCinemasCount":4,"showSchedulesCount":4,"have_schedule":1,"media":"XMTYyNjI3ODgyOA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201607/06/60319-76235.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201607/06/60319-76235.jpg"},{"filmId":"60285","filmName":"张震讲故事之合租屋","brief":"","short_brief":"四室一厅鬼影重重","dimensional":"0","imax":"0","releaseDate":"2016-07-08","wekDate":"今天","starCode":"3","grade":"6.2","duration":"87","status":0,"showCinemasCount":3,"showSchedulesCount":3,"have_schedule":1,"media":"XMTU2NjAzMDMwMA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201606/17/60285-56649.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201606/17/60285-56649.jpg"},{"filmId":"60341","filmName":"笔仙撞碟仙","brief":"","short_brief":"中韩合拍经典恐怖IP 两大邪灵对对碰","dimensional":"0","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"6.0","duration":"90","status":0,"showCinemasCount":6,"showSchedulesCount":11,"have_schedule":1,"media":"XMTYyNzAzMzAxMg","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201607/14/60341-47852.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201607/14/60341-47852.jpg"},{"filmId":"60342","filmName":"超能太阳鸭","brief":"","short_brief":"中美俄合拍3D动画电影 末日决战强势来袭","dimensional":"1","imax":"0","releaseDate":"2016-07-15","wekDate":"明天上映","starCode":"3","grade":"5.7","duration":"86","status":0,"showCinemasCount":12,"showSchedulesCount":38,"have_schedule":1,"media":"XMTYwMTAxNTQ4OA","imageUrl":"http://f1.lashouimg.com/cinema/film/150/201607/14/60342-35395.jpg","posterUrl":"http://f1.lashouimg.com/cinema/film/480_app/201607/14/60342-35395.jpg"}]
     * token :
     */

    private int ret;
    private String msg;
    private String token;
    /**
     * filmId : 60310
     * filmName : 陆垚知马俐
     * brief :
     * short_brief : 文章处女导包贝尔恋宋佳
     * dimensional : 0
     * imax : 0
     * releaseDate : 2016-07-15
     * wekDate : 明天上映
     * starCode : 3
     * grade : 6.0
     * duration : 90
     * status : 0
     * showCinemasCount : 7
     * showSchedulesCount : 100
     * have_schedule : 1
     * media : XMTU3MzcwNDk1Mg
     * imageUrl : http://f1.lashouimg.com/cinema/film/150/201606/29/60310-13496.jpg
     * posterUrl : http://f1.lashouimg.com/cinema/film/480_app/201606/29/60310-13496.jpg
     */

    private List<ResultBean> result;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String filmId;
        private String filmName;
        private String brief;
        private String short_brief;
        private String dimensional;
        private String imax;
        private String releaseDate;
        private String wekDate;
        private String starCode;
        private String grade;
        private String duration;
        private int status;
        private int showCinemasCount;
        private int showSchedulesCount;
        private int have_schedule;
        private String media;
        private String imageUrl;
        private String posterUrl;

        public String getFilmId() {
            return filmId;
        }

        public void setFilmId(String filmId) {
            this.filmId = filmId;
        }

        public String getFilmName() {
            return filmName;
        }

        public void setFilmName(String filmName) {
            this.filmName = filmName;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getShort_brief() {
            return short_brief;
        }

        public void setShort_brief(String short_brief) {
            this.short_brief = short_brief;
        }

        public String getDimensional() {
            return dimensional;
        }

        public void setDimensional(String dimensional) {
            this.dimensional = dimensional;
        }

        public String getImax() {
            return imax;
        }

        public void setImax(String imax) {
            this.imax = imax;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getWekDate() {
            return wekDate;
        }

        public void setWekDate(String wekDate) {
            this.wekDate = wekDate;
        }

        public String getStarCode() {
            return starCode;
        }

        public void setStarCode(String starCode) {
            this.starCode = starCode;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getShowCinemasCount() {
            return showCinemasCount;
        }

        public void setShowCinemasCount(int showCinemasCount) {
            this.showCinemasCount = showCinemasCount;
        }

        public int getShowSchedulesCount() {
            return showSchedulesCount;
        }

        public void setShowSchedulesCount(int showSchedulesCount) {
            this.showSchedulesCount = showSchedulesCount;
        }

        public int getHave_schedule() {
            return have_schedule;
        }

        public void setHave_schedule(int have_schedule) {
            this.have_schedule = have_schedule;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }
    }
}
