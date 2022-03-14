package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.collections.FXCollections;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {

    public static Visiteur seConnecter(String matricule, String mdp) throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion();

        String requete = "SELECT t.vis_matricule, t.tra_role, t.jjmmaa, V.vis_prenom, V.vis_nom\n" +
                "FROM Travailler t \n" +
                "INNER JOIN (SELECT tra_role, vis_matricule, MAX(jjmmaa) AS jjmmaa\n" +
                "            FROM Travailler\n" +
                "            GROUP BY vis_matricule) AS s\n" +
                "INNER JOIN Visiteur AS V\n" +
                "ON s.vis_matricule = t.vis_matricule\n" +
                "AND t.jjmmaa = s.jjmmaa\n" +
                "AND V.vis_matricule = t.vis_matricule\n" +
                "WHERE t.tra_role = 'Délégué'\n" +
                "AND V.vis_matricule = '" + matricule + "'\n" +
                "AND V.vis_mdp = '" + mdp + "'";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            ResultSet resultat = requetePreparee.executeQuery();
            if (resultat.next()) {
                Visiteur visiteur = new Visiteur();
                visiteur.setMatricule(matricule);
                visiteur.setNom(resultat.getString("vis_nom"));
                visiteur.setPrenom(resultat.getString("vis_prenom"));

                requetePreparee.close();
                return visiteur;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Praticien> getPraticiensHesitants() throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion();

        List<Praticien> praticiens = FXCollections.observableArrayList();

        String requete = """
                    SELECT p.pra_num,
                    p.pra_nom,
                    p.pra_ville,
                    p.pra_coefnotoriete,
                    rv.rap_date_visite,
                    rv.rap_coef_confiance
                FROM Praticien p
                    INNER JOIN (
                        SELECT MAX(rap_date_visite) AS rap_date_visite,
                            MAX(rap_coef_confiance) AS rap_coef_confiance
                        FROM RapportVisite
                        GROUP BY rap_num
                    ) AS r
                    INNER JOIN RapportVisite as rv ON p.pra_num = rv.pra_num
                WHERE rv.rap_date_visite = r.rap_date_visite
                    AND rv.rap_coef_confiance = r.rap_coef_confiance
                    AND rv.rap_coef_confiance < 5;
                                        """;

        try {
            Statement stmt = connexion.createStatement();
            ResultSet resultat = stmt.executeQuery(requete);
            while (resultat.next()) {
                Praticien praticien = new Praticien(
                        resultat.getInt("p.pra_num"),
                        resultat.getString("p.pra_nom"),
                        resultat.getString("p.pra_ville"),
                        resultat.getDouble("p.pra_coefnotoriete"),
                        resultat.getDate("rv.rap_date_visite").toLocalDate(),
                        resultat.getInt("rv.rap_coef_confiance"));
                praticiens.add(praticien);
            }
            return praticiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return praticiens;
    }

    public static List<Praticien> getPraticiens() throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        List<Praticien> praticiens = new ArrayList<>();
        String requete = """
                SELECT p.pra_num,
                    p.pra_nom,
                    p.pra_prenom,
                    p.pra_ville,
                    p.pra_coefnotoriete,
                    rv.rap_date_visite,
                    rv.rap_coefficient,
                    p.pra_adresse,
                    p.pra_cp
                FROM Praticien p
                    INNER JOIN (
                        SELECT MAX(rap_date_visite) AS rap_date_visite,
                            MAX(rap_coefficient) AS rap_coefficient
                        FROM RapportVisite
                        GROUP BY rap_num
                    ) AS r
                    INNER JOIN RapportVisite as rv ON p.pra_num = rv.pra_num
                WHERE rv.rap_date_visite = r.rap_date_visite
                    AND rv.rap_coefficient = r.rap_coefficient;
                    """;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet resultat = stmt.executeQuery(requete);
            while (resultat.next()) {
                Praticien praticien = new Praticien(
                        resultat.getInt("pra_num"),
                        resultat.getString("pra_nom"),
                        resultat.getString("pra_ville")
                );
                praticiens.add(praticien);
            }
            return praticiens;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return praticiens;
    }

    public static List<Visiteur> getVisiteurs() throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        List<Visiteur> visiteurs = new ArrayList<>();
        String requete = """
                    SELECT vis_matricule, 
                        vis_nom, 
                        vis_prenom
                    FROM Visiteur ;
                    """;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet resultat = stmt.executeQuery(requete);
            while (resultat.next()) {
                Visiteur visiteur = new Visiteur(
                        resultat.getString("vis_matricule"),
                        resultat.getString("vis_nom"),
                        resultat.getString("vis_prenom")
                        );
                visiteurs.add(visiteur);
            }
            return visiteurs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visiteurs;
    }

    public static List<RapportVisite> getRapportsVisite(String matricule , int mois, int année) throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion();

        List<RapportVisite> rapportsVisites = FXCollections.observableArrayList();

        String requete = "SELECT rv.rap_num, rv.rap_date_visite, rv.rap_date_saisie, rv.rap_bilan, rv.rap_motif, rv.rap_coef_confiance, rv.rap_lu, rv.vis_matricule " +
                "FROM RapportVisite rv " +
                "INNER JOIN Visiteur as v " +
                "ON rv.vis_matricule = v.vis_matricule " +
                "WHERE v.vis_matricule = ? " +
                "AND MONTH(rv.rap_date_visite) = ? " +
                "AND YEAR(rv.rap_date_visite) = ?";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            requetePreparee.setInt(2, mois);
            requetePreparee.setInt(3, année);
            ResultSet resultat = requetePreparee.executeQuery();
            List<RapportVisite> rapportsVisite = new ArrayList<>();
            while (resultat.next()) {
                RapportVisite rapportVisite = new RapportVisite();
                    rapportVisite.setNumero(resultat.getInt("rap_num"));
                    rapportVisite.setDateVisite(resultat.getDate("rv.rap_date_visite").toLocalDate());
                    rapportVisite.setDateRedaction(resultat.getDate("rv.rap_date_saisie").toLocalDate());
                    rapportVisite.setBilan(resultat.getString("rap_bilan"));
                    rapportVisite.setMotif(resultat.getString("rap_motif"));
                    rapportVisite.setCoefConfiance(resultat.getInt("rap_coef_confiance"));
                    rapportVisite.setLu(resultat.getBoolean("rap_lu"));

                    for (Visiteur visiteur : getVisiteurs()) {
                        if (visiteur.getMatricule() == resultat.getString("vis_matricule")) {
                            rapportVisite.setVisiteur(visiteur);
                        }
                    }

                    for (Praticien praticien : getPraticiens()) {
                        if (praticien.getNumero() == resultat.getInt("pra_num")) {
                            rapportVisite.setPraticien(praticien);
                        }
                    }

                    rapportsVisite.add(rapportVisite);
            }
            return rapportsVisites;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rapportsVisites;
    }

    public static void setRapportVisiteLu(String matricule , int numRapport) throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion();

        String requete = "UPDATE RapportVisite " +
                "SET rap_lu = true " +
                "WHERE vis_matricule = ?" +
                "AND rap_num = ? ;";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            requetePreparee.setInt(2,numRapport);
            requetePreparee.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getAnnee(){

        List<Integer> annees = FXCollections.observableArrayList();

        LocalDate dateActuelle = LocalDate.now();
        int anneeActuelle = dateActuelle.getYear();
        int i = anneeActuelle ;
        while (i != anneeActuelle - 5){
            annees.add(i);
            i -= 1 ;
        }
        return annees;
    }
}