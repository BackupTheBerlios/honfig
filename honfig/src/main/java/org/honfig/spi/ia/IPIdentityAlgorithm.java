package org.honfig.spi.ia;

import org.honfig.spi.ia.IdentityAlgorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: IPIdentityAlgorithm.java,v 1.1 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2004-12-29
 *          Time: 01:24:29
 */
public class IPIdentityAlgorithm implements IdentityAlgorithm {

    public Object getToken() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
