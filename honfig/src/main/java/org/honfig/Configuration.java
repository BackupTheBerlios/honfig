package org.honfig;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: Configuration.java,v 1.1 2006/03/21 20:32:54 conradh Exp $
 *          Date: 2004-12-28
 *          Time: 22:22:44
 */
public interface Configuration {
    public Object get(String key);
}