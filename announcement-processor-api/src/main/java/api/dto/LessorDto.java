package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessorDto {

    private String name;
    private String lessorType; // to enum
    private String phoneNumber;
    private String email;
}
