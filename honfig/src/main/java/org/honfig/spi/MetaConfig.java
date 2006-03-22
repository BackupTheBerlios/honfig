/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package org.honfig.spi;

import org.honfig.spi.provider.ConfigurationProvider;
import org.honfig.spi.ia.IdentityAlgorithm;

/**
 * <p>Title: </p>
 * <p>Description: This interface represents metaconfiguration.
 *      Each configuration is defined in following:
 *      <ul>
 *  
 *
 *
 *      </ul>
 * </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: MetaConfig.java,v 1.1 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-01-03
 *          Time: 23:09:54
 */
public interface MetaConfig {


    String getName();

    void setName(String name);

    ConfigurationProvider getProvider();

    void setProvider(String type);

    String getDescription();

    void setDescription(String description);

    IdentityAlgorithm getAlgorithm();

    void setAlgorithm(String algorithm);
}
