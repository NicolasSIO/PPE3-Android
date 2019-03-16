package fr.gsb.rv.modeles;

import java.util.ArrayList;
import java.util.List;

import fr.gsb.rv.entites.Visiteur;

public class ModeleGsb {

    private static ModeleGsb modele = null;

    private List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();

    private ModeleGsb(){
        super();
        this.peuplerVisiteur();
    }

    public static ModeleGsb getInstance(){
        if( ModeleGsb.modele == null ){
            modele = new ModeleGsb();
        }
        return ModeleGsb.modele;
    }

    public void peuplerVisiteur(){
        this.lesVisiteurs.add( new Visiteur("a131","azerty","Villechalane", "Louis") );
        this.lesVisiteurs.add( new Visiteur("b13","azerty","Bentot", "Pascal") );
        this.lesVisiteurs.add( new Visiteur("b16","azerty","Bioret", "Luc") );
        this.lesVisiteurs.add( new Visiteur("a137","azerty","Andre", "David") );
    }

    public Visiteur seConnecter(String matricule, String mdp ){
        for( Visiteur unVisiteur : this.lesVisiteurs ){
            if( unVisiteur.getMatricule().equals(matricule) && unVisiteur.getMdp().equals(mdp) ){
                return unVisiteur;
            }
        }
        return null;
    }

}
