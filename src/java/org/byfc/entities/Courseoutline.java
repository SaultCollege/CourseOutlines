/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.byfc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcarella
 */
@Entity
@Table(name = "courseoutline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courseoutline.findAll", query = "SELECT c FROM Courseoutline c"),
    @NamedQuery(name = "Courseoutline.findById", query = "SELECT c FROM Courseoutline c WHERE c.id = :id"),
    @NamedQuery(name = "Courseoutline.findByCoursetitle", query = "SELECT c FROM Courseoutline c WHERE c.coursetitle = :coursetitle"),
    @NamedQuery(name = "Courseoutline.findByCodenumber", query = "SELECT c FROM Courseoutline c WHERE c.codenumber = :codenumber"),
    @NamedQuery(name = "Courseoutline.findBySemester", query = "SELECT c FROM Courseoutline c WHERE c.semester = :semester"),
    @NamedQuery(name = "Courseoutline.findByProgram", query = "SELECT c FROM Courseoutline c WHERE c.program = :program"),
    @NamedQuery(name = "Courseoutline.findByAuthor", query = "SELECT c FROM Courseoutline c WHERE c.author = :author"),
    @NamedQuery(name = "Courseoutline.findByDate", query = "SELECT c FROM Courseoutline c WHERE c.date = :date"),
    @NamedQuery(name = "Courseoutline.findByPreviousoutlinedated", query = "SELECT c FROM Courseoutline c WHERE c.previousoutlinedated = :previousoutlinedated"),
    @NamedQuery(name = "Courseoutline.findByApprovedby", query = "SELECT c FROM Courseoutline c WHERE c.approvedby = :approvedby"),
    @NamedQuery(name = "Courseoutline.findByTotalcredits", query = "SELECT c FROM Courseoutline c WHERE c.totalcredits = :totalcredits"),
    @NamedQuery(name = "Courseoutline.findByPrerequisites", query = "SELECT c FROM Courseoutline c WHERE c.prerequisites = :prerequisites")})
public class Courseoutline implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "coursetitle")
    private String coursetitle;
    @Size(max = 45)
    @Column(name = "codenumber")
    private String codenumber;
    @Column(name = "semester")
    private Integer semester;
    @Size(max = 255)
    @Column(name = "program")
    private String program;
    @Size(max = 45)
    @Column(name = "author")
    private String author;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "previousoutlinedated")
    @Temporal(TemporalType.DATE)
    private Date previousoutlinedated;
    @Size(max = 45)
    @Column(name = "approvedby")
    private String approvedby;
    @Column(name = "totalcredits")
    private Integer totalcredits;
    @Size(max = 45)
    @Column(name = "prerequisites")
    private String prerequisites;
    @Lob
    @Size(max = 16777215)
    @Column(name = "copyright")
    private String copyright;
    @Lob
    @Size(max = 16777215)
    @Column(name = "coursedescription")
    private String coursedescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseoutline")
    private List<Learningoutcome> learningoutcomeList;

    public Courseoutline() {
    }

    public Courseoutline(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

    public String getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(String codenumber) {
        this.codenumber = codenumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPreviousoutlinedated() {
        return previousoutlinedated;
    }

    public void setPreviousoutlinedated(Date previousoutlinedated) {
        this.previousoutlinedated = previousoutlinedated;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Integer getTotalcredits() {
        return totalcredits;
    }

    public void setTotalcredits(Integer totalcredits) {
        this.totalcredits = totalcredits;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription;
    }

    @XmlTransient
    public List<Learningoutcome> getLearningoutcomeList() {
        return learningoutcomeList;
    }

    public void setLearningoutcomeList(List<Learningoutcome> learningoutcomeList) {
        this.learningoutcomeList = learningoutcomeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courseoutline)) {
            return false;
        }
        Courseoutline other = (Courseoutline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.byfc.entities.Courseoutline[ id=" + id + " ]";
    }
    
}
