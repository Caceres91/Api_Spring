package com.api.desarrollo.repository;

import com.api.desarrollo.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
