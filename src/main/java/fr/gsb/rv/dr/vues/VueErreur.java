package fr.gsb.rv.dr.vues;

import javafx.scene.control.Alert;

public class VueErreur extends Alert {

    public VueErreur(String title, String header, String content){
        super(AlertType.ERROR);
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(content);
    }
}
