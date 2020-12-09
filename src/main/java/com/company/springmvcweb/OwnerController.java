package com.company.springmvcweb;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

@GetMapping("/owner")
    public String owner(Model model) {
      model.addAttribute("name","Gusts");
      return "owner";
    }
}
