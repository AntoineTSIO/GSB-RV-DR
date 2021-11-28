package fr.gsb.rv.dr.vues;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;


public class VueConnexion extends Dialog<Pair<String,String>> {

    public VueConnexion() {
        super();
        this.setTitle("Authentification");
        this.setHeaderText("Saisir vos données de connexion.");
        Label LblMatricule = new Label("Matricule : ");
        TextField InputMatricule = new TextField();
        Label LblMdp = new Label("Mot de passe : ");
        PasswordField InputMdp = new PasswordField();
        GridPane gridPane = new GridPane();
        gridPane.add(LblMatricule,0,1);
        gridPane.add(InputMatricule,1,1);
        gridPane.add(LblMdp,0,2);
        gridPane.add(InputMdp,1,2);
        this.getDialogPane().setContent(gridPane);
        ButtonType btnSeConnecter = new ButtonType("Se Connecter" , ButtonBar.ButtonData.OK_DONE);
        ButtonType btnAnnuler = new ButtonType("Annuler" , ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(btnSeConnecter , btnAnnuler);
        this.setResultConverter(new Callback<ButtonType, Pair<String, String>>() {
            @Override
            public Pair<String, String> call(ButtonType buttonType) {
                if(buttonType == btnSeConnecter){
                    return new Pair<String,String>(InputMatricule.getText(),InputMdp.getText());
                }
                return null;
            }
        });

    }
}
