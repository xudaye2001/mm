package com.bookindle.boosystem.repository.member;


import com.bookindle.boosystem.entity.user.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
	UserRole findByRolename(String name);
}
