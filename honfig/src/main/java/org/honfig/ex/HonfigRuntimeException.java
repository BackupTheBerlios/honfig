package org.honfig.ex;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author <a href="conradh@users.berlios.de">Conradh</a>
 * @version $Id: HonfigRuntimeException.java,v 1.2 2006/03/22 19:30:52 conradh Exp $
 *          Date: 2005-11-08
 *          Time: 21:53:43
 */
public class HonfigRuntimeException extends RuntimeException {
    private final static long serialVersionUID = 1;

    private final int errNo;


    public HonfigRuntimeException(String message, Throwable cause, int errNo) {
        super(message, cause);
        this.errNo = errNo;
    }


    public String getMessage() {
        return "HONFIG-" + this.errNo + ": " + super.getMessage();
    }
}
