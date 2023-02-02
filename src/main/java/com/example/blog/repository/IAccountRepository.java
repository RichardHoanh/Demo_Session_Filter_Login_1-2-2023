package com.example.blog.repository;

import com.example.blog.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends PagingAndSortingRepository<Account,Integer> {
    @Query(nativeQuery = true, value =
            "select * from account where username=:username and password=:password")
    Account checkLogin(@Param("username") String username, @Param("password") String password);

}
