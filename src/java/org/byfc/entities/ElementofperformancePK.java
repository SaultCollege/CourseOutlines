/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fcarella
 */
@Embeddable
public class ElementofperformancePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "learningoutcome_id")
    private int learningoutcomeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "learningoutcome_courseoutline_id")
    private int learningoutcomeCourseoutlineId;

    public ElementofperformancePK() {
    }

    public ElementofperformancePK(int id, int learningoutcomeId, int learningoutcomeCourseoutlineId) {
        this.id = id;
        this.learningoutcomeId = learningoutcomeId;
        this.learningoutcomeCourseoutlineId = learningoutcomeCourseoutlineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearningoutcomeId() {
        return learningoutcomeId;
    }

    public void setLearningoutcomeId(int learningoutcomeId) {
        this.learningoutcomeId = learningoutcomeId;
    }

    public int getLearningoutcomeCourseoutlineId() {
        return learningoutcomeCourseoutlineId;
    }

    public void setLearningoutcomeCourseoutlineId(int learningoutcomeCourseoutlineId) {
        this.learningoutcomeCourseoutlineId = learningoutcomeCourseoutlineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) learningoutcomeId;
        hash += (int) learningoutcomeCourseoutlineId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementofperformancePK)) {
            return false;
        }
        ElementofperformancePK other = (ElementofperformancePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.learningoutcomeId != other.learningoutcomeId) {
            return false;
        }
        if (this.learningoutcomeCourseoutlineId != other.learningoutcomeCourseoutlineId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.byfc.entities.ElementofperformancePK[ id=" + id + ", learningoutcomeId=" + learningoutcomeId + ", learningoutcomeCourseoutlineId=" + learningoutcomeCourseoutlineId + " ]";
    }
    
}
