/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.byfc.entities.Courseoutline;

/**
 *
 * @author fcarella
 */
@Stateless
public class CourseoutlineFacade extends AbstractFacade<Courseoutline> {
    @PersistenceContext(unitName = "CourseOutlinesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseoutlineFacade() {
        super(Courseoutline.class);
    }
    
}
