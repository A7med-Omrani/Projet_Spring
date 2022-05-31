package com.ahmed.produits.Repos;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.produits.entities.Role;


public interface RoleRepository  extends JpaRepository<Role, Long> {
	
	Role findByRole(String role);
	Optional<Role> findById(Long id);
	



}
