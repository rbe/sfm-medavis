/*
 * sfm-medavis
 * sfm-medavis-csv
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.08.12 12:05
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.OrbisLstgSQLImport;
import eu.artofcoding.sfm.medavis.csv.importer.bean.OrbisLstgBean;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author rbe
 */
public class OrbisLstgSQLImportImpl implements OrbisLstgSQLImport<OrbisLstgBean> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(OrbisLstgSQLImportImpl.class);

    /**
     * DataSource.
     */
    private DataSource dataSource;

    /**
     * Spring's JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO orbislstg"
            + " (nachname, vorname, geburtstag, geschlecht, fallnummer, rechnungsfallnummer, aufnahme, entlassung, aufenthaltsart, aktkstnr, aktkst, leistungsdatum, anfkstnr, anfkst, anfabteilung, anfstation1, anzahl, leistungskrz, leistung, erbrkstnr, erbrkst, erbrabteilung, lstgrpkrz, lstgrp, pid, anrede, name, titel, namenspraefix, namenssuffix, aufenthaltsartid, aufenthaltsartkrz, aktkstid, anfkstid, anffaid, anffa, anfstatid, anfstation, leistungsid, erbrkstid, erbrfaid, erbrfa, lstgrpid)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Setter for dataSource.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        logger.debug(String.format("Got dataSource=%s and jdbcTemplate=%s", dataSource, jdbcTemplate));
    }

    /**
     *
     */
    @Override
    public void insert(OrbisLstgBean orbisBean) {
        if (null != dataSource && null != orbisBean) {
            if (null != jdbcTemplate) {
                try {
                    jdbcTemplate.update(sql,
                            orbisBean.getNachname(),
                            orbisBean.getVorname(),
                            orbisBean.getGeburtstag(),
                            orbisBean.getGeschlecht(),
                            orbisBean.getFallnummer(),
                            orbisBean.getRechnungsfallnummer(),
                            orbisBean.getAufnahme(),
                            orbisBean.getEntlassung(),
                            orbisBean.getAufenthaltsart(),
                            orbisBean.getAktkstnr(),
                            orbisBean.getAktkst(),
                            orbisBean.getLeistungsdatum(),
                            orbisBean.getAnfkstnr(),
                            orbisBean.getAnfkst(),
                            orbisBean.getAnfabteilung(),
                            orbisBean.getAnfstation1(),
                            orbisBean.getAnzahl(),
                            orbisBean.getLeistungskrz(),
                            orbisBean.getLeistung(),
                            orbisBean.getErbrkstnr(),
                            orbisBean.getErbrkst(),
                            orbisBean.getErbrabteilung(),
                            orbisBean.getLstgrpkrz(),
                            orbisBean.getLstgrp(),
                            orbisBean.getPid(),
                            orbisBean.getAnrede(),
                            orbisBean.getName(),
                            orbisBean.getTitel(),
                            orbisBean.getNamenspraefix(),
                            orbisBean.getNamenssuffix(),
                            orbisBean.getAufenthaltsartid(),
                            orbisBean.getAufenthaltsartkrz(),
                            orbisBean.getAktkstid(),
                            orbisBean.getAnfkstid(),
                            orbisBean.getAnffaid(),
                            orbisBean.getAnffa(),
                            orbisBean.getAnfstatid(),
                            orbisBean.getAnfstation(),
                            orbisBean.getLeistungsid(),
                            orbisBean.getErbrkstid(),
                            orbisBean.getErbrfaid(),
                            orbisBean.getErbrfa(),
                            orbisBean.getLstgrpid());
                } catch (org.springframework.jdbc.BadSqlGrammarException e) {
                    logger.error(String.format("Could not insert data: %s", e.getMessage()));
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    // TODO Ignore or write line into error-file?
                    logger.error(String.format("Cannot insert data: %s: %s", orbisBean, e.getMessage()));
                }
            } else {
                logger.error("Sorry, no jdbcTemplate!");
            }
        } else {
            logger.error(String.format("Sorry, no dataSource(=%s) or orbisBean(=%s)", dataSource, orbisBean));
        }
    }

    /**
     * Camel Processor.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        // Get payload from incoming message body
        List<OrbisLstgBean> payload = (List<OrbisLstgBean>) exchange.getIn().getBody();
        // Process every parsed line from CSV file
        for (OrbisLstgBean orbisBean : payload) {
            insert(orbisBean);
        }
    }

}
