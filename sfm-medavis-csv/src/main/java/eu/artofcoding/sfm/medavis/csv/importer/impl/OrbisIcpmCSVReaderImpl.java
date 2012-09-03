/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.OrbisIcpmCSVReader;
import eu.artofcoding.sfm.medavis.csv.importer.bean.OrbisIcpmBean;
import eu.artofcoding.sfm.medavis.csv.importer.helper.ParseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read contents of a Orbis CSV file and put it into database:
 * this class can import any Orbis CSV files as they have the same format.
 * @author rbe
 */
public class OrbisIcpmCSVReaderImpl extends AbstractOrbisIcpmCSVReader<OrbisIcpmBean> implements OrbisIcpmCSVReader {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisIcpmCSVReaderImpl.class);

    /**
     * Constructor.
     */
    public OrbisIcpmCSVReaderImpl() {
        //logger.debug(".init<>");
        clazz = OrbisIcpmBean.class;
    }

    /**
     * @param bean A data bean.
     * @param list One line of the CSV file, containing Strings
     */
    @Override
    protected void parseData(Object bean, String[] list) {
        // Cast argument
        OrbisIcpmBean orbisBean = (OrbisIcpmBean) bean;
        // Fill OrbisBean with data; 0-22
        // Fach-  abteilung;Schlüssel-Nr.;Katalog;Prozedur;Fall  H/N;OP  H/N;OP  Bezug;G;durchgeführt am;Fallnummer;Name;Vorname;geb.;vorst. Aufnahme;Aufnahme;Entlassung;Titel;Anrede;Präfix;Suffix;PID;Fach-  abteilung;Katalog;
        orbisBean.setFachabteilung1(list[0]);
        orbisBean.setSchluesselnr(list[1]);
        orbisBean.setKatalog1(list[2]);
        orbisBean.setProzedur(list[3]);
        orbisBean.setFallhn(list[4]);
        orbisBean.setOphn(list[5]);
        orbisBean.setOpbezug(list[6]);
        orbisBean.setG(list[7]);
        orbisBean.setDurchgefuehrtam(ParseHelper.parseDateTimeHHmm(list[8]));
        orbisBean.setFallnummer(list[9]);
        orbisBean.setName(list[10]);
        orbisBean.setVorname(list[11]);
        orbisBean.setGeburtstag(ParseHelper.parseDate(list[12]));
        orbisBean.setVorstaufnahme(ParseHelper.parseDate(list[13]));
        orbisBean.setAufnahme(ParseHelper.parseDate(list[14]));
        orbisBean.setEntlassung(ParseHelper.parseDate(list[15]));
        orbisBean.setTitel(list[16]);
        orbisBean.setAnrede(list[17]);
        orbisBean.setPraefix(list[18]);
        orbisBean.setSuffix(list[19]);
        orbisBean.setPid(list[20]);
        orbisBean.setFachabteilung(list[21]);
        orbisBean.setKatalog(list[22]);
    }

}
