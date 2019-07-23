package com.example.recitewords.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wording {

    /**
     * dict : {"-num":"219","-id":"219","-name":"219","key":"a","ps":["ə","eɪ"],"pron":[" http://res.iciba.com/resource/amp3/oxford/0/22/97/2297067c3e5d25a542d1c1268d474d95.mp3 "," http://res-tts.iciba.com/0/c/c/0cc175b9c0f1b6a831c399e269772661.mp3 "],"pos":"art.","acceptation":"一（个）；每一（个）；任一（个）； ","sent":[{"orig":" If a is any ideal of A, the radical of a is r ( a ). ","trans":" 设a是A中的任意理想, 它的根是r ( a ). "},{"orig":" A subset S of a ring A is a subring of A. ","trans":" 环A的子集S叫作A的子环. "},{"orig":" Case A we called here is a type of SRMCS based on designing method A. ","trans":" 由设计方案A研制的××××发动机壳体称为A壳体. "},{"orig":" There's a good A - road going North -- the A 1. ","trans":" 有一条良好的A 级 公路通往北方--A级1号公路. "},{"orig":" Personality is a large extent inherent A - type pa A - type offspring. ","trans":" 性格在很大程度上是先天形成的 \u2014\u2014 A型性格A型性格的子女. "}]}
     */

    private DictBean dict;

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public static class DictBean {
        /**
         * -num : 219
         * -id : 219
         * -name : 219
         * key : a
         * ps : ["ə","eɪ"]
         * pron : [" http://res.iciba.com/resource/amp3/oxford/0/22/97/2297067c3e5d25a542d1c1268d474d95.mp3 "," http://res-tts.iciba.com/0/c/c/0cc175b9c0f1b6a831c399e269772661.mp3 "]
         * pos : art.
         * acceptation : 一（个）；每一（个）；任一（个）；
         * sent : [{"orig":" If a is any ideal of A, the radical of a is r ( a ). ","trans":" 设a是A中的任意理想, 它的根是r ( a ). "},{"orig":" A subset S of a ring A is a subring of A. ","trans":" 环A的子集S叫作A的子环. "},{"orig":" Case A we called here is a type of SRMCS based on designing method A. ","trans":" 由设计方案A研制的××××发动机壳体称为A壳体. "},{"orig":" There's a good A - road going North -- the A 1. ","trans":" 有一条良好的A 级 公路通往北方--A级1号公路. "},{"orig":" Personality is a large extent inherent A - type pa A - type offspring. ","trans":" 性格在很大程度上是先天形成的 \u2014\u2014 A型性格A型性格的子女. "}]
         */

        @SerializedName("-num")
        private String num;
        @SerializedName("-id")
        private String id;
        @SerializedName("-name")
        private String name;
        private String key;
        private String pos;
        private String acceptation;
        private List<String> ps;
        private List<String> pron;
        private List<SentBean> sent;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getAcceptation() {
            return acceptation;
        }

        public void setAcceptation(String acceptation) {
            this.acceptation = acceptation;
        }

        public List<String> getPs() {
            return ps;
        }

        public void setPs(List<String> ps) {
            this.ps = ps;
        }

        public List<String> getPron() {
            return pron;
        }

        public void setPron(List<String> pron) {
            this.pron = pron;
        }

        public List<SentBean> getSent() {
            return sent;
        }

        public void setSent(List<SentBean> sent) {
            this.sent = sent;
        }

        public static class SentBean {
            /**
             * orig :  If a is any ideal of A, the radical of a is r ( a ).
             * trans :  设a是A中的任意理想, 它的根是r ( a ).
             */

            private String orig;
            private String trans;

            public String getOrig() {
                return orig;
            }

            public void setOrig(String orig) {
                this.orig = orig;
            }

            public String getTrans() {
                return trans;
            }

            public void setTrans(String trans) {
                this.trans = trans;
            }
        }
    }
}
