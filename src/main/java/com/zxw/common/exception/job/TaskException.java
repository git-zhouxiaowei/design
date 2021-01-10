package com.zxw.common.exception.job;

/**
 * 计划策略异常
 *
 * @author Zhouxw
 */
public class TaskException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Code code;

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx) {
        super(msg, nestedEx);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    /**
     * 任务代码
     */
    public enum Code {
        /**
         * 项
         */
        TASK_EXISTS, NO_TASK_EXISTS, TASK_ALREADY_STARTED, UNKNOWN, CONFIG_ERROR, TASK_NODE_NOT_AVAILABLE
    }
}
