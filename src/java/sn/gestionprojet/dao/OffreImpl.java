/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sn.gestionprojet.entities.Cv;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Domaine;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
public class OffreImpl implements IOffre {

    private DB db = new DB();
    private int ok;
    @Override
    public int persiste(Offre offre) {
        String sql = "INSERT INTO offre VALUES(NULL, ?, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, offre.getDateO());
            db.getPstm().setString(2, offre.getLibelle());
            db.getPstm().setInt(3, offre.getEntreprise().getId());
            db.getPstm().setInt(4, offre.getDomaine().getId());
            
            ok = db.executeMaj(); //Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Offre> getOffre(String motcle) {
        List<Offre> offres = new ArrayList<Offre>();
        String sql = "SELECT * FROM offre where libelle like ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, "%" +motcle+"%");
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                Offre offre = new Offre();
                offre.setId(rs.getInt(1));
                offre.setDateO(rs.getString(2));
                offre.setLibelle(rs.getString(3));
               
               //Entreprise
               Entreprise entreprise = new Entreprise();
               entreprise.setId(rs.getInt(4));
               offre.setEntreprise(entreprise);
               
               //Domaine
               Domaine domaine = new Domaine();
               domaine.setId(rs.getInt(5));
               offre.setDomaine(domaine);
                
                offres.add(offre);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return offres;
    }

    @Override
    public List<Offre> domdemOffre(Demandeur dem) {
       List<Offre> offres = new ArrayList<>();
       Offre offre = null;
        String sql = "select * from offre where dom_id in (select c.dom_id from cv c where c.dem_id = ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, dem.getId());
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                offre = new Offre();
                offre.setId(rs.getInt(1));
                offre.setDateO(rs.getString(2));
                offre.setLibelle(rs.getString(3));
               
               //Entreprise
               Entreprise entreprise = new Entreprise();
               entreprise.setId(rs.getInt(4));
               offre.setEntreprise(entreprise);
               
               //Domaine
               Domaine domaine = new Domaine();
               domaine.setId(rs.getInt(5));
               offre.setDomaine(domaine);
                
                offres.add(offre);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return offres;
    }
    
}
