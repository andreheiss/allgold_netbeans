/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geschaeftslogik;

import datenlogik.Artikel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author S935
 */
@Stateless
public class ArtikelFacade extends AbstractFacade<Artikel> {

    @PersistenceContext(unitName = "AllgoldPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtikelFacade() {
        super(Artikel.class);
    }
    
    public List<Artikel> allProducts()
    {
      List<Artikel> prod = em.createNamedQuery("Artikel.findAll").getResultList();
      return prod;
    }   
}