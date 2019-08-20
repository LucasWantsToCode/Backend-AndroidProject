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
import sn.gestionprojet.entities.Favorite;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
public class FavoriteImpl implements IFavorite{

    private DB db = new DB();
    private int ok;
    @Override
    public int persist(Favorite fav) {
         String sql = "INSERT INTO favorites VALUES(NULL, ?, ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, fav.getDemandeur().getId());
            db.getPstm().setInt(2, fav.getOffre().getId());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }
    
    @Override
    public int update(Favorite fav) {
         String sql = "UPDATE favorites set dem_id = ?, off_id = ?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, fav.getDemandeur().getId());
            db.getPstm().setInt(2, fav.getOffre().getId());
            
            ok = db.executeMaj();//Insertion dans la base de données
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Favorite> favorite(Demandeur dema) {
        List<Favorite> favs = new ArrayList<>();
        String sql = "SELECT * FROM favorites where dem_id = ?";
        
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, dema.getId());
            ResultSet rs = db.executeSelect();
            while(rs.next()){
                Favorite fav = new Favorite();
                fav.setId(rs.getInt(1));
                Demandeur dem = new Demandeur();
                dem.setId(rs.getInt(2));
                fav.setDemandeur(dem);
                Offre offre = new Offre();
                offre.setId(rs.getInt(3));
                fav.setOffre(offre);
                
                favs.add(fav);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return favs;
    }
    
}
