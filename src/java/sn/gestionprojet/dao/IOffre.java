/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gestionprojet.dao;

import java.util.List;
import sn.gestionprojet.entities.Demandeur;
import sn.gestionprojet.entities.Entreprise;
import sn.gestionprojet.entities.Offre;

/**
 *
 * @author darkshadow
 */
public interface IOffre {
    public int persiste(Offre offre);
    public List<Offre> getOffre(String motcle);
    public List<Offre> domdemOffre(Demandeur dem);
    
    
}
