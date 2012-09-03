/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data for Medavis #10025.
 * @author rbe
 */
public class Medavis10025Bean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis10025Bean.class);

    private String kostentraeger;
    private String episode;
    private String patient;
    private java.util.Date geburtstag;
    private java.util.Date untersuchung;
    private String untKuerzel;
    private String untKurzbezeichnung;
    private String materialKuerzel;
    private String material;
    private Double menge;
    private String einheit;

    public String getKostentraeger() {
        return kostentraeger;
    }

    public void setKostentraeger(String kostentraeger) {
        this.kostentraeger = kostentraeger;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public java.util.Date getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public java.util.Date getUntersuchung() {
        return untersuchung;
    }

    public void setUntersuchung(java.util.Date untersuchung) {
        this.untersuchung = untersuchung;
    }

    public String getUntKuerzel() {
        return untKuerzel;
    }

    public void setUntKuerzel(String untKuerzel) {
        this.untKuerzel = untKuerzel;
    }

    public String getUntKurzbezeichnung() {
        return untKurzbezeichnung;
    }

    public void setUntKurzbezeichnung(String untKurzbezeichnung) {
        this.untKurzbezeichnung = untKurzbezeichnung;
    }

    public String getMaterialKuerzel() {
        return materialKuerzel;
    }

    public void setMaterialKuerzel(String materialKuerzel) {
        this.materialKuerzel = materialKuerzel;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getMenge() {
        return menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public String toString() {
        return String.format("%s[kostentraeger=%s,episode=%s,untersuchung=%s,untKuerzel=%s,untKurzbezeichnung=%s,materialKuerzel=%s,menge=%s,einheit=%s]", this.getClass(), kostentraeger, episode, untersuchung, untKuerzel, untKurzbezeichnung, materialKuerzel, menge, einheit);
    }

}
