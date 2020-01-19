package extractor.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lessor {

    private String name;
    private String lessorType; // todo to enum
    private String phoneNumber;
    private String email;
}
