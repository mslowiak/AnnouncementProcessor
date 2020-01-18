package extractor.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Utilities {

    GAS("gaz"),
    ELECTRICITY("prąd"),
    ADMINISTRATION_FEE("czynsz"),
    HEATING("ogrzewanie"),
    CENTRAL_HEATING("CO"),
    GARBAGE_COLLECTION("śmieci"),
    MEDIA("media"),
    INTERNET("internet"),
    WATER("woda");

    private final String utilityName;
}
