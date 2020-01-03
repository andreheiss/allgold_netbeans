/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenlogik;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S935
 */
@Entity
@Table(name = "verkauf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Verkauf.findAll", query = "SELECT v FROM Verkauf v")
    , @NamedQuery(name = "Verkauf.findByVerkaufNr", query = "SELECT v FROM Verkauf v WHERE v.verkaufNr = :verkaufNr")
    , @NamedQuery(name = "Verkauf.findByStandortNr", query = "SELECT v FROM Verkauf v WHERE v.standortNr = :standortNr")
    , @NamedQuery(name = "Verkauf.findByPersNr", query = "SELECT v FROM Verkauf v WHERE v.persNr = :persNr")
    , @NamedQuery(name = "Verkauf.findByArtikelNr", query = "SELECT v FROM Verkauf v WHERE v.artikelNr = :artikelNr")
    , @NamedQuery(name = "Verkauf.findByAnzahl", query = "SELECT v FROM Verkauf v WHERE v.anzahl = :anzahl")
    , @NamedQuery(name = "Verkauf.findByZeitpunkt", query = "SELECT v FROM Verkauf v WHERE v.zeitpunkt = :zeitpunkt")})
public class Verkauf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VerkaufNr")
    private Integer verkaufNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StandortNr")
    private int standortNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersNr")
    private int persNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArtikelNr")
    private int artikelNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Anzahl")
    private int anzahl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Zeitpunkt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zeitpunkt;

    public Verkauf() {
    }

    public Verkauf(Integer verkaufNr) {
        this.verkaufNr = verkaufNr;
    }

    public Verkauf(Integer verkaufNr, int standortNr, int persNr, int artikelNr, int anzahl, Date zeitpunkt) {
        this.verkaufNr = verkaufNr;
        this.standortNr = standortNr;
        this.persNr = persNr;
        this.artikelNr = artikelNr;
        this.anzahl = anzahl;
        this.zeitpunkt = zeitpunkt;
    }

    public Integer getVerkaufNr() {
        return verkaufNr;
    }

    public void setVerkaufNr(Integer verkaufNr) {
        this.verkaufNr = verkaufNr;
    }

    public int getStandortNr() {
        return standortNr;
    }

    public void setStandortNr(int standortNr) {
        this.standortNr = standortNr;
    }

    public int getPersNr() {
        return persNr;
    }

    public void setPersNr(int persNr) {
        this.persNr = persNr;
    }

    public int getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(int artikelNr) {
        this.artikelNr = artikelNr;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verkaufNr != null ? verkaufNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Verkauf)) {
            return false;
        }
        Verkauf other = (Verkauf) object;
        if ((this.verkaufNr == null && other.verkaufNr != null) || (this.verkaufNr != null && !this.verkaufNr.equals(other.verkaufNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datenlogik.Verkauf[ verkaufNr=" + verkaufNr + " ]";
    }
    
}
