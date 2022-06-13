package com.lunch.appfeeder.repository;


import com.lunch.appfeeder.model.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    @Query("SELECT u FROM AppUser as u WHERE u.verificationCode = ?1")
    AppUser findByVerificationCode(String code);
}
