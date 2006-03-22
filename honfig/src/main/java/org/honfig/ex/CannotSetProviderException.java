package org.honfig.ex;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: CannotSetProviderException.java,v 1.2 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-11-08
 *          Time: 22:01:39
 */
public class CannotSetProviderException extends HonfigRuntimeException {
    private final static long serialVersionUID = 4;

    public CannotSetProviderException(final String cp, final Exception ex) {
        super("Cannot instantiate Configuration Provider " + cp, ex, 80101);
    }

}
