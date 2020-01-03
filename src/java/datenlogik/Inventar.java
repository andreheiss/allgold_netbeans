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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S935
 */
@Entity
@Table(name = "inventar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventar.findAll", query = "SELECT i FROM Inventar i")
    , @NamedQuery(name = "Inventar.findByInventarNr", query = "SELECT i FROM Inventar i WHERE i.inventarNr = :inventarNr")
    , @NamedQuery(name = "Inventar.findByStandortNr", query = "SELECT i FROM Inventar i WHERE i.standortNr = :standortNr")
    , @NamedQuery(name = "Inventar.findByArtikelNr", query = "SELECT i FROM Inventar i WHERE i.artikelNr = :artikelNr")
    , @NamedQuery(name = "Inventar.findByStueckzahlIST", query = "SELECT i FROM Inventar i WHERE i.stueckzahlIST = :stueckzahlIST")
    , @NamedQuery(name = "Inventar.findByStueckzahlSOLL", query = "SELECT i FROM Inventar i WHERE i.stueckzahlSOLL = :stueckzahlSOLL")})
public class Inventar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InventarNr")
    private Integer inventarNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StandortNr")
    private int standortNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArtikelNr")
    private int artikelNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StueckzahlIST")
    private int stueckzahlIST;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StueckzahlSOLL")
    private int stueckzahlSOLL;

    public Inventar() {
    }

    public Inventar(Integer inventarNr) {
        this.inventarNr = inventarNr;
    }

    public Inventar(Integer inventarNr, int standortNr, int artikelNr, int stueckzahlIST, int stueckzahlSOLL) {
        this.inventarNr = inventarNr;
        this.standortNr = standortNr;
        this.artikelNr = artikelNr;
        this.stueckzahlIST = stueckzahlIST;
        this.stueckzahlSOLL = stueckzahlSOLL;
    }

    public Integer getInventarNr() {
        return inventarNr;
    }

    public void setInventarNr(Integer inventarNr) {
        this.inventarNr = inventarNr;
    }

    public int getStandortNr() {
        return standortNr;
    }

    public void setStandortNr(int standortNr) {
        this.standortNr = standortNr;
    }

    public int getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(int artikelNr) {
        this.artikelNr = artikelNr;
    }

    public int getStueckzahlIST() {
        return stueckzahlIST;
    }

    public void setStueckzahlIST(int stueckzahlIST) {
        this.stueckzahlIST = stueckzahlIST;
    }

    public int getStueckzahlSOLL() {
        return stueckzahlSOLL;
    }

    public void setStueckzahlSOLL(int stueckzahlSOLL) {
        this.stueckzahlSOLL = stueckzahlSOLL;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventarNr != null ? inventarNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventar)) {
            return false;
        }
        Inventar other = (Inventar) object;
        if ((this.inventarNr == null && other.inventarNr != null) || (this.inventarNr != null && !this.inventarNr.equals(other.inventarNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datenlogik.Inventar[ inventarNr=" + inventarNr + " ]";
    }
    
}
