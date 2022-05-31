package com.ahmed.produits.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.entities.Produit;
import com.ahmed.produits.service.ProduitService;
import com.ahmed.produits.service.CategorieService;

@Controller
public class ProduitController {
	@Autowired
	ProduitService produitService;
	
	@Autowired
	CategorieService categorieService;

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
	modelMap.addAttribute("produit", new Produit());
	modelMap.addAttribute("mode", "new");
	List<Categorie> cats = categorieService.getAllCategories();

	modelMap.addAttribute("categories", cats);
	return "formProduit";
	}

	@RequestMapping("/saveProduit")
	public String saveProduit(@Valid Produit produit,

	BindingResult bindingResult)

	{
	if (bindingResult.hasErrors()) return "formProduit";
	produitService.saveProduit(produit);
	return "redirect:/ListeProduits";
	}
	

	@RequestMapping("/ListeProduits")
	public String listeProduits(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
	{
		Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
		List<Categorie> cats=categorieService.getAllCategories();
		modelMap.addAttribute("categories",cats);
		modelMap.addAttribute("produits", prods);

		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);

		modelMap.addAttribute("currentPage", page);
		
		return "listeProduits";
	}

	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) 
	{
		produitService.deleteProduitById(id);
		Page<Produit> prods = produitService.getAllProduitsParPage(page,size);
		modelMap.addAttribute("produits", prods);
		modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeProduits";
	}
	

	@RequestMapping("/modifierProduit")
	public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap) {
		Produit p = produitService.getProduit(id);
		modelMap.addAttribute("produit", p);
		modelMap.addAttribute("mode", "edit");
		List<Categorie> cats = categorieService.getAllCategories();

		modelMap.addAttribute("categories", cats);
		return "formProduit";
	}

	@RequestMapping("/updateProduit")
	public String updateProduit(@ModelAttribute("produit") Produit produit, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException

	{
//conversion de la date

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		produit.setDateCreation(dateCreation);
		produitService.updateProduit(produit);
		List<Produit> prods = produitService.getAllProduits();
		modelMap.addAttribute("produits", prods);
		return "listeProduits";

	}
	
	@RequestMapping("/search")
	public String doSearchEnseignant(@ModelAttribute("produitSearchFormData")Produit formData,Model model, @RequestParam("nomp") String nomp) {
		List<Produit> prods=produitService.findByNomProduitContains(nomp);
		model.addAttribute("produits",prods);
		return "listeProduits";
	}
	
	@RequestMapping("/search2")
	public String doSearchEnseignant1(@ModelAttribute("produitSearchFormData")Produit formData,Model modelMap, @RequestParam("s") Long s) {
		List<Categorie> cats = categorieService.getAllCategories();
		modelMap.addAttribute("categories", cats);
		
		List<Produit> prods=produitService.findByCategorieIdCat(s);
		modelMap.addAttribute("produits", prods);

		return "listeProduits";
	}

}
