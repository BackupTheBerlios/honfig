/*
 * Copyright (c) 2006 Your Corporation. All Rights Reserved.
 */
package org.honfig.ex;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: DefaultConfigurationNotFoundException.java,v 1.1 2006/03/22 14:43:44 conradh Exp $
 *          Date: 2005-11-08
 *          Time: 22:01:39
 */
public class DefaultConfigurationNotFoundException extends HonfigException {
    private final static long serialVersionUID = 2;

    public DefaultConfigurationNotFoundException( ) {
        super("Default configuration not found! Check honfig.xml file", null, 80002 );
    }

}
