package parser.exceptions;

public class PropertyNotValidForGumtreeProviderException extends PropertyNotValidForProviderException {

    public PropertyNotValidForGumtreeProviderException(String propertyName) {
        super("Gumtree", propertyName);
    }
}
