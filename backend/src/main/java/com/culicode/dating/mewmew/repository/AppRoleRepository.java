package com.culicode.dating.mewmew.repository;

import com.culicode.dating.mewmew.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

}
