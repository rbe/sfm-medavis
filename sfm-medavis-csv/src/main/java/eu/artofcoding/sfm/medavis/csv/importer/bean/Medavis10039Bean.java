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
 * Data for Medavis #10039.
 * @author rbe
 */
public class Medavis10039Bean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis10039Bean.class);

    private String kuerzel;
    private String kurzbezeichnung;
    private String bezeichnung;
    private String art;
    private String code;
    private String version;

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getKurzbezeichnung() {
        return kurzbezeichnung;
    }

    public void setKurzbezeichnung(String kurzbezeichnung) {
        this.kurzbezeichnung = kurzbezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String toString() {
        return String.format("%s[kuerzel=%s,kurzbezeichnung=%s,bezeichnung=%s,art=%s,code=%s,version=%s]", this.getClass(), kuerzel, kurzbezeichnung, bezeichnung, art, code, version);
    }

}
