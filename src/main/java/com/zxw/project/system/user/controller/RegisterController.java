package com.zxw.project.system.user.controller;

import com.zxw.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zxw.framework.shiro.service.RegisterService;
import com.zxw.framework.web.controller.BaseController;
import com.zxw.framework.web.domain.AjaxResult;
import com.zxw.project.system.config.service.IConfigService;
import com.zxw.project.system.user.domain.User;

/**
 * 注册验证
 *
 * @author Zhouxw
 */
@Controller
public class RegisterController extends BaseController {
    public static final String REG = "sys.account.registerUser";
    @Autowired
    private RegisterService registerService;

    @Autowired
    private IConfigService configService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(User user) {
        if (!(Constants.TRUE.equals(configService.selectConfigByKey(REG)))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
