package de.molit.controller;

import de.molit.services.PrimerDAO;
import de.molit.xto.Primer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrimerController {

    private static final Log logger = LogFactory.getLog(PrimerController.class);

    @Autowired
    private PrimerDAO primerDAO;

    @RequestMapping(value = "/primers", method = RequestMethod.GET)
    public List<Primer> getPrimerList() {
        return primerDAO.all();
    }

    @RequestMapping(value = "/primers", method = RequestMethod.POST)
    public ResponseEntity<Primer> createPrimer(@RequestBody Primer primer) {
        primer = primerDAO.create(primer);

        logger.info("Primer \"" + primer.getName() + "\" successfully created with id: " + primer.getId());

        return new ResponseEntity<>(primer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/primers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimerById(@PathVariable("id") long id) {
        return primerDAO.findById(id)
                .map(primer -> new ResponseEntity<>(primer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
