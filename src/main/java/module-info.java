module fr.gsb.rv.dr.gsbrvdr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.gsb.rv.dr.gsbrvdr to javafx.fxml;
    exports fr.gsb.rv.dr.gsbrvdr;
    exports fr.gsb.rv.dr.vues;
    opens fr.gsb.rv.dr.vues to javafx.fxml;
    exports fr.gsb.rv.dr.panneaux;
    opens fr.gsb.rv.dr.panneaux to javafx.fxml;

    opens fr.gsb.rv.dr.entites to javafx.graphics, javafx.fxml, javafx.base;
    opens fr.gsb.rv.dr.technique to javafx.base, javafx.fxml, javafx.graphics;
}