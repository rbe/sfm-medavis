/*
 * sfm-medavis
 * sfm-medavis-csv
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 11:55
 */
package eu.artofcoding.sfm.medavis.csv.importer.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data for Medavis #145.
 * @author rbe
 */
public class Medavis145Bean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis145Bean.class);

    private String kategorie;
    private String kuerzel;
    private String bezeichnung;
    private String pzn;
    private String einheit;
    private Double preis;
    private String refnr;

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getPzn() {
        return pzn;
    }

    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public String getRefnr() {
        return refnr;
    }

    public void setRefnr(String refnr) {
        this.refnr = refnr;
    }

    public String toString() {
        return String.format("%s[kategorie=%s,kuerzel=%s,bezeichnung=%s,pzn=%s,einheit=%s,preis=%s,refnr=%s]", this.getClass(), kategorie, kuerzel, bezeichnung, pzn, einheit, preis, refnr);
    }

}
