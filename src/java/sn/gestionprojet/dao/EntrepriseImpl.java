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
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Domaine;

/**
 *
 * @author darkshadow
 */
public class EntrepriseImpl implements IEntreprise{

    private DB db = new DB();
    private int ok;
    @Override
    public int persist(Entreprise entreprise) {
        String sql = "INSERT INTO entreprise VALUES(NULL, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, entreprise.getNom());
            db.getPstm().setString(2, entreprise.getEmail());
            db.getPstm().setString(3, entreprise.getPassword());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public Entreprise Login(String email, String password) {
        
        Entreprise entreprise = null;
        String sql = "SELECT * from entreprise where email = ? and password = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            db.getPstm().setString(2, password);
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                entreprise = new Entreprise();
                entreprise.setId(rs.getInt(1));
                entreprise.setNom(rs.getString(2));
                entreprise.setEmail(rs.getString(3));
                entreprise.setPassword(rs.getString(4));  
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return entreprise;
    }
    @Override
    public Entreprise get(String email) {
        
        Entreprise entreprise = null;
        String sql = "SELECT * from entreprise where email = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                entreprise = new Entreprise();
                entreprise.setId(rs.getInt(1));
                entreprise.setNom(rs.getString(2));
                entreprise.setEmail(rs.getString(3));
                entreprise.setPassword(rs.getString(4));  
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return entreprise;
    }

    @Override
    public List<Cv> showCv() {
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
    
}
