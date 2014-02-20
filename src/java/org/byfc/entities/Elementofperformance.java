/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fcarella
 */
@Entity
@Table(name = "elementofperformance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elementofperformance.findAll", query = "SELECT e FROM Elementofperformance e"),
    @NamedQuery(name = "Elementofperformance.findById", query = "SELECT e FROM Elementofperformance e WHERE e.elementofperformancePK.id = :id"),
    @NamedQuery(name = "Elementofperformance.findByLearningoutcomeId", query = "SELECT e FROM Elementofperformance e WHERE e.elementofperformancePK.learningoutcomeId = :learningoutcomeId"),
    @NamedQuery(name = "Elementofperformance.findByLearningoutcomeCourseoutlineId", query = "SELECT e FROM Elementofperformance e WHERE e.elementofperformancePK.learningoutcomeCourseoutlineId = :learningoutcomeCourseoutlineId")})
public class Elementofperformance implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ElementofperformancePK elementofperformancePK;
    @Lob
    @Size(max = 16777215)
    @Column(name = "elementofperformance")
    private String elementofperformance;
    @JoinColumns({
        @JoinColumn(name = "learningoutcome_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "learningoutcome_courseoutline_id", referencedColumnName = "courseoutline_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Learningoutcome learningoutcome;

    public Elementofperformance() {
    }

    public Elementofperformance(ElementofperformancePK elementofperformancePK) {
        this.elementofperformancePK = elementofperformancePK;
    }

    public Elementofperformance(int id, int learningoutcomeId, int learningoutcomeCourseoutlineId) {
        this.elementofperformancePK = new ElementofperformancePK(id, learningoutcomeId, learningoutcomeCourseoutlineId);
    }

    public ElementofperformancePK getElementofperformancePK() {
        return elementofperformancePK;
    }

    public void setElementofperformancePK(ElementofperformancePK elementofperformancePK) {
        this.elementofperformancePK = elementofperformancePK;
    }

    public String getElementofperformance() {
        return elementofperformance;
    }

    public void setElementofperformance(String elementofperformance) {
        this.elementofperformance = elementofperformance;
    }

    public Learningoutcome getLearningoutcome() {
        return learningoutcome;
    }

    public void setLearningoutcome(Learningoutcome learningoutcome) {
        this.learningoutcome = learningoutcome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elementofperformancePK != null ? elementofperformancePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elementofperformance)) {
            return false;
        }
        Elementofperformance other = (Elementofperformance) object;
        if ((this.elementofperformancePK == null && other.elementofperformancePK != null) || (this.elementofperformancePK != null && !this.elementofperformancePK.equals(other.elementofperformancePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.byfc.entities.Elementofperformance[ elementofperformancePK=" + elementofperformancePK + " ]";
    }
    
}
