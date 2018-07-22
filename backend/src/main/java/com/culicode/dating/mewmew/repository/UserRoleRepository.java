package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.AppRole;
import com.culicode.dating.mewmew.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur.appRole.roleName from UserRole ur where ur.user.id = :userId")
    List<String> getRoleNameById(@Param("userId") Long userId);

    @Query("select ur.appRole.roleName from UserRole ur where ur.user.username = :username")
    List<String> getRoleNameByUsername(@Param("username") String username);

//    FIXME: Can not issue data manipulation statements with executeQuery().
    @Query(value = "insert into user_role(user_id, role_id) values (:userId, :roleId)", nativeQuery = true)
    int setRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
