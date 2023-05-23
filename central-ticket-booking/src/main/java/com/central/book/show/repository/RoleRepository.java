package com.central.book.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.show.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findOneByRoleName(String roleName);

}
