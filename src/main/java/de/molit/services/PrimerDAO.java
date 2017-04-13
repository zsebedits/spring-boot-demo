package de.molit.services;

import de.molit.xto.Primer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PrimerDAO {

    private List<Primer> primerList;

    public List<Primer> all() {
        return primerList;
    }

    public Primer create(Primer primer) {
        primer.setId(UUID.randomUUID().toString());
        primerList.add(primer);

        return primer;
    }

    public Optional<Primer> findById(String id) {
        return primerList.stream()
                .filter(primer -> primer.getId().equals(id))
                .findFirst();
    }

    @PostConstruct
    private void init() {
        primerList = new ArrayList<>();

        primerList.add(new Primer(UUID.randomUUID().toString(), "Awesome Primer is awesome", "TAAC"));
        primerList.add(new Primer(UUID.randomUUID().toString(), "Super Primer", "CATCAT"));
        primerList.add(new Primer(UUID.randomUUID().toString(), "Mega Primer", "TAAC"));
    }

}
