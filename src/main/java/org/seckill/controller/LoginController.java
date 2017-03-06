package org.seckill.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.seckill.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by X-man on 2017/3/6.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request) throws Exception{
        // shiro在认证过程中出现错误后将异常类路径通过request返回
        String exceptionClassName = (String) request
                .getAttribute("shiroLoginFailure");
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else{
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }
        //认证成功后，shrio会自动跳转到上次请求路径
        return "login";
    }
}
