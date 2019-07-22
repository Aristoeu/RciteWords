package com.example.recitewords.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WordBean {
    /**
     * tSpeakUrl : http://openapi.youdao.com/ttsapi?q=%E5%85%B3%E9%94%AE&langType=zh-CHS&sign=013C9654D5B71934655385B06486A50D&salt=1563772446921&voice=4&format=mp3&appKey=0bf7efd417f60436
     * returnPhrase : ["key"]
     * web : [{"value":["钥匙"],"key":"Key"},{"value":["控制键","按钮","控制码","控制关键字"],"key":"control key"},{"value":["换档键","移位键","字母变换键"],"key":"shift key"}]
     * query : key
     * translation : ["关键"]
     * errorCode : 0
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=key"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=key"}
     * basic : {"exam_type":["高中","SAT","初中","商务英语"],"phonetic":"kiː","uk-phonetic":"kiː","wfs":[{"wf":{"name":"复数","value":"keys"}}],"uk-speech":"http://openapi.youdao.com/ttsapi?q=key&langType=en&sign=71AD75D03AC81A9B2A457832C03B6EC1&salt=1563772446921&voice=5&format=mp3&appKey=0bf7efd417f60436","explains":["n. 钥匙；关键，密钥；（键盘乐器或电脑键盘的）键；调，声调，色调；答案；图例； 扳手；销子，楔，栓；翼果；板条间首层灰泥；（篮球）罚球区；低岛，礁","v. 键入，用键盘输入；用别针等固定；使与\u2026\u2026符合或关联；使\u2026\u2026与\u2026\u2026共处，相安无事；用钥匙将汽车表面划坏；将（表面）弄粗糙；给（广告）加识别码；成为获得\u2026\u2026的关键","adj. 关键的，主要的","n. (Key)（美）基（人名）"],"us-speech":"http://openapi.youdao.com/ttsapi?q=key&langType=en&sign=71AD75D03AC81A9B2A457832C03B6EC1&salt=1563772446921&voice=6&format=mp3&appKey=0bf7efd417f60436"}
     * l : en2zh-CHS
     * speakUrl : http://openapi.youdao.com/ttsapi?q=key&langType=en&sign=71AD75D03AC81A9B2A457832C03B6EC1&salt=1563772446921&voice=4&format=mp3&appKey=0bf7efd417f60436
     */

    private String tSpeakUrl;
    private String query;
    private String errorCode;
    private DictBean dict;
    private WebdictBean webdict;
    private BasicBean basic;
    private String l;
    private String speakUrl;
    private List<String> returnPhrase;
    private List<WebBean> web;
    private List<String> translation;

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public WebdictBean getWebdict() {
        return webdict;
    }

    public void setWebdict(WebdictBean webdict) {
        this.webdict = webdict;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public List<String> getReturnPhrase() {
        return returnPhrase;
    }

    public void setReturnPhrase(List<String> returnPhrase) {
        this.returnPhrase = returnPhrase;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public static class DictBean {
        /**
         * url : yddict://m.youdao.com/dict?le=eng&q=key
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebdictBean {
        /**
         * url : http://m.youdao.com/dict?le=eng&q=key
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BasicBean {
        /**
         * exam_type : ["高中","SAT","初中","商务英语"]
         * phonetic : kiː
         * uk-phonetic : kiː
         * wfs : [{"wf":{"name":"复数","value":"keys"}}]
         * uk-speech : http://openapi.youdao.com/ttsapi?q=key&langType=en&sign=71AD75D03AC81A9B2A457832C03B6EC1&salt=1563772446921&voice=5&format=mp3&appKey=0bf7efd417f60436
         * explains : ["n. 钥匙；关键，密钥；（键盘乐器或电脑键盘的）键；调，声调，色调；答案；图例； 扳手；销子，楔，栓；翼果；板条间首层灰泥；（篮球）罚球区；低岛，礁","v. 键入，用键盘输入；用别针等固定；使与\u2026\u2026符合或关联；使\u2026\u2026与\u2026\u2026共处，相安无事；用钥匙将汽车表面划坏；将（表面）弄粗糙；给（广告）加识别码；成为获得\u2026\u2026的关键","adj. 关键的，主要的","n. (Key)（美）基（人名）"]
         * us-speech : http://openapi.youdao.com/ttsapi?q=key&langType=en&sign=71AD75D03AC81A9B2A457832C03B6EC1&salt=1563772446921&voice=6&format=mp3&appKey=0bf7efd417f60436
         */

        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        @SerializedName("uk-speech")
        private String ukspeech;
        @SerializedName("us-speech")
        private String usspeech;
        private List<String> exam_type;
        private List<WfsBean> wfs;
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public String getUkspeech() {
            return ukspeech;
        }

        public void setUkspeech(String ukspeech) {
            this.ukspeech = ukspeech;
        }

        public String getUsspeech() {
            return usspeech;
        }

        public void setUsspeech(String usspeech) {
            this.usspeech = usspeech;
        }

        public List<String> getExam_type() {
            return exam_type;
        }

        public void setExam_type(List<String> exam_type) {
            this.exam_type = exam_type;
        }

        public List<WfsBean> getWfs() {
            return wfs;
        }

        public void setWfs(List<WfsBean> wfs) {
            this.wfs = wfs;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }

        public static class WfsBean {
            /**
             * wf : {"name":"复数","value":"keys"}
             */

            private WfBean wf;

            public WfBean getWf() {
                return wf;
            }

            public void setWf(WfBean wf) {
                this.wf = wf;
            }

            public static class WfBean {
                /**
                 * name : 复数
                 * value : keys
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }

    public static class WebBean {
        /**
         * value : ["钥匙"]
         * key : Key
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
