package com.kennedy.tfi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kennedy.tfi.models.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String userName);
}
