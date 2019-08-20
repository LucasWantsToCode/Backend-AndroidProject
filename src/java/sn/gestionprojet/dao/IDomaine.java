/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.util.List;
import sn.gestionprojet.entities.Domaine;

/**
 *
 * @author darkshadow
 */
public interface IDomaine {
    public int persist(Domaine dom);
    public List<Domaine> findAll();
   
}
