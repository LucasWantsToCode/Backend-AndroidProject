/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import sn.gestionprojet.entities.Commentaire;

/**
 *
 * @author darkshadow
 */
public class CommentaireImpl implements ICommentaire{

    private DB db = new DB();
    private int ok;
    @Override
    public int persist(Commentaire com) {
         String sql = "INSERT INTO comment VALUES(NULL, ?, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, com.getLibelle());
            db.getPstm().setInt(2, com.getId_off().getId());
            db.getPstm().setInt(3, com.getId_dem().getId());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }
    
}
