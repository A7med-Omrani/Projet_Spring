package com.ahmed.produits.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.produits.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);

}
