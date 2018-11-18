package parser.exceptions;

class PropertyNotValidForProviderException extends RuntimeException {

    PropertyNotValidForProviderException(String providerName, String propertyName) {
        super("Property named: '" + propertyName + "' is not valid for provider: '" + providerName + "'");
    }
}
