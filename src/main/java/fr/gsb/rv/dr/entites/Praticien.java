package fr.gsb.rv.dr.entites;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.LocalDate;

public class Praticien {

    private int numero ;
    private String nom ;
    private String prenom ;
    private String adresse ;
    private String codePostal ;
    private String ville ;
    private double coefNotoriete ;
    private LocalDate dateDerniereVisite ;
    private int dernierCoefConfiance ;

    public Praticien(int pra_num, String pra_nom, String pra_ville, double pra_coefnotoriete, LocalDate rap_date_visite,
                     int rap_coef_confiance) {
        this.numero = pra_num;
        this.nom = pra_nom;
        this.ville = pra_ville;
        this.coefNotoriete = pra_coefnotoriete;
        this.dateDerniereVisite = rap_date_visite;
        this.dernierCoefConfiance = rap_coef_confiance;
    }

    public Praticien(int numero, String nom, String prenom, String adresse, String codePostal, String ville, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getCoefNotoriete() {
        return this.coefNotoriete;
    }

    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return this.dateDerniereVisite;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return this.dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    @Override
    public String toString() {
        return "Praticien{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", coefNotoriete=" + coefNotoriete +
                ", dateDerniereVisite=" + dateDerniereVisite +
                ", dernierCoefConfiance=" + dernierCoefConfiance +
                '}';
    }
}
