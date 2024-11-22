package com.quantumtech.mediapp_backend.repo;

import java.util.List;
import java.util.Optional;

import com.quantumtech.mediapp_backend.model.Token;

public interface ITokenRepo extends IGenericRepo<Token, Integer>{

    Optional<Token> findByToken(String token);

    void deleteByToken(String token);

    List<Token> findByUsernameAndIsValidTrue(String username);  // Obtener tokens válidos de un usuario

}
