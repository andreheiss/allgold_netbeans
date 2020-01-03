/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geschaeftslogik;

import datenlogik.Inventar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author S935
 */
@Stateless
public class InventarFacade extends AbstractFacade<Inventar> {

    @PersistenceContext(unitName = "AllgoldPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarFacade() {
        super(Inventar.class);
    }
    
    public List<Inventar> inventoryByStation(java.lang.Integer standortNr)
    {
       List<Inventar> inv = em.createNamedQuery("Inventar.findByStandortNr")
               .setParameter("standortNr", standortNr)
               .getResultList();
       return inv;
    }
    
    public List<Inventar> inventoryByProduct(java.lang.Integer artikelNr)
    {
       List<Inventar> inv = em.createNamedQuery("Inventar.findByArtikelNr")
               .setParameter("artikelNr", artikelNr)
               .getResultList();
       return inv;
    }
}
