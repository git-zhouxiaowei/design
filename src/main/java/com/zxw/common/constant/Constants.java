package com.zxw.common.constant;

/**
 * 通用常量信息
 *
 * @author Zhouxw
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 系统用户授权缓存
     */
    public static final String SYS_AUTH_CACHE = "sys-authCache";

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 商铺文件目录
     */
    public static final String SHOP_FILE = "shopFile";

    /**
     * 临时目录
     */
    public static final String TEMP = "temp";

    /**
     * 已审核
     */
    public static final String REVIEW = "1";

    /**
     * 店铺编号前缀
     */
    public static final String SHOP_PRE = "SHOP-FILE-";

    /**
     * 验证码类型
     */
    public static final String CAPTCHA_MATH = "math", CAPTCHA_CHAR = "char";

    /**
     * HTTP类型
     */
    public static final String HTTP_POST = "post", HTTP_GET = "get";

    /**
     * 最顶端企业ID
     */
    public static final long PEAK = 100L;

    /**
     * 符号
     */
    public static final String POINT = ".", DOU = ",", P_P = "..", SPACE = " ", XIE = "/";

    /**
     * 浏览器类型
     */
    public static final String BROWSER_IE = "MSIE", BROWSER_FIREFOX = "Firefox", BROWSER_CHROME = "Chrome", BROWSER_WIN = "Windows NT", BROWSER_MACINTOSH = "Macintosh";

    /**
     * 商铺类型
     */
    public static final String PERSON = "101", COMPANY = "102";

    /**
     * 时间量
     */
    public static final int SIXTY = 60, ONE_TWO = 120, THOUSAND = 1000;

    /**
     * 对否
     */
    public static final String TRUE = "true", FALSE = "false";

    /**
     * 后缀
     */
    public static final String SUFFIX_JSON = ".json", SUFFIX_XML = ".xml";

    /**
     * 类型
     */
    public static final String TYPE_JSON = "application/json", TYPE_XML = "XMLHttpRequest";

    /**
     * 模板类型
     */
    public static final String DOMAIN_VM = "domain.java.vm",
            SUB_DOMAIN_VM = "sub-domain.java.vm",
            MAPPER_VM = "mapper.java.vm",
            SERVICE_VM = "service.java.vm",
            SERVICE_IMPL_VM = "serviceImpl.java.vm",
            CONTROLLER_VM = "controller.java.vm",
            MAPPER_XML_VM = "mapper.xml.vm",
            LIST_HTML_VM = "list.html.vm",
            LIST_TREE_HTML_VM = "list-tree.html.vm",
            TREE_HTML_VM = "tree.html.vm",
            ADD_HTML_VM = "add.html.vm",
            EDIT_HTML_VM = "edit.html.vm",
            SQL_VM = "sql.vm";
    /**
     * 前台菜单类型
     */
    public static final String YES = "Y", NO = "N";
}
