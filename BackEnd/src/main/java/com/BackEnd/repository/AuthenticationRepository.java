package com.BackEnd.repository;

import com.BackEnd.models.Authentication;
import com.BackEnd.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<Authentication,String> {
  Authentication findByEmailAndProvider(
            String email,
            Provider provider
    );

    Authentication findByProviderUserIdAndProvider(
        String providerUserId,
        Provider provider
    );

    Authentication findByEmail(String email);

    Authentication save(Authentication authentication);


}
