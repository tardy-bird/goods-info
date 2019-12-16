package com.tardybird.goodsinfo.domain;

import lombok.Getter;

public class Log {

    @Getter
    private Integer type;
    @Getter
    private String actions;
    @Getter
    private Integer status;
    @Getter
    private Integer actionId;

    private Log(LogBuilder logBuilder) {
        this.type = logBuilder.type;
        this.actions = logBuilder.actions;
        this.status = logBuilder.status;
        this.actionId = logBuilder.actionId;
    }

    public static class LogBuilder {
        private Integer type;
        private String actions;
        private Integer status;
        private Integer actionId;

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

        public LogBuilder actionId(Integer actionId) {
            this.actionId = actionId;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }

}
