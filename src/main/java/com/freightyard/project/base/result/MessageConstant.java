package com.freightyard.project.base.result;

/**
 *
 * @author 梁晋一
 * @date 2020/2/1
 */
public enum MessageConstant {

    /**
     * 操作成功
     */
    MESSAGE_ALERT_SUCCESS("操作成功"),
    /**
     * 操作失败
     */
    MESSAGE_ALERT_ERROR("操作失败");

    public String msg;

    MessageConstant(String msg) {
        this.msg = msg;
    }
}
