package api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PROPERTY_DATA")
public class PropertyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PROPERTY_TYPE")
    private String propertyType;

    @Column(name = "AREA")
    private Double area;

    @Column(name = "IS_FOR_SMOKERS")
    private Boolean isSmokingAllowed;

    @Column(name = "IS_FOR_PETS")
    private Boolean isPetFriendly;

    @Column(name = "ROOM_NUMBER")
    private Integer roomNumber;

    @Column(name = "BATHROOM_NUMBER")
    private Integer bathroomNumber;

    @Column(name = "PARKING_AVAILABILITY")
    private String parkingAvailability;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "FURNISHING")
    private String furnishing;

    @JsonBackReference
    @OneToOne(mappedBy = "propertyData")
    private Announcement announcement;
}
