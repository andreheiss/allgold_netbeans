/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geschaeftslogik;

import datenlogik.Standort;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S935
 */
@Stateless
public class StandortFacade extends AbstractFacade<Standort> {

    @PersistenceContext(unitName = "AllgoldPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StandortFacade() {
        super(Standort.class);
    }
    
}
