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

/**
 *
 * @author darkshadow
 */
public class DomaineImpl implements IDomaine{
    private DB db = new DB();
    private int ok;

    @Override
    public int persist(Domaine dom) {
        String sql = "INSERT INTO domaine VALUES(NULL, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, dom.getNom());
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }
    
    @Override
    public List<Domaine> findAll() {
       List<Domaine> doms = new ArrayList<>();
        String sql = "select * from domaine";
        try{
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while(rs.next()) {
                Domaine dom = new Domaine();
                dom.setId(rs.getInt(1));
                dom.setNom(rs.getString(2));
               
              doms.add(dom);
            }
            rs.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return doms;
    }
    
}
