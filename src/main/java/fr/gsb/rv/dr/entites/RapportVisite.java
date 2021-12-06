package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

public class RapportVisite {

    private int numero ;
    private LocalDate dateVisite ;
    private LocalDate dateRedaction ;
    private String bilan ;
    private String motif ;
    private int coefConfiance ;
    private boolean lu ;

    public RapportVisite(int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDateVisite() {
        return this.dateVisite;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public LocalDate getDateRedaction() {
        return this.dateRedaction;
    }

    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public String getBilan() {
        return this.bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return this.motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getCoefConfiance() {
        return this.coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public boolean isLu() {
        return this.lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    @Override
    public String toString() {
        return "RapportVisite{" +
                "numero=" + numero +
                ", dateVisite=" + dateVisite +
                ", dateRedaction=" + dateRedaction +
                ", bilan='" + bilan + '\'' +
                ", motif='" + motif + '\'' +
                ", coefConfiance=" + coefConfiance +
                ", lu=" + lu +
                '}';
    }
}
