/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.util.List;
import sn.gestionprojet.entities.Cv;
import sn.gestionprojet.entities.Demandeur;

/**
 *
 * @author darkshadow
 */
public interface ICv {
    public int persist(Cv cv);
    public int update(Cv cv);
    public Cv get(Demandeur dem);
    public List<Cv> getCv();
    
}
