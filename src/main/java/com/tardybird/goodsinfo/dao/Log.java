package com.tardybird.goodsinfo.dao;

import lombok.Getter;

public class Log {

    @Getter
    private Integer type;
    @Getter
    private String actions;
    @Getter
    private Integer status;

    private Log(LogBuilder logBuilder) {
        this.type = logBuilder.type;
        this.actions = logBuilder.actions;
        this.status = logBuilder.status;
    }

    public static class LogBuilder {
        private Integer type;
        private String actions;
        private Integer status;

        public LogBuilder type(Integer type) {
            this.type = type;
            return this;
        }

        public LogBuilder actions(String actions) {
            this.actions = actions;
            return this;
        }

        public LogBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }

}
