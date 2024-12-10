package one.tranic.vico.exception;

public class DependencyNotFoundException extends RuntimeException {
    public DependencyNotFoundException() {
        super();
    }

    public DependencyNotFoundException(String message) {
        super(message);
    }
}
