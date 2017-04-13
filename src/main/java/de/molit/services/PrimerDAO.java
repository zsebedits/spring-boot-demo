package de.molit.services;

import de.molit.xto.Primer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PrimerDAO {

    private List<Primer> primerList;
    private long sequence;

    public List<Primer> all() {
        return primerList;
    }

    public Primer create(Primer primer) {
        primer.setId(sequence++);
        primerList.add(primer);

        return primer;
    }

    public Optional<Primer> findById(long id) {
        return primerList.stream()
                .filter(primer -> primer.getId() == id)
                .findFirst();
    }

    @PostConstruct
    private void init() {
        sequence = 1;

        primerList = new ArrayList<>();

        primerList.add(new Primer(sequence++, "Awesome Primer is awesome", "TAAC"));
        primerList.add(new Primer(sequence++, "Super Primer", "CATCAT"));
        primerList.add(new Primer(sequence++, "Mega Primer", "TAAC"));
    }

}
