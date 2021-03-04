package yc.espaceclient.extractinfos;

import java.time.LocalDate;

public class lignefacture {

    private long id;
    private Float Qte;
    private String Designation ;
    private Float prixtotal;
    private Float proixHT;
    private Float tva;

    public lignefacture(long id, Float qte, String designation, Float prixtotal, Float proixHT, Float tva) {
        this.id = id;
        Qte = qte;
        Designation = designation;
        this.prixtotal = prixtotal;
        this.proixHT = proixHT;
        this.tva = tva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getQte() {
        return Qte;
    }

    public void setQte(Float qte) {
        Qte = qte;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public Float getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(Float prixtotal) {
        this.prixtotal = prixtotal;
    }

    public Float getProixHT() {
        return proixHT;
    }

    public void setProixHT(Float proixHT) {
        this.proixHT = proixHT;
    }

    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public lignefacture() {
    }

    @Override
    public String toString() {
        return "lignefacture{" +
                "id=" + id +
                ", Qte=" + Qte +
                ", Designation='" + Designation + '\'' +
                ", prixtotal=" + prixtotal +
                ", proixHT=" + proixHT +
                ", tva=" + tva +
                '}';
    }
}
