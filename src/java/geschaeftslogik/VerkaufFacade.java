/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geschaeftslogik;

import datenlogik.Verkauf;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S935
 */
@Stateless
public class VerkaufFacade extends AbstractFacade<Verkauf> {

    @PersistenceContext(unitName = "AllgoldPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VerkaufFacade() {
        super(Verkauf.class);
    }
    
}
