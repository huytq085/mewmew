package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.Message;
import com.culicode.dating.mewmew.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    String listConversationQuery = "select u.id, u.username, u.password, u.full_name, u.description, u.purpose, u.email, u.phone, u.status, u.address, u.city, u.avatar, u.rate, u.height, u.weight, u.job, u.matrimony, u.date_added, u.gender, u.last_access from user u\n" +
            "join (select m.recipient_id,m.sender_id from message m\n" +
            "where m.sender_id = :userId or m.recipient_id = :userId\n" +
            "group by m.recipient_id,m.sender_id) m\n" +
            "on u.id = m.sender_id or u.id = m.recipient_id\n" +
            "where u.id != :userId\n" +
            "group by u.id";

    @Query(value = "select * from user", nativeQuery = true)
    List<User> findListConversation(@Param("userId") int userId);

    @Query("from Message where (recipientId = :user1 and sender.id = :user2) or (recipientId = :user2 and sender.id = :user1)")
    List<Message> findConversation(@Param("user1") int user1, @Param("user2") int user2);



}
