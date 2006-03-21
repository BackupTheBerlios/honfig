package org.honfig.impl;

import org.honfig.Configuration;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: PropertiesConfigurationImpl.java,v 1.1 2006/03/21 20:32:53 conradh Exp $
 *          Date: 2004-12-28
 *          Time: 22:23:15
 */
public class PropertiesConfigurationImpl implements Configuration {
    private static final Logger log = Logger.getLogger(PropertiesConfigurationImpl.class.getName());
    private Properties props;

    public PropertiesConfigurationImpl(final Properties props) {
        this.props = props;
    }

    public Object get(String key) {
        return this.props.getProperty(key);
    }
}
