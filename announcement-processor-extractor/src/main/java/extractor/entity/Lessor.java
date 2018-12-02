package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lessor {

    String name;
    String lessorType; // to enum
    String phoneNumber;
    String email;
}
