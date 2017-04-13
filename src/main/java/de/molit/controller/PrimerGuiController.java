package de.molit.controller;

import de.molit.xto.Primer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by patrickwerner on 13.04.17.
 */
@Controller
public class PrimerGuiController {

    @RequestMapping("/")
    public String index(Model model){
        ArrayList<Primer> primers =new ArrayList<>();
        Primer primer1 = new Primer(1,"superName", "sequenco");
        primers.add(primer1);
        model.addAttribute("primers", primers);
        return "index";
    }
}
