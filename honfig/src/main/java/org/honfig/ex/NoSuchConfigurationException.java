/*
 * Copyright (c) 2005 www.honfig.org. All Rights Reserved.
 */
package org.honfig.ex;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: NoSuchConfigurationException.java,v 1.2 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-11-08
 *          Time: 22:01:39
 */
public class NoSuchConfigurationException extends HonfigException {
    private final static long serialVersionUID = 2;

    public NoSuchConfigurationException(String configName) {
        super("Configuration with name " + configName +
                " could not be found! Check honfig.xml file", null, 80001);
    }

}
