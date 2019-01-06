package api.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "ANNOUNCEMENTS")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "URL")
    private String url;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CURRENCY")
    private String currency;

    @OneToOne
    @JoinColumn(name = "PRICE_ID")
    private PriceOffer priceOffer;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToOne
    @JoinColumn(name = "PROPERTY_DATA_ID")
    private PropertyData propertyData;

    @OneToOne
    @JoinColumn(name = "LESSOR_ID")
    private Lessor lessor;

    @OneToMany(mappedBy = "announcement")
    private List<AdditionalCosts> additionalCosts;
}
