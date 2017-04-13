package de.molit.controller.rest;

import de.molit.services.PrimerDAO;
import de.molit.xto.ErrorXTO;
import de.molit.xto.Primer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Interface for primers.
 * <p>
 * Notice the class is annotated with @RequestMapping("/primers"). This indicates that all methods annotated
 * with @RequestMapping within this class are also available by using the URL "/primers" if not stated otherwise.
 */
@RestController
@RequestMapping("/primers")
public class PrimerController {

    // Always use a logger instead of System.out.println - you will have better control over your logs by categorizing
    // them in e.g. "info", "error" etc.
    private static final Log logger = LogFactory.getLog(PrimerController.class);

    // The primer DAO that represents the interface to the database in a real world application.
    private final PrimerDAO primerDAO;

    // You don't have to instantiate the PrimerDAO by yourself - Spring does it for you. To use the DAO you can use
    // the annotation @Autowired on the constructor.
    @Autowired
    public PrimerController(PrimerDAO primerDAO) {
        this.primerDAO = primerDAO;
    }

    /**
     * Returns a list of all primers. Notice that there is no value specified. The URL is inferred by the annotation
     * on the class.
     * <p>
     * URL: /primers
     *
     * @return - the {@link List<Primer>}
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Primer> getPrimerList() {
        return primerDAO.all();
    }

    /**
     * Accepts a primer in the body that is then sent to the DAO to be created.
     * <p>
     * URL: /primers
     *
     * @param primer - the primer to be created. Must not be empty and name of primer must not be <code>null</code>.
     * @return - the {@link ResponseEntity} containing an error or the {@link Primer} with <code>id</code> that was
     * assigned by the DAO.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPrimer(@RequestBody Primer primer) {

        if (primer == null || primer.getName() == null || primer.getName().isEmpty()) {
            return new ResponseEntity<>(new ErrorXTO("Name must not be null or empty."), HttpStatus.BAD_REQUEST);
        }

        primer = primerDAO.create(primer);

        logger.info("Primer \"" + primer.getName() + "\" successfully created with id: " + primer.getId());

        return new ResponseEntity<>(primer, HttpStatus.CREATED);
    }

    /**
     * Returns the primer with the given id. If no primer was found, 404 is returned.
     * <p>
     * URL: /primers/{id}
     *
     * @param id - the id
     * @return the {@link ResponseEntity} containing the {@link Primer} with the given id or a 404 error, if the
     * primer was not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrimerById(@PathVariable("id") String id) {
        return primerDAO.findById(id)
                .map(primer -> new ResponseEntity<>(primer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
