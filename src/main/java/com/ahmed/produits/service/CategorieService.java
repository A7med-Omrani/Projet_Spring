package com.ahmed.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ahmed.produits.entities.Categorie;

public interface CategorieService {
	List<Categorie> getAllCategories();
	
	Categorie saveCategorie(Categorie e);
	Categorie updateCategorie(Categorie e);
	void deleteCategorie(Categorie e);
	void deleteCategorieById(Long id);
	Categorie getCategorie(Long id);
	Page<Categorie> getAllCategoriesParPage(int page, int size);

}
