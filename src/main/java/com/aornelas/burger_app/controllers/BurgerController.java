package com.aornelas.burger_app.controllers;

import com.aornelas.burger_app.domain.Burger;
import com.aornelas.burger_app.repositories.JdbcBurgerRepositoryDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@SuppressWarnings("unused")
public class BurgerController {

    private JdbcBurgerRepositoryDaoImpl burgerRepo;

    @Autowired
    public BurgerController(JdbcBurgerRepositoryDaoImpl burgerRepo) {
        this.burgerRepo = burgerRepo;
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @GetMapping
    public String getBurgers(Model model) {
        model.addAttribute("burgers", burgerRepo.findAll());
        return "index";
    }

    @PostMapping
    public String createNewBurger(@ModelAttribute Burger burger)  {
        burgerRepo.insertBurger(burger);
        return "redirect:/";
    }

    @RequestMapping(value = "{id}")
    public String deleteBurger(@PathVariable("id") Long id) {
        burgerRepo.deleteBurger(id);
        return "redirect:/";
    }

    @GetMapping("editBurger/{id}")
    public String getBurgerData(@PathVariable("id") Long id, Model model) {
        model.addAttribute("burger", burgerRepo.findById(id));
        return "editBurger";
    }

    @PostMapping("editBurger/{id}")
    public String editBurger(@PathVariable("id") Long id, @ModelAttribute Burger burger) {
        burgerRepo.updateBurger(burger);
        return "redirect:/";
    }
}
