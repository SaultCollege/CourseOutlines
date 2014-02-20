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
public class LearningoutcomePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseoutline_id")
    private int courseoutlineId;

    public LearningoutcomePK() {
    }

    public LearningoutcomePK(int id, int courseoutlineId) {
        this.id = id;
        this.courseoutlineId = courseoutlineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseoutlineId() {
        return courseoutlineId;
    }

    public void setCourseoutlineId(int courseoutlineId) {
        this.courseoutlineId = courseoutlineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) courseoutlineId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LearningoutcomePK)) {
            return false;
        }
        LearningoutcomePK other = (LearningoutcomePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.courseoutlineId != other.courseoutlineId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.byfc.entities.LearningoutcomePK[ id=" + id + ", courseoutlineId=" + courseoutlineId + " ]";
    }
    
}
