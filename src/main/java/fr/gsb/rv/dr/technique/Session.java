package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

public class Session {

    private static Session session = null ;

    public Session(Session session) {
        this.session = session;
    }

    public static Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static void ouvrir(Visiteur visiteur){

    }

    public static void fermer(){

    }

    public static boolean estOuverte(){
        if(session == )
    }
}
