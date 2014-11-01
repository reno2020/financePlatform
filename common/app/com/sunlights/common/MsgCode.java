package com.sunlights.common;

/***
 * XXX_XXXX
 *
 * _前为业务类型
 * _后4位为数字：第一位：0(info) 1(warn) 2(error业务逻辑) 3(fatal系统异常)
 *             第二位：业务代码
 *                    0 公用部分
 *                    1 注册登录
 *                    2 账户中心
 */
public enum MsgCode {

    OPERATE_SUCCESS("0000", "操作成功",""),
    ACCESS_FAIL("2001", "访问失败", "传入参数'{0}'不能为空"),
    LOGIN_TIMEOUT("2002","登录超时", "请重新登录"),
    MISSING_PARAM_CONFIG("2003", "参数未配置", "参数编码：{0}"),
    PARAM_IS_NOT_NUMBER("2004", "参数不是数字类型", "参数编码：{0}"),
    REGISTRY_SUCCESS("0100","注册成功",""),
    LOGIN_SUCCESS("0101","登录成功",""),
    PASSWORD_CHANGE_SUCCESS("0102","修改密码成功",""),
    GESTURE_PASSWORD_SUCCESS("0103","手势密码设置成功",""),
    LOGOUT_SUCCESS("0104","登出成功",""),
    PHONE_NUMBER_NOT_REGISTRY("2100","该手机号未注册",""),
    PHONE_NUMBER_ALREADY_REGISTRY("2101","该手机号已注册",""),
    CERTIFY_OVER_MAX_TIME("2102","验证码超过最大次数",""),
    CERTIFY_ERROR("2103","验证码不正确","请重新输入"),
    CERTIFY_TIMEOUT("2104","验证码超时失效","请重新获取"),
    CERTIFY_NONE("2105","未获取验证码","请获取验证码"),
    PASSWORD_ERROR_OVER_COUNT("2106","密码错误次数过多","约{0}分钟后再试"),
    PASSWORD_ERROR("2107","密码错误","剩余次数{0}"),
    GESTURE_PASSWORD_ERROR("2108","手势密码错误","剩余次数{0}"),
    GESTURE_LOGIN_ERROR_OVER_COUNT("2109","手势登录失败超过最大次数","请换密码登录"),
    PASSWORD_CONFIRM_ERROR("2110","确认密码错误",""),
    GESTURE_NONE("2111","手势未设置","请先开启手势"),
    CERTIFY_DEVICE_NOT_MATCH("2112","验证码与设备号不匹配", "请重新获取"),
    TRAD_PASSWORD_RESET_SUCCESS("0200","交易密码重置成功",""),
    CERTIFY_SUCCESS("0201","认证成功",""),
    NAME_OR_ID_ERROR("2200","真实姓名或身份证号错误","请重新填写"),
    CERTIFY_INFO_FAIL("2201","认证失败","{0}"),
    TRADE_PASSWORD_ERROR("2202","交易密码错误",""),
    CERTIFY_NAME_FAIL("3200","实名认证接口失败","{0}"),
    ACCOUNT_NOT_EXIST("4100","不存在该账户","{0}"),
    SEARCH_FAIL_EMPTY_PROTOCOL_NO("2300","查询失败","协议编号不能为空"),
    SEARCH_FAIL_PROTOCOL_NONE("2301","查询失败","没有该协议"),
    SEARCH_FAIL_TYPE_EMPTY("2302", "查询失败", "类型不能为空"),
    BANK_CARD_ADD_SUCCESS("0210","操作成功","银行卡添加成功"),
    BANK_CARD_DELETE_SUCCESS("0211","操作成功","银行卡删除成功"),
    BANK_NAME_CERTIFY_FAIL("2210","请先实名认证",""),
    BIND_CARD_FAIL_ALREADY_BIND("2211","绑卡失败","该银行卡已被绑定"),
    BANK_CARD_ADD_FAIL("2212","银行卡添加失败"),
    BANK_CARD_DELETE_FAIL("2213","银行卡删除失败"),
    BANK_CARD_CERTIFY_FAIL("2214","银行卡验证失败"),
    BIND_CARD_FAIL_EMPTY_BANK("2215","绑卡失败","银行不能为空")
    ;

    private String code;
    private String message;
    private String detail="";

    private MsgCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    private MsgCode(String code, String message, String detail){
        this(code, message);
        this.detail = detail;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDetail(){
        return this.detail;
    }
}