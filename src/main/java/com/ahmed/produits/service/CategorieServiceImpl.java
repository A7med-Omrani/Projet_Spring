package com.ahmed.produits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ahmed.produits.Repos.CategorieRepository;
import com.ahmed.produits.entities.Categorie;


@Service
public class CategorieServiceImpl implements CategorieService {
	@Autowired
	CategorieRepository categorieRepository;

	
	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}



	@Override
	public Categorie saveCategorie(Categorie e) {
		return categorieRepository.save(e);
	}



	@Override
	public Categorie updateCategorie(Categorie e) {
		return categorieRepository.save(e);
	}



	@Override
	public void deleteCategorie(Categorie e) {

		categorieRepository.delete(e);		
	}



	@Override
	public void deleteCategorieById(Long id) {
		categorieRepository.deleteById(id);		
	}



	@Override
	public Categorie getCategorie(Long id) {
		return categorieRepository.findById(id).get();
	}



	@Override
	public Page<Categorie> getAllCategoriesParPage(int page, int size) {
		return categorieRepository.findAll(PageRequest.of(page, size));
	}

}
