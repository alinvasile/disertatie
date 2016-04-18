package com.alin.disertatie.bileteonline.dao.oracle;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * <p>
 *   Repository interface handling the Parking data.
 * </p>
 *
 * @author Alin Vasile
 * @version 1.0
 */
public abstract class AbstractOracleRepository extends SimpleJdbcDaoSupport {

    protected TransactionTemplate txTemplate;

    /**
     * Sets the PlatformTransactionManager instance to be used by this repository.
     * @param txManager
     * @throws IllegalArgumentException if the txManager parameter is null
     */
    public void setTransactionManager(PlatformTransactionManager txManager) {
        this.txTemplate = new TransactionTemplate(txManager);
        this.txTemplate.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
    }

}
