package fr.gsb.rv.technique;

import fr.gsb.rv.entites.Visiteur;

public class Session {

    private static Session session = null;
    private Visiteur unVisiteur;

    private Session(Visiteur unVisiteur){
        this.unVisiteur = unVisiteur;
    }

    public static Session getSession() {
        return session;
    }

    public void setSession(Session session){
        this.session = session;
    }

    public static void ouvrir(Visiteur unVisiteur){
        session = new Session(unVisiteur);
    }

    public void fermer(){
        session = null;
    }

    public Visiteur getUnVisiteur() {
        return unVisiteur;
    }

    public void setUnVisiteur(Visiteur unVisiteur){
        this.unVisiteur = unVisiteur;
    }
}