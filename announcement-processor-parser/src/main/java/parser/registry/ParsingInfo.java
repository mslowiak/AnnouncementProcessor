package parser.registry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PARSED_INFO_REGISTRY")
public class ParsingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Integer id;

    @Column(name = "URL")
    String url;

    @Column(name = "PAGE_HASH")
    String pageHash;

    @Column(name = "DATE")
    LocalDate date;

    @Column(name = "COUNTER_PER_DATE")
    Integer counterPerDate;
}
