package com.lunch.appfeeder.repository;


import com.lunch.appfeeder.model.login.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole,Long> {
}
