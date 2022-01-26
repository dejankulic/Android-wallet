package rs.raf.prviprojekat.models;

import java.io.File;

public class Rashod {
    private int id;
    private String naslov;
    private String opis;
    private int kolicina;
    private File file;

    public Rashod(int id, String naslov,String opis, int kolicina){
        this.id = id;
        this.naslov = naslov;
        this.opis = opis;
        this.kolicina = kolicina;
    }
    public Rashod(int id, String naslov, File file, int kolicina) {
        this.id = id;
        this.naslov = naslov;
        this.file = file;
        this.kolicina = kolicina;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

}
