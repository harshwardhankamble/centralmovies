package com.central.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.central.book.common.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findOneByRoleName(String roleName);

}
