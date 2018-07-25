package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Procedure(name = "follow")
    int follow(int user1, int user2);

    @Procedure(name = "unFollow")
    int unFollow(int user1, int user2);

    @Procedure(name = "isFollowing")
    int isFollowing(int user1, int user2);

}
