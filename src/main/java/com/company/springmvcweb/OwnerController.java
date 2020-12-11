package com.company.springmvcweb;


import com.company.springmvcweb.data.CarsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping("/owners")
    public String owner(Model model) {

        var repo = new CarsRepository();
        var items = repo.getOwners();

        model.addAttribute("title", "Owners");
        model.addAttribute("owners", items);
        return "owners";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {

        var repo = new CarsRepository();
        var items = repo.getCars();

        model.addAttribute("title", "Cars");
        model.addAttribute("cars", items);

        return "cars";
    }
}
