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
 * Data for 'Orbis ICPM'.
 * @author rbe
 */
public class OrbisIcpmBean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisIcpmBean.class);

    private String fachabteilung1;
    private String schluesselnr;
    private String katalog1;
    private String prozedur;
    private String fallhn;
    private String ophn;
    private String opbezug;
    private String g;
    private java.util.Date durchgefuehrtam;
    private String fallnummer;
    private String name;
    private String vorname;
    private java.util.Date geburtstag;
    private java.util.Date vorstaufnahme;
    private java.util.Date aufnahme;
    private java.util.Date entlassung;
    private String titel;
    private String anrede;
    private String praefix;
    private String suffix;
    private String pid;
    private String fachabteilung;
    private String katalog;

    public void setFachabteilung1(String fachabteilung1) {
        this.fachabteilung1 = fachabteilung1;
    }

    public String getFachabteilung1() {
        return fachabteilung1;
    }

    public void setSchluesselnr(String schluesselnr) {
        this.schluesselnr = schluesselnr;
    }

    public String getSchluesselnr() {
        return schluesselnr;
    }

    public void setKatalog1(String katalog1) {
        this.katalog1 = katalog1;
    }

    public String getKatalog1() {
        return katalog1;
    }

    public void setProzedur(String prozedur) {
        this.prozedur = prozedur;
    }

    public String getProzedur() {
        return prozedur;
    }

    public void setFallhn(String fallhn) {
        this.fallhn = fallhn;
    }

    public String getFallhn() {
        return fallhn;
    }

    public void setOphn(String ophn) {
        this.ophn = ophn;
    }

    public String getOphn() {
        return ophn;
    }

    public void setOpbezug(String opbezug) {
        this.opbezug = opbezug;
    }

    public String getOpbezug() {
        return opbezug;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getG() {
        return g;
    }

    public void setDurchgefuehrtam(java.util.Date durchgefuehrtam) {
        this.durchgefuehrtam = durchgefuehrtam;
    }

    public java.util.Date getDurchgefuehrtam() {
        return durchgefuehrtam;
    }

    public void setFallnummer(String fallnummer) {
        this.fallnummer = fallnummer;
    }

    public String getFallnummer() {
        return fallnummer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setGeburtstag(java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public java.util.Date getGeburtstag() {
        return geburtstag;
    }

    public void setVorstaufnahme(java.util.Date vorstaufnahme) {
        this.vorstaufnahme = vorstaufnahme;
    }

    public java.util.Date getVorstaufnahme() {
        return vorstaufnahme;
    }

    public void setAufnahme(java.util.Date aufnahme) {
        this.aufnahme = aufnahme;
    }

    public java.util.Date getAufnahme() {
        return aufnahme;
    }

    public void setEntlassung(java.util.Date entlassung) {
        this.entlassung = entlassung;
    }

    public java.util.Date getEntlassung() {
        return entlassung;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setPraefix(String praefix) {
        this.praefix = praefix;
    }

    public String getPraefix() {
        return praefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setFachabteilung(String fachabteilung) {
        this.fachabteilung = fachabteilung;
    }

    public String getFachabteilung() {
        return fachabteilung;
    }

    public void setKatalog(String katalog) {
        this.katalog = katalog;
    }

    public String getKatalog() {
        return katalog;
    }

    public String toString() {
        return String.format("%s[name=%s,vorname=%s,geburtstag=%s,fallnummer=%s]", this.getClass(), name, vorname, geburtstag, fallnummer);
    }

}
