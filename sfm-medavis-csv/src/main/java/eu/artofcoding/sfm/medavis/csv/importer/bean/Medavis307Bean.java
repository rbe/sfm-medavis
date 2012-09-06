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
public class Medavis307Bean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis307Bean.class);

    private java.util.Date untersuchungDatum;
    private String ueberweiser;
    private String nachname;
    private String vorname;
    private java.util.Date geburtstag;
    private String untersuchung;
    private String episode;
    private String ops;

    public java.util.Date getUntersuchungDatum() {
        return untersuchungDatum;
    }

    public void setUntersuchungDatum(java.util.Date untersuchungDatum) {
        this.untersuchungDatum = untersuchungDatum;
    }

    public String getUeberweiser() {
        return ueberweiser;
    }

    public void setUeberweiser(String ueberweiser) {
        this.ueberweiser = ueberweiser;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public java.util.Date getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getUntersuchung() {
        return untersuchung;
    }

    public void setUntersuchung(String untersuchung) {
        this.untersuchung = untersuchung;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String toString() {
        return String.format("%s[untersuchung=%s,ueberweiser=%s,untersuchung=%s,episode=%s,ops=%s]", this.getClass(), untersuchung, ueberweiser, untersuchung, episode, ops);
    }

}
