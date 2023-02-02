package com.example.blog.controller;

import com.example.blog.model.Account;
import com.example.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/login")
    public String showFormLogin() {
        return "formLogin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Account account = accountService.checkLogin(username, password);
        if (account == null) {
            return "redirect:/login";
        } else {
            httpSession.setAttribute("account", account);
            return "redirect:/blog";
        }
    }

    @GetMapping("/register")
    public String showFormRegister() {
        return "formRegister";
    }

    @PostMapping("/register")
    public String saveNewAccount(Account account, @RequestParam MultipartFile upAvatar) {

        String nameFile = upAvatar.getOriginalFilename();
        try {
            FileCopyUtils.copy(upAvatar.getBytes(), new File("I:/CodeGym/Module4/Bai_6/Demo_Blog/blog/src/main/webapp/WEB-INF/img/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        account.setAvatar("/img/" + nameFile);
        accountService.save(account);
        return "redirect:/blog";
    }
}
