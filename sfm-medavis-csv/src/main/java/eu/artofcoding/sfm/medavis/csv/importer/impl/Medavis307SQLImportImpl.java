/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.csv.importer.impl;

import eu.artofcoding.sfm.medavis.csv.importer.api.Medavis307SQLImport;
import eu.artofcoding.sfm.medavis.csv.importer.bean.Medavis307Bean;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author rbe
 */
public class Medavis307SQLImportImpl implements Medavis307SQLImport<Medavis307Bean> {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Medavis307SQLImportImpl.class);

    /**
     * DataSource.
     */
    private DataSource dataSource;

    /**
     * Spring's JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO medavis307"
            + " (untdatum, ueberweiser, nachname, vorname, gebdatum, untersuchung, episode, ops)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
    public void insert(Medavis307Bean medavisBean) {
        if (null != dataSource && null != medavisBean) {
            if (null != jdbcTemplate) {
                try {
                    jdbcTemplate.update(sql,
                            medavisBean.getUntersuchungDatum(),
                            medavisBean.getUeberweiser(),
                            medavisBean.getNachname(),
                            medavisBean.getVorname(),
                            medavisBean.getGeburtstag(),
                            medavisBean.getUntersuchung(),
                            medavisBean.getEpisode(),
                            medavisBean.getOps());
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
            logger.error(String.format("Sorry, no dataSource(=%s) or medavisBean(=%s)", dataSource, medavisBean));
        }
    }

    /**
     * Camel Processor.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        // Get payload from incoming message body
        List<Medavis307Bean> payload = (List<Medavis307Bean>) exchange.getIn().getBody();
        // Process every parsed line from CSV file
        for (Medavis307Bean medavisBean : payload) {
            insert(medavisBean);
        }
    }

}
