package de.molit.controller;

import de.molit.services.PrimerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by patrickwerner on 13.04.17.
 */
@Controller
public class PrimerGuiController {

    private final PrimerDAO primerDAO;

    @Autowired
    public PrimerGuiController(PrimerDAO primerDAO) {
        this.primerDAO = primerDAO;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("primers", primerDAO.all());
        return "index";
    }
}
