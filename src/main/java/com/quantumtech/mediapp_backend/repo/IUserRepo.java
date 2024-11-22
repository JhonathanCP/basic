package com.quantumtech.mediapp_backend.repo;

import com.quantumtech.mediapp_backend.model.User;


public interface IUserRepo extends IGenericRepo<User, Integer>{

    //@Query("FROM User u WHERE u.username = :username")
    //DerivedQuery
    User findOneByUsername(String username);
}