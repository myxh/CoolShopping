package com.myxh.coolshopping.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 2016/9/6.
 */
public class GoodsDetailInfo implements Serializable {

    /**
     * ret : 10000
     * msg : 成功
     * result : {"goods_id":"12806690","goods_type":"1","product":"味味海鲜大排档（曾厝垵店）","title":"现金抵用1次，营业时间内均可使用","short_title":"现金抵用1次，营业时间内均可使用","is_new":0,"value":"10","price":"8.5","is_appointment":1,"left_time":12807178,"goods_show_type":5,"images":[{"width":440,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-440x280.jpg"},{"width":220,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-220x140.jpg"},{"width":100,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-110x70.jpg"}],"if_join":0,"is_collected":0,"details":"","notice":"","detail_imags":["http://d2.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-440x280.jpg","http://f3.lashouimg.com/cms/M00/D1/98/CqgBVFVdQ2aASTe3AAGbO3zKmFE473.jpg","http://f3.lashouimg.com/cms/M00/D4/B8/CqgBHlVdQ2eAUUA6AAFQVK1NCBk566.jpg","http://f3.lashouimg.com/cms/M00/D4/AF/CqgBJlVdQ2eACEKbAAC3XK1Uxag086.jpg","http://f3.lashouimg.com/cms/M00/D4/AF/CqgBJlVdQ2eAQbnBAAFtjILGkv8935.jpg","http://f3.lashouimg.com/cms/M00/D5/12/CqgBHlVdiFeAPSVXAAEukL8vrkY044.jpg","http://f3.lashouimg.com/cms/M00/D5/09/CqgBJlVdiFmAE4NEAAEeR4bmMwA335.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5CAa5yNAAD6f_y4Wg4719.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5GALGF5AADoOKFfa-k092.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5GAFV7qAAE1YW1zLWU420.jpg","http://f3.lashouimg.com/cms/M00/D4/B9/CqgBHlVdQ5KADdOzAADYNreniis883.jpg","http://f3.lashouimg.com/cms/M00/D1/99/CqgBVFVdQ5OAGcoVAAEaSWXYByY870.jpg"],"comment":{"total_score":0,"count":0,"items":[],"tags":{}},"signiture":{},"group_recommend":[],"pay_mobile":1,"type":"1","is_self":"0","address_id":0,"cinema_id":"","btn_disabled":0,"lashou_price":{}}
     * token :
     */

    private int ret;
    private String msg;
    /**
     * goods_id : 12806690
     * goods_type : 1
     * product : 味味海鲜大排档（曾厝垵店）
     * title : 现金抵用1次，营业时间内均可使用
     * short_title : 现金抵用1次，营业时间内均可使用
     * is_new : 0
     * value : 10
     * price : 8.5
     * is_appointment : 1
     * left_time : 12807178
     * goods_show_type : 5
     * images : [{"width":440,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-440x280.jpg"},{"width":220,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-220x140.jpg"},{"width":100,"image":"http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-110x70.jpg"}]
     * if_join : 0
     * is_collected : 0
     * details :
     * notice :
     * detail_imags : ["http://d2.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-440x280.jpg","http://f3.lashouimg.com/cms/M00/D1/98/CqgBVFVdQ2aASTe3AAGbO3zKmFE473.jpg","http://f3.lashouimg.com/cms/M00/D4/B8/CqgBHlVdQ2eAUUA6AAFQVK1NCBk566.jpg","http://f3.lashouimg.com/cms/M00/D4/AF/CqgBJlVdQ2eACEKbAAC3XK1Uxag086.jpg","http://f3.lashouimg.com/cms/M00/D4/AF/CqgBJlVdQ2eAQbnBAAFtjILGkv8935.jpg","http://f3.lashouimg.com/cms/M00/D5/12/CqgBHlVdiFeAPSVXAAEukL8vrkY044.jpg","http://f3.lashouimg.com/cms/M00/D5/09/CqgBJlVdiFmAE4NEAAEeR4bmMwA335.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5CAa5yNAAD6f_y4Wg4719.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5GALGF5AADoOKFfa-k092.jpg","http://f3.lashouimg.com/cms/M00/D4/B0/CqgBJlVdQ5GAFV7qAAE1YW1zLWU420.jpg","http://f3.lashouimg.com/cms/M00/D4/B9/CqgBHlVdQ5KADdOzAADYNreniis883.jpg","http://f3.lashouimg.com/cms/M00/D1/99/CqgBVFVdQ5OAGcoVAAEaSWXYByY870.jpg"]
     * comment : {"total_score":0,"count":0,"items":[],"tags":{}}
     * signiture : {}
     * group_recommend : []
     * pay_mobile : 1
     * type : 1
     * is_self : 0
     * address_id : 0
     * cinema_id :
     * btn_disabled : 0
     * lashou_price : {}
     */

    private ResultBean result;
    private String token;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class ResultBean implements Serializable{
        private String goods_id;
        private String goods_type;
        private String product;
        private String title;
        private String short_title;
        private int is_new;
        private String value;
        private String price;
        private int is_appointment;
        private int left_time;
        private int goods_show_type;
        private int if_join;
        private int is_collected;
        private String details;
        private String notice;
        /**
         * total_score : 0
         * count : 0
         * items : []
         * tags : {}
         */

        private CommentBean comment;
        private SignitureBean signiture;
        private int pay_mobile;
        private String type;
        private String is_self;
        private int address_id;
        private String cinema_id;
        private int btn_disabled;
        private LashouPriceBean lashou_price;
        /**
         * width : 440
         * image : http://s1.lashouimg.com/cms/M00/D1/F2/CqgBVFVdiFKAdvGIAAF6cvn7-CI784-440x280.jpg
         */

        private List<ImagesBean> images;
        private List<String> detail_imags;
        private List<?> group_recommend;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(int is_appointment) {
            this.is_appointment = is_appointment;
        }

        public int getLeft_time() {
            return left_time;
        }

        public void setLeft_time(int left_time) {
            this.left_time = left_time;
        }

        public int getGoods_show_type() {
            return goods_show_type;
        }

        public void setGoods_show_type(int goods_show_type) {
            this.goods_show_type = goods_show_type;
        }

        public int getIf_join() {
            return if_join;
        }

        public void setIf_join(int if_join) {
            this.if_join = if_join;
        }

        public int getIs_collected() {
            return is_collected;
        }

        public void setIs_collected(int is_collected) {
            this.is_collected = is_collected;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public SignitureBean getSigniture() {
            return signiture;
        }

        public void setSigniture(SignitureBean signiture) {
            this.signiture = signiture;
        }

        public int getPay_mobile() {
            return pay_mobile;
        }

        public void setPay_mobile(int pay_mobile) {
            this.pay_mobile = pay_mobile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_self() {
            return is_self;
        }

        public void setIs_self(String is_self) {
            this.is_self = is_self;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getCinema_id() {
            return cinema_id;
        }

        public void setCinema_id(String cinema_id) {
            this.cinema_id = cinema_id;
        }

        public int getBtn_disabled() {
            return btn_disabled;
        }

        public void setBtn_disabled(int btn_disabled) {
            this.btn_disabled = btn_disabled;
        }

        public LashouPriceBean getLashou_price() {
            return lashou_price;
        }

        public void setLashou_price(LashouPriceBean lashou_price) {
            this.lashou_price = lashou_price;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<String> getDetail_imags() {
            return detail_imags;
        }

        public void setDetail_imags(List<String> detail_imags) {
            this.detail_imags = detail_imags;
        }

        public List<?> getGroup_recommend() {
            return group_recommend;
        }

        public void setGroup_recommend(List<?> group_recommend) {
            this.group_recommend = group_recommend;
        }

        public static class CommentBean implements Serializable{
        }

        public static class SignitureBean implements Serializable{
        }

        public static class LashouPriceBean implements Serializable{
        }

        public static class ImagesBean implements Serializable {
            private int width;
            private String image;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
