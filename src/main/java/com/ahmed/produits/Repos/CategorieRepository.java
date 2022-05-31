package com.ahmed.produits.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ahmed.produits.entities.Categorie;
@RepositoryRestResource(path = "rest")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
