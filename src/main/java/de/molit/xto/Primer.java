package de.molit.xto;

public class Primer {

    private String id;
    private String name;
    private String sequence;

    public Primer() {
    }

    public Primer(String id, String name, String sequence) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
