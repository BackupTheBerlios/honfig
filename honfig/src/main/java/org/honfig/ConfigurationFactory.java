package org.honfig;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: ConfigurationFactory.java,v 1.1 2006/03/22 14:48:10 conradh Exp $
 *          Date: 2006-03-22
 *          Time: 10:04:05
 */
public class ConfigurationFactory {

    public static Configuration getConfiguration( final String cfgName ) throws Exception {
        ConfigurationManager cm = new ConfigurationManager();
        return cm.getConfiguration( cfgName );
    }

    public static Configuration getConfiguration( ) throws Exception {
        ConfigurationManager cm = new ConfigurationManager();
        return cm.getConfiguration( );
    }
}
