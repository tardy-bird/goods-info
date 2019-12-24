package com.tardybird.goodsinfo.domain;

import lombok.Getter;

/**
 * @author nick
 */
public class Log {

    /**
     * 操作的类型
     * 0 查询，1 插入，2修改，3删除(逻辑删除)
     */
    @Getter
    private Integer type;
    /**
     * 操作的动作
     */
    @Getter
    private String actions;
    /**
     * 操作的状态，0表示操作失败，1表示操作成功
     */
    @Getter
    private Integer statusCode;
    /**
     * 操作对象的ID
     */
    @Getter
    private Integer actionId;

    private Log(LogBuilder logBuilder) {
        this.type = logBuilder.type;
        this.actions = logBuilder.actions;
        this.statusCode = logBuilder.statusCode;
        this.actionId = logBuilder.actionId;
    }

    /**
     * Builder设计模式
     */
    public static class LogBuilder {
        private Integer type;
        private String actions;
        private Integer statusCode;
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
            this.statusCode = status;
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
