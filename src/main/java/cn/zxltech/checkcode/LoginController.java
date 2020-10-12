package cn.zxltech.checkcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping({"", "login"}) //空或是'login'都能进入
    public String login() {
        return "login";
    }
}
