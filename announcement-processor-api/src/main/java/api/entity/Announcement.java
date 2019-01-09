package api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "DESCRIPTION", length = 15000)
    private String description;

    @Column(name = "CURRENCY")
    private String currency;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRICE_ID")
    private PriceOffer priceOffer;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROPERTY_DATA_ID")
    private PropertyData propertyData;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LESSOR_ID")
    private Lessor lessor;

    @JsonManagedReference
    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<AdditionalCosts> additionalCosts;
}
