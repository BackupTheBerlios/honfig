/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.spi.provider;

import org.honfig.Configuration;
import org.honfig.MetaConfig;

/**
 * <p>Title: </p>
 * <p>Description: Configuration provider load user configuration based on the given metaconfigurations.
 *    There can be few types of CP which handles different formats of configuration: ie XML file,
 *    property file, database table or any other custom user format.</p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: ConfigurationProvider.java,v 1.1 2006/03/22 19:07:48 conradh Exp $
 *          Date: 2005-01-14
 *          Time: 18:58:15
 */
public interface ConfigurationProvider {

    String getType();

    Configuration load( MetaConfig metaConfig );
    
}
