package com.restaurant.controller;

import com.restaurant.entity.User;
import com.restaurant.service.UserService;
import com.restaurant.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value="/login", method = RequestMethod.GET)
    private String login(){
        return "login";
    }

    @RequestMapping(value="/index", method = RequestMethod.GET)
    private String index(){
        return "index";
    }
    @RequestMapping(value="/handleLogin", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> handlerLogin(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        if(!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Please enter the correct Validation Code");
            return modelMap;
        }
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        if(userId == null || userId.trim().equals("") || password == null || password.trim().equals("")){
            modelMap.put("success", false);
            modelMap.put("errMsg", "userId and password could not be empty");
            return modelMap;
        }
        try{
            User user = userService.verifyUser(userId,password);
            if(user != null){
                modelMap.put("success",true);
                request.getSession().setAttribute("user", user);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "wrong password or user account does not exit");
            }
        } catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value="/getuser", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getUser(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            modelMap.put("success",true);
            modelMap.put("currentUser",user);
            return modelMap;
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","could not load the user information");
        }
        return modelMap;
    }

    @RequestMapping(value="/updateInfo", method = RequestMethod.GET)
    private String updateInfo(){
        return "update";
    }

    @RequestMapping(value="/update", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object>update(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        User user = new User();
        String userId = request.getParameter("userId");
        if(userId == null){
            modelMap.put("success",false);
            modelMap.put("errMsg", "Could not load the user's account name");
            return modelMap;
        }
        String password = request.getParameter("password");
        User temp = userService.verifyUser(userId,password);
        if(temp == null){
            modelMap.put("success",false);
            modelMap.put("errMsg", "Wrong password");
            return modelMap;
        }
        user.setUserId(userId);
        String newpassword = request.getParameter("newpassword");
        String newpassword1 = request.getParameter("newpassword1");
        if(newpassword != null && !newpassword.trim().equals("") && newpassword.trim().equals(newpassword1.trim())){
            user.setPassword(newpassword);
        }
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try{
            userService.updateUser(user);
        } catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        request.getSession().setAttribute("user", user);
        return modelMap;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    private String register(){
        return "register";
    }
    @RequestMapping(value="/handleRegister", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> handleReigister(HttpServletRequest request){
        Map<String,Object>modelMap = new HashMap<>();
        String userId = request.getParameter("userId");
        if(userId == null || userId.trim().equals("")){
            modelMap.put("success",false);
            modelMap.put("errMsg","account name could not be empty");
            return modelMap;
        }
        User user = userService.selectUserById(userId);
        if(user != null){
            modelMap.put("success",false);
            modelMap.put("errMsg","account name has already been registered, please change to another one");
            return modelMap;
        }
        String password = request.getParameter("password");
        if(password == null || password.trim().equals("")){
            modelMap.put("success",false);
            modelMap.put("errMsg","password could not be empty");
            return modelMap;
        }
        String password1 = request.getParameter("password1");
        if(password1 == null || password1.trim().equals("")){
            modelMap.put("success",false);
            modelMap.put("errMsg","please confirm the password");
            return modelMap;
        }
        if(!password.equals(password1)){
            modelMap.put("success",false);
            modelMap.put("errMsg","passwords are not equal");
            return modelMap;
        }
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try{
            int effectNum = userService.saveUser(user);
            if(effectNum <= 0){
                throw new RuntimeException();
            }
            modelMap.put("success",true);
        } catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        return modelMap;
    }


}
