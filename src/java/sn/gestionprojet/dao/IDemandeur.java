/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.util.List;
import sn.gestionprojet.entities.Demandeur;

/**
 *
 * @author darkshadow
 */
public interface IDemandeur {
    public int persist(Demandeur demandeur);
    public Demandeur login(String email, String password);
    public List<Demandeur> findAll();
    public Demandeur get(String email);
    
}
