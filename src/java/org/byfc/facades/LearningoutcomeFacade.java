/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.byfc.entities.Courseoutline;
import org.byfc.entities.Learningoutcome;

/**
 *
 * @author fcarella
 */
@Stateless
public class LearningoutcomeFacade extends AbstractFacade<Learningoutcome> {
    @PersistenceContext(unitName = "CourseOutlinesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LearningoutcomeFacade() {
        super(Learningoutcome.class);
    }

    public List<Learningoutcome> getItemsByCourseoutline(Courseoutline courseoutline) {
        TypedQuery<Learningoutcome> query= em.createQuery("SELECT l FROM Learningoutcome l WHERE l.learningoutcomePK.courseoutlineId = :courseoutlineId", Learningoutcome.class);
        query.setParameter("courseoutlineId", courseoutline.getId());
        List<Learningoutcome> list = query.getResultList();
        return list;
    }
    
}
