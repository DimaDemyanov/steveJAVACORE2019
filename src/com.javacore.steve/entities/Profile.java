package entities;

public class Profile {
    private int id;
    private String name;
    private String dna;
    private String fingerprint;

    public Profile() {
    }

    public Profile(String name, String dna, String fingerprint) {
        this.name = name;
        this.dna = dna;
        this.fingerprint = fingerprint;
    }

    public Profile(int id, String name, String dna, String fingerprint) {
        this.id = id;
        this.name = name;
        this.dna = dna;
        this.fingerprint = fingerprint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
}
