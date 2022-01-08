package com.produit.PrduitCatalogue.web;

import com.produit.PrduitCatalogue.dao.ProduitRepository;
import com.produit.PrduitCatalogue.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    //@RequestMapping(value = "/index",method = RequestMethod.GET)

    @GetMapping("/user/index")
    public  String  index(Model model ,@RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "motcle" ,defaultValue = "") String mc){
        Page<Produit> pageproduit = produitRepository.findByDesignationContains(mc,PageRequest.of(page,5));
        model.addAttribute("listProduits",pageproduit.getContent());//liste des produits
        model.addAttribute("pages",new int[pageproduit.getTotalPages()]);// liste des pages
        model.addAttribute("currentpage",page);
        model.addAttribute("motcle",mc);
        return "produit";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id,int page, String motcle){
        produitRepository.deleteById(id);
        return "redirect:/user/index?page"+page+"&motcle="+motcle;
    }

    @GetMapping("/admin/formproduit")
    public String form(Model model){
        model.addAttribute("produit",new Produit());
        return "formproduit";

    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Produit produit , BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formproduit";
        produitRepository.save(produit);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/edit")
    public String edit(Model model , Long id){
        Produit produit = produitRepository.findById(id).get();
        model.addAttribute("produit",produit);
        return "edit";
    }
    @GetMapping("/")
    public String def(){
        return "redirect:/user/index";
    }

    @GetMapping("/403")
    public String notAuth(){
        return "403";
    }




}
