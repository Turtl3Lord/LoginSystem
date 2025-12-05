package com.BackEnd.repository;

import com.BackEnd.models.Authentication;
import com.BackEnd.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthenticationRepository extends JpaRepository<Authentication,String> {
  Authentication findByEmailAndProvider(
            String email,
            Provider provider
    );

    Authentication findByProviderUserId(
        String providerUserId
    );

    Authentication findByEmail(String email);

    Authentication save(Authentication authentication);


}
