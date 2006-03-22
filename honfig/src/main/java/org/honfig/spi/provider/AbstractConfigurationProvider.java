/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.spi.provider;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: AbstractConfigurationProvider.java,v 1.2 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-01-14
 *          Time: 18:58:48
 */
public abstract class AbstractConfigurationProvider implements ConfigurationProvider {


    public AbstractConfigurationProvider() {

    }


    public String getType() {
        return getClass().getName();
    }

}
