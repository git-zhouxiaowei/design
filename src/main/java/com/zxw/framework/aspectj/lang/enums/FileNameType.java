package com.zxw.framework.aspectj.lang.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 采集附件名称英文和拼音对应（呵呵，奇怪）
 *
 * @author Zhouxw
 * @date 2020/12/28 9:00
 */
public enum FileNameType {
    /**
     * 图片类型
     */
    ID_CARD_BACK("idCardBackImg", "shenfenzheng-renxiang.png"),
    ID_CARD_FRONT("idCardFrontImg", "shenfenzheng-guohui.png"),
    ID_CARD_HOLD("idCardHoldImg", "shenfenzheng-shouchi.png"),
    LICENCE("licenseImg", "yingyezhizhao.png"),
    SHOP_SIGN_ONE("shopSignImgOne", "zhaopai1.png"),
    SHOP_SIGN_TWO("shopSignImgTwo", "zhaopai2.png"),
    HIRE_CONTRACT_ONE("hireContractImgOne", "zulinhetong1.png"),
    HIRE_CONTRACT_TWO("hireContractImgTwo", "zulinhetong2.png"),
    HIRE_CONTRACT_THREE("hireContractImgThree", "zulinhetong3.png"),
    HIRE_CONTRACT_FOUR("hireContractImgFour", "zulinhetong4.png"),
    SETTLE_ACCOUNT("settleAccountImg", "jiesuanzhanghu.png");

    private String code;
    private String name;


    FileNameType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<String, String> MAP;

    static {
        MAP = new HashMap<>();
        for (FileNameType e : FileNameType.values()) {
            MAP.put(e.getCode(), e.getName());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取业务板块
     */
    public static String getFileNameSector(String code) {
        return MAP.get(code);
    }
}
