package api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LESSORS")
public class Lessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LESSOR_TYPE")
    private String lessorType;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(mappedBy = "lessor")
    private Announcement announcement;
}
