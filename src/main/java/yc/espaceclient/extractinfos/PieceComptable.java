package yc.espaceclient.extractinfos;

import java.util.Date;

public class PieceComptable {
    private long id;
    private Date datepiececomptable;
    private String numfacture ;
    private Float Montanttotal;
    private Float MontanttotalHT;
    private Float MontantTva;
    private String TauxTVA;


    public PieceComptable(long id, Date datepiececomptable, String numfacture, Float montanttotal, Float montanttotalHT, Float montantTva, String tauxTVA) {
        this.id = id;
        this.datepiececomptable = datepiececomptable;
        this.numfacture = numfacture;
        Montanttotal = montanttotal;
        MontanttotalHT = montanttotalHT;
        MontantTva = montantTva;
        TauxTVA = tauxTVA;
    }

    public PieceComptable() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatepiececomptable() {
        return datepiececomptable;
    }

    public void setDatepiececomptable(Date datepiececomptable) {
        this.datepiececomptable = datepiececomptable;
    }

    public Float getMontanttotal() {
        return Montanttotal;
    }

    public void setMontanttotal(Float montanttotal) {
        Montanttotal = montanttotal;
    }

    public Float getMontanttotalHT() {
        return MontanttotalHT;
    }

    public void setMontanttotalHT(Float montanttotalHT) {
        MontanttotalHT = montanttotalHT;
    }

    public Float getMontantTva() {
        return MontantTva;
    }

    public void setMontantTva(Float montantTva) {
        MontantTva = montantTva;
    }

    public String getTauxTVA() {
        return TauxTVA;
    }

    public void setTauxTVA(String tauxTVA) {
        TauxTVA = tauxTVA;
    }

    public String getNumfacture() {
        return numfacture;
    }

    public void setNumfacture(String numfacture) {
        this.numfacture = numfacture;
    }
}
