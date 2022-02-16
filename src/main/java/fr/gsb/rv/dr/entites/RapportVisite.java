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
    private Visiteur visiteur ;
    private Praticien praticien ;

    public RapportVisite(int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu, Visiteur visiteur, Praticien praticien) {
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
        this.visiteur = visiteur ;
        this.praticien = praticien ;
    }

    public RapportVisite(int rap_num, LocalDate rap_date_visite, LocalDate rap_date_saisie, String rap_bilan, String rap_motif, int rap_coef_confiance, boolean rap_lu, Visiteur vis_matricule) {
        this.numero = rap_num;
        this.dateVisite = rap_date_visite;
        this.dateRedaction = rap_date_saisie;
        this.bilan = rap_bilan;
        this.motif = rap_motif;
        this.coefConfiance = rap_coef_confiance;
        this.lu = rap_lu;
        this.visiteur = vis_matricule ;
    }

    public RapportVisite() {

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

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
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
                ", visiteur=" + visiteur +
                ", praticien=" + praticien +
                '}';
    }
}
