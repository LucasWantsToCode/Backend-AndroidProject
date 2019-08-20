/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Domaine;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
public class DemandeurImpl implements IDemandeur{

    private DB db = new DB();
    private int ok;
    @Override
    public int persist(Demandeur demandeur) {
        String sql = "INSERT INTO demandeur VALUES(NULL, ?, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, demandeur.getNom());
            db.getPstm().setString(2, demandeur.getPrenom());
            db.getPstm().setString(3, demandeur.getEmail());
            db.getPstm().setString(4, demandeur.getPassword());
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public Demandeur login(String email, String password) {
        Demandeur demandeur = null;
        String sql = "SELECT * from demandeur where email = ? and password = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            db.getPstm().setString(2, password);
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                demandeur = new Demandeur();
                demandeur.setId(rs.getInt(1));
                demandeur.setNom(rs.getString(2));
                demandeur.setPrenom(rs.getString(3));
                demandeur.setEmail(rs.getString(4));
                demandeur.setPassword(rs.getString(5));  
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return demandeur;
    }
    
    @Override
    public List<Demandeur> findAll() {
       List<Demandeur> dems = new ArrayList<Demandeur>();
        String sql = "select * from demandeur";
        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                Demandeur dem = new Demandeur();
                dem.setId(rs.getInt(1));
                dem.setNom(rs.getString(2));
                dem.setPrenom(rs.getString(3));
                dem.setEmail(rs.getString(4));
                dem.setPassword(rs.getString(5));
               
              dems.add(dem);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return dems;
    }
    @Override
    public Demandeur get(String email) {
        Demandeur demandeur = null;
        String sql = "SELECT * from demandeur where email = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            ResultSet rs = db.executeSelect();
            if(rs.next()) {
                demandeur = new Demandeur();
                demandeur.setId(rs.getInt(1));
                demandeur.setNom(rs.getString(2));
                demandeur.setPrenom(rs.getString(3));
                demandeur.setEmail(rs.getString(4));
                demandeur.setPassword(rs.getString(5));  
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return demandeur;
    }
    
}
