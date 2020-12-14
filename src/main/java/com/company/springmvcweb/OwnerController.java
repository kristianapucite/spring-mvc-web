package com.company.springmvcweb;


import com.company.springmvcweb.data.CarsRepository;
import com.company.springmvcweb.dto.CarSearchDto;
import com.company.springmvcweb.dto.OwnerSearchDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

//    @PostMapping("/owners")
//    public String searchOwners(@ModelAttribute("searchDto") OwnerSearchDto searchDto, Model model) {
//
//        var repo = new CarsRepository();
//        var items = repo.getOwners();
//
//        model.addAttribute("title", "Owners");
//        model.addAttribute("owners", items);
//
//        return "owners";
//    }

    @GetMapping("/cars")
    public String getCars(Model model) {

        var repo = new CarsRepository();
        var items = repo.getCars();

        model.addAttribute("title", "Cars");
        model.addAttribute("cars", items);

        return "cars";
    }

    @PostMapping("/cars")
    public String searchCars(@ModelAttribute("searchDto") CarSearchDto dto, Model model) {
        var repo = new CarsRepository();
        var items = repo.getCars(dto);

        model.addAttribute("title", "Cars");
        model.addAttribute("cars", items);

        return "cars";
    }
}
