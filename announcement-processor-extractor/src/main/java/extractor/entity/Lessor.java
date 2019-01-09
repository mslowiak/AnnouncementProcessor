package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lessor {

    private String name;
    private String lessorType; // to enum
    private String phoneNumber;
    private String email;
}
