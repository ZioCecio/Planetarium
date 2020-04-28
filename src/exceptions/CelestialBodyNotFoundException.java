package exceptions;

public class CelestialBodyNotFoundException extends Exception {
    public CelestialBodyNotFoundException() {}

    public CelestialBodyNotFoundException(String message) {
        super(message);
    }
}