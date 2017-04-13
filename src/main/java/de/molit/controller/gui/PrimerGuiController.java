package de.molit.controller.gui;

import de.molit.services.PrimerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * GUI Controller for ThymeLeaf
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
