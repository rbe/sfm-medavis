/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis145SQLImport;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis145Bean;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author rbe
 */
public class Medavis145SQLImportImpl implements Medavis145SQLImport<Medavis145Bean> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis145SQLImportImpl.class);

    /**
     * DataSource.
     */
    private DataSource dataSource;

    /**
     * Spring's JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO medavis145"
            + " (kategorie, kuerzel, bezeichnung, pzn, einheit, preis, refnr)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

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
    public void insert(Medavis145Bean medavisBean) {
        if (null != dataSource && null != medavisBean) {
            if (null != jdbcTemplate) {
                try {
                    jdbcTemplate.update(sql,
                            medavisBean.getKategorie(),
                            medavisBean.getKuerzel(),
                            medavisBean.getBezeichnung(),
                            medavisBean.getPzn(),
                            medavisBean.getEinheit(),
                            medavisBean.getPreis(),
                            medavisBean.getRefnr());
                } catch (org.springframework.jdbc.BadSqlGrammarException e) {
                    logger.error(String.format("Could not insert data: %s", e.getMessage()));
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    // TODO Ignore or write line into error-file?
                    logger.error(String.format("Cannot insert data: %s: %s", medavisBean, e.getMessage()));
                }
            } else {
                logger.error("Sorry, no jdbcTemplate!");
            }
        } else {
            logger.error(String.format("%sSorry, no dataSource(=%s) or medavisBean(=%s)", this, dataSource, medavisBean));
        }
    }

    /**
     * Camel Processor.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        // Get payload from incoming message body
        List<Medavis145Bean> payload = (List<Medavis145Bean>) exchange.getIn().getBody();
        // Process every parsed line from CSV file
        for (Medavis145Bean medavisBean : payload) {
            insert(medavisBean);
        }
    }

}
