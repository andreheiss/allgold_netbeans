/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenlogik;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S935
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByPersNr", query = "SELECT p FROM Person p WHERE p.persNr = :persNr")
    , @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name")
    , @NamedQuery(name = "Person.findByVorname", query = "SELECT p FROM Person p WHERE p.vorname = :vorname")
    , @NamedQuery(name = "Person.findByBenutzername", query = "SELECT p FROM Person p WHERE p.benutzername = :benutzername")
    , @NamedQuery(name = "Person.findByPosition", query = "SELECT p FROM Person p WHERE p.position = :position")
    , @NamedQuery(name = "Person.findByStandortNr", query = "SELECT p FROM Person p WHERE p.standortNr = :standortNr")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PersNr")
    private Integer persNr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Vorname")
    private String vorname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Benutzername")
    private String benutzername;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "Passwort")
    private String passwort;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StandortNr")
    private int standortNr;

    public Person() {
    }

    public Person(Integer persNr) {
        this.persNr = persNr;
    }

    public Person(Integer persNr, String name, String vorname, String benutzername, String passwort, String position, int standortNr) {
        this.persNr = persNr;
        this.name = name;
        this.vorname = vorname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.position = position;
        this.standortNr = standortNr;
    }

    public Integer getPersNr() {
        return persNr;
    }

    public void setPersNr(Integer persNr) {
        this.persNr = persNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStandortNr() {
        return standortNr;
    }

    public void setStandortNr(int standortNr) {
        this.standortNr = standortNr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (persNr != null ? persNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.persNr == null && other.persNr != null) || (this.persNr != null && !this.persNr.equals(other.persNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datenlogik.Person[ persNr=" + persNr + " ]";
    }
    
}
