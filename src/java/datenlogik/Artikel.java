/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenlogik;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "artikel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a")
    , @NamedQuery(name = "Artikel.findByArtikelNr", query = "SELECT a FROM Artikel a WHERE a.artikelNr = :artikelNr")
    , @NamedQuery(name = "Artikel.findByName", query = "SELECT a FROM Artikel a WHERE a.name = :name")
    , @NamedQuery(name = "Artikel.findByBeschreibung", query = "SELECT a FROM Artikel a WHERE a.beschreibung = :beschreibung")
    , @NamedQuery(name = "Artikel.findByKategorie", query = "SELECT a FROM Artikel a WHERE a.kategorie = :kategorie")
    , @NamedQuery(name = "Artikel.findByPreis", query = "SELECT a FROM Artikel a WHERE a.preis = :preis")})
public class Artikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ArtikelNr")
    private Integer artikelNr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Beschreibung")
    private String beschreibung;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Kategorie")
    private String kategorie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Preis")
    private BigDecimal preis;

    public Artikel() {
    }

    public Artikel(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public Artikel(Integer artikelNr, String name, String beschreibung, String kategorie, BigDecimal preis) {
        this.artikelNr = artikelNr;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.preis = preis;
    }

    public Integer getArtikelNr() {
        return artikelNr;
    }

    public void setArtikelNr(Integer artikelNr) {
        this.artikelNr = artikelNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelNr != null ? artikelNr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikel)) {
            return false;
        }
        Artikel other = (Artikel) object;
        if ((this.artikelNr == null && other.artikelNr != null) || (this.artikelNr != null && !this.artikelNr.equals(other.artikelNr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datenlogik.Artikel[ artikelNr=" + artikelNr + " ]";
    }
    
}
