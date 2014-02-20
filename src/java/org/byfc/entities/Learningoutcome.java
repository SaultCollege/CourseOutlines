/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcarella
 */
@Entity
@Table(name = "learningoutcome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Learningoutcome.findAll", query = "SELECT l FROM Learningoutcome l"),
    @NamedQuery(name = "Learningoutcome.findById", query = "SELECT l FROM Learningoutcome l WHERE l.learningoutcomePK.id = :id"),
    @NamedQuery(name = "Learningoutcome.findByCourseoutlineId", query = "SELECT l FROM Learningoutcome l WHERE l.learningoutcomePK.courseoutlineId = :courseoutlineId")})
public class Learningoutcome implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LearningoutcomePK learningoutcomePK;
    @Lob
    @Size(max = 16777215)
    @Column(name = "learningoutcome")
    private String learningoutcome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learningoutcome")
    private List<Elementofperformance> elementofperformanceList;
    @JoinColumn(name = "courseoutline_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Courseoutline courseoutline;

    public Learningoutcome() {
    }

    public Learningoutcome(LearningoutcomePK learningoutcomePK) {
        this.learningoutcomePK = learningoutcomePK;
    }

    public Learningoutcome(int id, int courseoutlineId) {
        this.learningoutcomePK = new LearningoutcomePK(id, courseoutlineId);
    }

    public LearningoutcomePK getLearningoutcomePK() {
        return learningoutcomePK;
    }

    public void setLearningoutcomePK(LearningoutcomePK learningoutcomePK) {
        this.learningoutcomePK = learningoutcomePK;
    }

    public String getLearningoutcome() {
        return learningoutcome;
    }

    public void setLearningoutcome(String learningoutcome) {
        this.learningoutcome = learningoutcome;
    }

    @XmlTransient
    public List<Elementofperformance> getElementofperformanceList() {
        return elementofperformanceList;
    }

    public void setElementofperformanceList(List<Elementofperformance> elementofperformanceList) {
        this.elementofperformanceList = elementofperformanceList;
    }

    public Courseoutline getCourseoutline() {
        return courseoutline;
    }

    public void setCourseoutline(Courseoutline courseoutline) {
        this.courseoutline = courseoutline;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (learningoutcomePK != null ? learningoutcomePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Learningoutcome)) {
            return false;
        }
        Learningoutcome other = (Learningoutcome) object;
        if ((this.learningoutcomePK == null && other.learningoutcomePK != null) || (this.learningoutcomePK != null && !this.learningoutcomePK.equals(other.learningoutcomePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.byfc.entities.Learningoutcome[ learningoutcomePK=" + learningoutcomePK + " ]";
    }
    
}
