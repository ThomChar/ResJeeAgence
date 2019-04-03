package connexionBD;

/**
 * L'exception AgencyException est levee lorsqu'une transaction est inadequate.
 */
public final class AgencyException extends Exception
{
    private static final long serialVersionUID = 1L;

    public AgencyException(String message)
    {
        super(message);
    }
}