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
 * Data for 'Orbis Leistungen'.
 * Abkürzungen:
 * akt    = aktuelle
 * anf    = anfordernde
 * erbr   = erbringende
 * fa     = Fachabteilung
 * kst    = Kostenstelle
 * krz    = Kürzel
 * lstgrp = Leistungsgruppe
 * @author rbe
 */
public class OrbisLstgBean {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisLstgBean.class);

    private String nachname;
    private String vorname;
    private java.util.Date geburtstag;
    private String geschlecht;
    private String fallnummer;
    private String rechnungsfallnummer;
    private java.util.Date aufnahme;
    private java.util.Date entlassung;
    private String aufenthaltsart;
    private Integer aktkstnr;       // Aktuelle Kostenstelle Nr
    private String aktkst;          // Aktuelle Kostenstelle
    private java.util.Date leistungsdatum;
    private Integer anfkstnr;       // Anfordernde Kostenstelle Nr.
    private String anfkst;          // Anfordernde Kostenstelle
    private String anfabteilung;    // Anfordernde Abteilung
    private String anfstation1;     // Anfordernde Station
    private Integer anzahl;
    private String leistungskrz;    // Leistungskürzel
    private String leistung;
    private Integer erbrkstnr;      // Erbringende Kostenstelle Nr
    private String erbrkst;         // Erbringende Kostenstelle
    private String erbrabteilung;   // Erbringende Abteilung
    private String lstgrpkrz;       // Leistungsgruppenkürzel
    private String lstgrp;          // Leistungsgruppe
    private String pid;
    private String anrede;
    private String name;
    private String titel;
    private String namenspraefix;
    private String namenssuffix;
    private Integer aufenthaltsartid;  // Aufenthaltsart ID
    private String aufenthaltsartkrz;  // Aufenthaltsartkürzel
    private Integer aktkstid;          // Aktuelle Kostenstelle ID
    private Integer anfkstid;          // Anfordernde Kostenstelle ID
    private Integer anffaid;           // Anfordernde Fachabteilung ID
    private String anffa;              // Anfordernde Fachabteilung
    private Integer anfstatid;         // Anfordernde Station ID
    private String anfstation;         // Anfordernde Station
    private Integer leistungsid;
    private Integer erbrkstid;         // Erbringende Kostenstelle ID
    private Integer erbrfaid;          // Erbringende Fachabteilung ID
    private String erbrfa;             // Erbringende Fachabteilung
    private Integer lstgrpid;          // Leistungsgruppen-ID

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getVorname() {
        return vorname;
    }

    public java.util.Date getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setFallnummer(String fallnummer) {
        this.fallnummer = fallnummer;
    }

    public String getFallnummer() {
        return fallnummer;
    }

    public void setRechnungsfallnummer(String rechnungsfallnummer) {
        this.rechnungsfallnummer = rechnungsfallnummer;
    }

    public String getRechnungsfallnummer() {
        return rechnungsfallnummer;
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

    public void setAufenthaltsart(String aufenthaltsart) {
        this.aufenthaltsart = aufenthaltsart;
    }

    public String getAufenthaltsart() {
        return aufenthaltsart;
    }

    public void setAktkstnr(Integer aktkstnr) {
        this.aktkstnr = aktkstnr;
    }

    public Integer getAktkstnr() {
        return aktkstnr;
    }

    public void setAktkst(String aktkst) {
        this.aktkst = aktkst;
    }

    public String getAktkst() {
        return aktkst;
    }

    public void setLeistungsdatum(java.util.Date leistungsdatum) {
        this.leistungsdatum = leistungsdatum;
    }

    public java.util.Date getLeistungsdatum() {
        return leistungsdatum;
    }

    public void setAnfkstnr(Integer anfkstnr) {
        this.anfkstnr = anfkstnr;
    }

    public Integer getAnfkstnr() {
        return anfkstnr;
    }

    public void setAnfkst(String anfkst) {
        this.anfkst = anfkst;
    }

    public String getAnfkst() {
        return anfkst;
    }

    public void setAnfabteilung(String anfabteilung) {
        this.anfabteilung = anfabteilung;
    }

    public String getAnfabteilung() {
        return anfabteilung;
    }

    public void setAnfstation1(String anfstation) {
        this.anfstation = anfstation;
    }

    public String getAnfstation1() {
        return anfstation;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setLeistungskrz(String leistungskrz) {
        this.leistungskrz = leistungskrz;
    }

    public String getLeistungskrz() {
        return leistungskrz;
    }

    public void setLeistung(String leistung) {
        this.leistung = leistung;
    }

    public String getLeistung() {
        return leistung;
    }

    public void setErbrkstnr(Integer erbrkstnr) {
        this.erbrkstnr = erbrkstnr;
    }

    public Integer getErbrkstnr() {
        return erbrkstnr;
    }

    public void setErbrkst(String erbrkst) {
        this.erbrkst = erbrkst;
    }

    public String getErbrkst() {
        return erbrkst;
    }

    public void setErbrabteilung(String erbrabteilung) {
        this.erbrabteilung = erbrabteilung;
    }

    public String getErbrabteilung() {
        return erbrabteilung;
    }

    public void setLstgrpkrz(String lstgrpkrz) {
        this.lstgrpkrz = lstgrpkrz;
    }

    public String getLstgrpkrz() {
        return lstgrpkrz;
    }

    public void setLstgrp(String lstgrp) {
        this.lstgrp = lstgrp;
    }

    public String getLstgrp() {
        return lstgrp;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void setNamenspraefix(String namenspraefix) {
        this.namenspraefix = namenspraefix;
    }

    public String getNamenspraefix() {
        return namenspraefix;
    }

    public void setNamenssuffix(String namenssuffix) {
        this.namenssuffix = namenssuffix;
    }

    public String getNamenssuffix() {
        return namenssuffix;
    }

    public void setAufenthaltsartid(Integer aufenthaltsartid) {
        this.aufenthaltsartid = aufenthaltsartid;
    }

    public Integer getAufenthaltsartid() {
        return aufenthaltsartid;
    }

    public void setAufenthaltsartkrz(String aufenthaltsartkrz) {
        this.aufenthaltsartkrz = aufenthaltsartkrz;
    }

    public String getAufenthaltsartkrz() {
        return aufenthaltsartkrz;
    }

    public void setAktkstid(Integer aktkstid) {
        this.aktkstid = aktkstid;
    }

    public Integer getAktkstid() {
        return aktkstid;
    }

    public void setAnfkstid(Integer anfkstid) {
        this.anfkstid = anfkstid;
    }

    public Integer getAnfkstid() {
        return anfkstid;
    }

    public void setAnffaid(Integer anffaid) {
        this.anffaid = anffaid;
    }

    public Integer getAnffaid() {
        return anffaid;
    }

    public void setAnffa(String anffa) {
        this.anffa = anffa;
    }

    public String getAnffa() {
        return anffa;
    }

    public void setAnfstatid(Integer anfstatid) {
        this.anfstatid = anfstatid;
    }

    public Integer getAnfstatid() {
        return anfstatid;
    }

    public void setAnfstation(String anfstation) {
        this.anfstation = anfstation;
    }

    public String getAnfstation() {
        return anfstation;
    }

    public void setLeistungsid(Integer leistungsid) {
        this.leistungsid = leistungsid;
    }

    public Integer getLeistungsid() {
        return leistungsid;
    }

    public void setErbrkstid(Integer erbrkstid) {
        this.erbrkstid = erbrkstid;
    }

    public Integer getErbrkstid() {
        return erbrkstid;
    }

    public void setErbrfaid(Integer erbrfaid) {
        this.erbrfaid = erbrfaid;
    }

    public Integer getErbrfaid() {
        return erbrfaid;
    }

    public void setErbrfa(String erbrfa) {
        this.erbrfa = erbrfa;
    }

    public String getErbrfa() {
        return erbrfa;
    }

    public void setLstgrpid(Integer lstgrpid) {
        this.lstgrpid = lstgrpid;
    }

    public Integer getLstgrpid() {
        return lstgrpid;
    }

    public String toString() {
        return String.format("%s[nachname=%s,vorname=%s,geburtstag=%s,geschlecht=%s,fallnummer=%s]", this.getClass(), nachname, vorname, geburtstag, geschlecht, fallnummer);
    }

}
