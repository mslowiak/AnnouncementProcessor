package api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LOCATIONS")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "BUILDING_NUMBER")
    private String buildingNumber;

    @Column(name = "FLAT_NUMBER")
    private String flatNumber;

    @Column(name = "DISTRICT")
    private String district;

    @OneToOne(mappedBy = "location")
    private Announcement announcement;
}
