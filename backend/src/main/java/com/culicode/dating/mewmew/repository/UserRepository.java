package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    String findFriendsQuery = "select * from user u\n" +
            "join (select * from friendlist f where (f.sender_id = :userId or f.recipient_id = :userId) and f.status = 1) ft\n" +
            "on u.id = ft.sender_id or u.id = ft.recipient_id\n" +
            "where u.id != :userId";
    Optional<User> findByEmail(String email);

    @Query(value = findFriendsQuery, nativeQuery = true)
    List<User> findFriends(@Param("userId") int userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Procedure
    int follow(int user1, int user2);

    @Procedure
    int unFollow(int user1, int user2);

    @Procedure
    int isFollowing(int user1, int user2);

    @Procedure
    int addFriend(int user1, int user2);

    @Procedure
    int unFriend(int user1, int user2);

    @Procedure
    int acceptFriend(int user1, int user2);

    @Procedure
    Integer friendStatus(int user1, int user2);







    @Query("from User u where u.username like CONCAT('%',:query,'%') or u.fullName like CONCAT('%',:query,'%')")
    List<User> search(@Param("query") String query);

}
