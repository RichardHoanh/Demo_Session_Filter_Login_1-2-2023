package com.example.blog.service;

import com.example.blog.model.Account;
import com.example.blog.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    IAccountRepository iAccountRepository;
    public Account checkLogin (String userName,String passWord){
        return iAccountRepository.checkLogin(userName,passWord);
    }
    public void save (Account account){
        iAccountRepository.save(account);
    }

}
