package de.molit.controller;

import de.molit.xto.Primer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrimerController {

    private static final Log logger = LogFactory.getLog(PrimerController.class);

    private List<Primer> primerList = new ArrayList<>();
    private long sequence = 1;

    @RequestMapping(value = "/primers", method = RequestMethod.GET)
    public List<Primer> getPrimerList() {
        return primerList;
    }

    @RequestMapping(value = "/primers", method = RequestMethod.POST)
    public ResponseEntity<Primer> createPrimer(@RequestBody Primer primer) {
        primer.setId(sequence++);
        primerList.add(primer);

        logger.info("Primer \"" + primer.getName() + "\" successfully created with id: " + primer.getId());

        return new ResponseEntity<>(primer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/primers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimerById(@PathVariable("id") long id) {
        return primerList.stream()
                .filter(primer -> primer.getId() == id)
                .map(primer -> new ResponseEntity<>(primer, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
