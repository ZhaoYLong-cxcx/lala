package frank.controller;

import frank.model.User;
import frank.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request){
        // TODO 校验用户名密码
        User user = loginService.queryByLogin(username, password);
        if(user == null){
            return "login";
        }
        // 校验通过的逻辑
        HttpSession session = request.getSession(true);
        System.out.println("queryByLogin: " + user);
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
