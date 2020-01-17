package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lessor {

    private String name;
    private String lessorType; // todo to enum
    private String phoneNumber;
    private String email;
}
