package com.ahmed.produits.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.service.CategorieService;

@Controller
public class CategorieController {
	
	@Autowired
	CategorieService catService;
	
	
	@RequestMapping("/showCreate1")
	public String showCreate(ModelMap modelMap) {
		Categorie cat=new Categorie();
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("categorie", cat);
		return "formCategorie";
	}
	
	
	@RequestMapping("/ListeCategories")
	public String listeCategories(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Categorie> cat = catService.getAllCategoriesParPage(page, size);
		
		
		modelMap.addAttribute("categories", cat);
		modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeCategories";
	}
	
	
	@RequestMapping("/saveCategorie")
	public String saveCategorie(@Valid Categorie categorie,
			 BindingResult bindingResult) 
	{
		if (bindingResult.hasErrors()) return "formCategorie";
	 catService.saveCategorie(categorie);
	 return "redirect:/ListeCategories";
	}
	
	@RequestMapping("/supprimerCategorie")
	public String supprimerCategorie(@RequestParam("id") Long id, ModelMap modelMap,

		@RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "2") int size) {

		catService.deleteCategorieById(id);
		Page<Categorie> cat = catService.getAllCategoriesParPage(page,
				size);


		modelMap.addAttribute("categories", cat);
		modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeCategories";
	}
	
	
	@RequestMapping("/modifierCategorie")
	public String editerCategorie(@RequestParam("id") Long id, ModelMap modelMap) {
		
		Categorie s = catService.getCategorie(id);
		modelMap.addAttribute("categorie", s);
		modelMap.addAttribute("mode", "edit");


		return "formCategorie";
	}

	@RequestMapping("/updateCategorie")
	public String updateCategorie(@ModelAttribute("categorie") Categorie categorie, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		catService.updateCategorie(categorie);
		List<Categorie> cat = catService.getAllCategories();
		modelMap.addAttribute("categories", cat);
		return "listeCategories";
	}

}
