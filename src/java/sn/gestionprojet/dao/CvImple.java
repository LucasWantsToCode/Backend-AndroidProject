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

/**
 *
 * @author darkshadow
 */
public class CvImple implements ICv{

    private DB db = new DB();
    private int ok;
    @Override
    public int persist(Cv cv) {
        String sql = "INSERT INTO cv VALUES(NULL, ?, ?, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, cv.getFormation());
            db.getPstm().setString(2, cv.getCompetence());
            db.getPstm().setInt(3, cv.getDemandeur().getId());
            db.getPstm().setInt(4, cv.getDomaine().getId());
            db.getPstm().setString(5, cv.getPassion());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Cv cv) {
        String sql = "UPDATE cv set formation = ?, competences = ?, dom_id = ?, passion = ? where id = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, cv.getFormation());
            db.getPstm().setString(2, cv.getCompetence());
            db.getPstm().setInt(3, cv.getDomaine().getId());
            db.getPstm().setString(4, cv.getPassion());
            db.getPstm().setInt(5, cv.getId());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Cv> getCv() {
       List<Cv> curiculum = new ArrayList<Cv>();
        String sql = "SELECT * FROM cv";
        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                Cv cv = new Cv();
                cv.setId(rs.getInt(1));
                cv.setFormation(rs.getString(2));
                cv.setCompetence(rs.getString(3));
               
               //Demandeur
               Demandeur demandeur = new Demandeur();
               demandeur.setId(rs.getInt(4));
               cv.setDemandeur(demandeur);
               
               //Domaine
               Domaine domaine = new Domaine();
               domaine.setId(rs.getInt(5));
               cv.setDomaine(domaine);
               
               cv.setPassion(rs.getString(6));
                
                curiculum.add(cv);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return curiculum;
    }
    
    public Cv get(Demandeur dem) {
        Cv cv = null;
        String sql = "SELECT * from cv where dem_id = (select id from demandeur where email = ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, dem.getEmail());
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                cv = new Cv();
                cv.setId(rs.getInt(1));
                cv.setFormation(rs.getString(2));
                cv.setCompetence(rs.getString(3));
                
                Demandeur demandeur = new Demandeur();
               demandeur.setId(4);
               cv.setDemandeur(demandeur);
               
               //Domaine
               Domaine domaine = new Domaine();
               domaine.setId(rs.getInt(5));
               cv.setDomaine(domaine);
               cv.setPassion(rs.getString(6));
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return cv;
    }
}
