/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;
import java.util.List;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Cv;

/**
 *
 * @author darkshadow
 */
public interface IEntreprise {
    public int persist(Entreprise entreprise);
    public Entreprise Login(String email, String password);
    public Entreprise get(String email);
    public List<Cv> showCv();
    
}
