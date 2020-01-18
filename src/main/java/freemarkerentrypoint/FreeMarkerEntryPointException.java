package freemarkerentrypoint;

/**
 * A {@code FreeMarkerEntryPointException} object represents an exception
 * thrown by the methods defined by the {@link FreeMarkerEntryPoint} interface
 * whenever an IO error or a FreeMarker-based error occurs.
 * 
 * @author Christophe Deleray
 */
public class FreeMarkerEntryPointException extends RuntimeException {
  private static final long serialVersionUID = 4396240703314737350L;

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with no detail message nor root cause.
   */
  public FreeMarkerEntryPointException() {
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given detail message.
   * 
   * @param message the detail message of this exception
   */
  public FreeMarkerEntryPointException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given root cause.
   * 
   * @param cause the root cause of this exception
   */
  public FreeMarkerEntryPointException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new instance of the {@code FreeMarkerEntryPointException} class
   * initialized with the given detail message and root cause.
   * 
   * @param message the detail message of this exception
   * @param cause the root cause of this exception
   */
  public FreeMarkerEntryPointException(String message, Throwable cause) {
    super(message, cause);
  }
}
