package com.company.springmvcweb;


import com.company.springmvcweb.data.CarsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

@GetMapping("/owner")
    public String owner(Model model) {

      var repo = new CarsRepository();
      var items = repo.list();

      model.addAttribute("owners",items);
      return "owner";
    }
}
