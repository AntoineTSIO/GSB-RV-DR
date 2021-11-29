package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String requete = "SELECT Praticien.pra_num, Praticien.pra_nom, Praticien.pra_ville, Praticien.pra_coefnotoriete, rap_date_visite, rap_coef_confiance\n" +
                "FROM Praticien\n" +
                "INNER JOIN RapportVisite\n" +
                "ON Praticien.pra_num = RapportVisite.pra_num\n" +
                "WHERE RapportVisite.rap_coef_confiance < 5";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            List<Praticien> praticiens = new ArrayList<Praticien>();
            while (resultat.next()) {
                praticiens.add(new Praticien(resultat.getInt("pra_num"),
                        resultat.getString("pra_nom"),
                        resultat.getString("pra_ville"),
                        resultat.getDouble("pra_coefnotoriete"),
                        resultat.getDate("rap_date_visite").toLocalDate(),
                        resultat.getInt("rap_coef_confiance")));
            }
            return praticiens;
        } catch (Exception e) {
            return null;
        }
    }
}