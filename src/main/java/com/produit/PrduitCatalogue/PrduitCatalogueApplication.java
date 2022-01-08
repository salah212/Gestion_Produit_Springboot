package com.produit.PrduitCatalogue;

import com.produit.PrduitCatalogue.dao.ProduitRepository;
import com.produit.PrduitCatalogue.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrduitCatalogueApplication implements CommandLineRunner { //
	@Autowired
	private ProduitRepository produitRepository;

	public static void main(String[] args) {
		SpringApplication.run(PrduitCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//produitRepository.save(new Produit(null,"PC HP",6000,12));
		//produitRepository.save(new Produit(null,"PC DELL",8000,22));
		//produitRepository.save(new Produit(null,"PC sndou9",100,32));
		//produitRepository.save(new Produit(null,"Scanner",6000,12));
		//produitRepository.findAll().forEach(p->{
		//	System.out.println(p.getDesignation());
		}
	}

