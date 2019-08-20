/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.entities;

/**
 *
 * @author darkshadow
 */
public class Commentaire {
    private int id;
    private Demandeur id_dem;
    private Offre id_off;
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Demandeur getId_dem() {
        return id_dem;
    }

    public void setId_dem(Demandeur id_dem) {
        this.id_dem = id_dem;
    }

    public Offre getId_off() {
        return id_off;
    }

    public void setId_off(Offre id_off) {
        this.id_off = id_off;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}
