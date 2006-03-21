package org.honfig.impl;

import org.honfig.IdentityAlgorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="hocon@homo-developerus.org">HoCon</a>
 * @version $Id: HostNameIdentityAlgorithm.java,v 1.1 2006/03/21 20:32:53 conradh Exp $
 *          Date: 2004-12-29
 *          Time: 01:24:29
 */
public class HostNameIdentityAlgorithm implements IdentityAlgorithm {

    public Object getToken() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
}
