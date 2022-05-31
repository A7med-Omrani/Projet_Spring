package com.ahmed.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.ahmed.produits.Repos.ProduitRepository;
import com.ahmed.produits.entities.Categorie;
import com.ahmed.produits.entities.Produit;
import com.ahmed.produits.service.ProduitService;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private ProduitService produitService;
	
	
	@Test
	public void testFindByNomProduitContains()
	{
	Page<Produit> prods = produitService.getAllProduitsParPage(0,2);
	System.out.println(prods.getSize());
	System.out.println(prods.getTotalElements());
	System.out.println(prods.getTotalPages());
	prods.getContent().forEach(p -> {System.out.println(p.toString());
	});
	/*ou bien
	for (Produit p : prods)
	{
	System.out.println(p);
	} */
	}
	
	
	@Test
	public void testFindByNomProduit()
	{
	List<Produit> prods = produitRepository.findByNomProduit("PS 4");

	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}
	
	@Test
	public void testFindByNomProduitContains1 ()
	{
	List<Produit> prods=produitRepository.findByNomProduitContains("P");

	for (Produit p : prods)
	{
	System.out.println(p);
	} }
	
	
	@Test
	public void testfindByNomPrix()
	{
	List<Produit> prods = produitRepository.findByNomPrix("PS 4", 1000.0);
	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}
	
	@Test
	public void testfindByCategorie()
	{
	Categorie cat = new Categorie();
	cat.setIdCat(1L);
	List<Produit> prods = produitRepository.findByCategorie(cat);
	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}
	
	@Test
	public void findByCategorieIdCat()
	{
	List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}
	
	@Test
	public void testfindByOrderByNomProduitAsc()
	{
	List<Produit> prods =

	produitRepository.findByOrderByNomProduitAsc();
	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}
	
	
	@Test
	public void testTrierProduitsNomsPrix()
	{
	List<Produit> prods = produitRepository.trierProduitsNomsPrix();
	for (Produit p : prods)
	{
	System.out.println(p);
	}

	}

}
