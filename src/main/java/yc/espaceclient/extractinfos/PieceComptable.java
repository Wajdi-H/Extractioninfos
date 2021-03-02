package yc.espaceclient.extractinfos;

import java.time.LocalDate;
import java.util.Date;

public class PieceComptable {
    private long id;
    private LocalDate datepiececomptable;
    private String numfacture ;
    private Float Montanttotal;
    private Float MontanttotalHT;
    private Float MontantTva;
    private String TauxTVA;


    public PieceComptable(long id, LocalDate datepiececomptable, String numfacture, Float montanttotal, Float montanttotalHT, Float montantTva, String tauxTVA) {
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

    public LocalDate getDatepiececomptable() {
        return datepiececomptable;
    }

    public void setDatepiececomptable(LocalDate datepiececomptable) {
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
