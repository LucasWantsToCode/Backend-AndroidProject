/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.util.List;
import sn.gestionprojet.entities.Favorite;
import sn.gestionprojet.entities.Demandeur;

/**
 *
 * @author darkshadow
 */
public interface IFavorite {
    public int persist(Favorite fav);
    public int update(Favorite fav);
    public List<Favorite> favorite(Demandeur dema);
}
