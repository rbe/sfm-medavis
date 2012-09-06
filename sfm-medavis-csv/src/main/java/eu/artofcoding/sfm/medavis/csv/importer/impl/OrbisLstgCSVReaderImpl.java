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
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.OrbisLstgCSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.OrbisLstgBean;
import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Orbis CSV file and put it into database:
 * this class can import any Orbis CSV files as they have the same format.
 * @author rbe
 */
public class OrbisLstgCSVReaderImpl extends AbstractOrbisLstgCSVReader<OrbisLstgBean> implements OrbisLstgCSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisLstgCSVReaderImpl.class);

    /**
     * Constructor.
     */
    public OrbisLstgCSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = OrbisLstgBean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        OrbisLstgBean orbisBean = (OrbisLstgBean) bean;
        // Fill OrbisBean with data; 0-42
        // Nachname;Vorname;Geburtsdatum;M/W;Fallnummer;Rechnungsfallnummer;Aufnahme;Entlassung;Aufenthaltsart;akt. Kst.-Nr.;aktuelle Kostenstelle;Leistungsdatum;anf. Kst.-Nr.;anfordernde Kostenstelle;anf. Abteilung;anf. Station;Anzahl;Leistungskürzel;Leistung;erbr. Kst.-Nr.;erbringende Kostenstelle;erbr. Abteilung;Leistungsgruppenkürzel;Leistungsgruppe;PID;Anrede;Name;Titel;Namenspräfix;Namenssuffix;Aufenthaltsart-ID;Aufenthaltsartkürzel;akt. Kst.-ID;anf. Kst.-ID;anf. Fa-ID;anfordernde Fachabteilung;anf. Stat-ID;anfordernde Station;Leistungs-ID;erbr. Kst.-ID;erbr. Fa-ID;erbringende Fachabteilung;Leistungsgruppen-ID;
        orbisBean.setNachname(list[0]);
        orbisBean.setVorname(list[1]);
        orbisBean.setGeburtstag(ParseHelper.parseDate(list[2]));
        if (null == orbisBean.getGeburtstag()) {
            orbisBean.setGeburtstag(ParseHelper.CAL_1_1_1900.getTime());
        }
        orbisBean.setGeschlecht(list[3]);
        orbisBean.setFallnummer(list[4]);
        orbisBean.setRechnungsfallnummer(list[5]);
        orbisBean.setAufnahme(ParseHelper.parseDate(list[6]));
        orbisBean.setEntlassung(ParseHelper.parseDate(list[7]));
        orbisBean.setAufenthaltsart(list[8]);
        orbisBean.setAktkstnr(ParseHelper.parseInteger(list[9]));
        orbisBean.setAktkst(list[10]);
        orbisBean.setLeistungsdatum(ParseHelper.parseDate(list[11]));
        orbisBean.setAnfkstnr(ParseHelper.parseInteger(list[12]));
        orbisBean.setAnfkst(list[13]);
        orbisBean.setAnfabteilung(list[14]);
        orbisBean.setAnfstation1(list[15]);
        orbisBean.setAnzahl(ParseHelper.parseInteger(list[16]));
        orbisBean.setLeistungskrz(list[17]);
        orbisBean.setLeistung(list[18]);
        orbisBean.setErbrkstnr(ParseHelper.parseInteger(list[19]));
        orbisBean.setErbrkst(list[20]);
        orbisBean.setErbrabteilung(list[21]);
        orbisBean.setLstgrpkrz(list[22]);
        orbisBean.setLstgrp(list[23]);
        orbisBean.setPid(list[24]);
        orbisBean.setAnrede(list[25]);
        orbisBean.setName(list[26]);
        orbisBean.setTitel(list[27]);
        orbisBean.setNamenspraefix(list[28]);
        orbisBean.setNamenssuffix(list[29]);
        orbisBean.setAufenthaltsartid(ParseHelper.parseInteger(list[30]));
        orbisBean.setAufenthaltsartkrz(list[31]);
        orbisBean.setAktkstid(ParseHelper.parseInteger(list[32]));
        orbisBean.setAnfkstid(ParseHelper.parseInteger(list[33]));
        orbisBean.setAnffaid(ParseHelper.parseInteger(list[34]));
        orbisBean.setAnffa(list[35]);
        orbisBean.setAnfstatid(ParseHelper.parseInteger(list[36]));
        orbisBean.setAnfstation(list[37]);
        orbisBean.setLeistungsid(ParseHelper.parseInteger(list[38]));
        orbisBean.setErbrkstid(ParseHelper.parseInteger(list[39]));
        orbisBean.setErbrfaid(ParseHelper.parseInteger(list[40]));
        orbisBean.setErbrfa(list[41]);
        orbisBean.setLstgrpid(ParseHelper.parseInteger(list[42]));
    }

}
