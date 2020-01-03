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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S935
 */
@Entity
@Table(name = "standort")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Standort.findAll", query = "SELECT s FROM Standort s")
    , @NamedQuery(name = "Standort.findByStandortNr", query = "SELECT s FROM Standort s WHERE s.standortNr = :standortNr")
    , @NamedQuery(name = "Standort.findByOrt", query = "SELECT s FROM Standort s WHERE s.ort = :ort")
    , @NamedQuery(name = "Standort.findByPlz", query = "SELECT s FROM Standort s WHERE s.plz = :plz")
    , @NamedQuery(name = "Standort.findByStrasse", query = "SELECT s FROM Standort s WHERE s.strasse = :strasse")
    , @NamedQuery(name = "Standort.findByTyp", query = "SELECT s FROM Standort s WHERE s.typ = :typ")
    , @NamedQuery(name = "Standort.findByBeschreibung", query = "SELECT s FROM Standort s WHERE s.beschreibung = :beschreibung")
    , @NamedQuery(name = "Standort.findByLon", query = "SELECT s FROM Standort s WHERE s.lon = :lon")
    , @NamedQuery(name = "Standort.findByLat", query = "SELECT s FROM Standort s WHERE s.lat = :lat")})
public class Standort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "StandortNr")
    private Integer standortNr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ort")
    private String ort;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Plz")
    private String plz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Strasse")
    private String strasse;
    @Column(name = "Typ")
    private Character typ;
    @Size(max = 40)
    @Column(name = "Beschreibung")
    private String beschreibung;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LON")
    private String lon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LAT")
    private String lat;

    public Standort() {
    }

    public Standort(Integer standortNr) {
        this.standortNr = standortNr;
    }

    public Standort(Integer standortNr, String ort, String plz, String strasse, String lon, String lat) {
        this.standortNr = standortNr;
        this.ort = ort;
        this.plz = plz;
        this.strasse = strasse;
        this.lon = lon;
        this.lat = lat;
    }

    public Integer getStandortNr() {
        return standortNr;
    }

    public void setStandortNr(Integer standortNr) {
        this.standortNr = standortNr;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Character getTyp() {
        return typ;
    }

    public void setTyp(Character typ) {
        this.typ = typ;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (standortNr != null ? standortNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Standort)) {
            return false;
        }
        Standort other = (Standort) object;
        if ((this.standortNr == null && other.standortNr != null) || (this.standortNr != null && !this.standortNr.equals(other.standortNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datenlogik.Standort[ standortNr=" + standortNr + " ]";
    }
    
}
