package parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class Announcement {
    private String title;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private String lessor;
    private String propertyType;
    private Double flatArea;
    private Integer roomAmount;
    private Integer bathAmount;
    private String parkingAvailability;
    private Boolean isSmokingAllowed;
    private Boolean isPetFriendly;
    private String lessorName;
    private BigDecimal additionalRentCost;
    private String level;
    private String furnishing;
    private String description;
    private String provider;
    private String url;
    private String phoneNumber;

    private Announcement() {
    }

    private Announcement(AnnouncementBuilder builder) {
        this.title = builder.title;
        this.price = builder.price;
        this.creationDate = builder.creationDate;
        this.lessor = builder.lessor;
        this.propertyType = builder.propertyType;
        this.flatArea = builder.flatArea;
        this.roomAmount = builder.roomAmount;
        this.bathAmount = builder.bathAmount;
        this.parkingAvailability = builder.parkingAvailability;
        this.isSmokingAllowed = builder.isSmokingAllowed;
        this.isPetFriendly = builder.isPetFriendly;
        this.lessorName = builder.lessorName;
        this.additionalRentCost = builder.additionalRentCost;
        this.level = builder.level;
        this.furnishing = builder.furnishing;
        this.description = builder.description;
        this.provider = builder.provider;
        this.url = builder.url;
        this.phoneNumber = builder.phoneNumber;
    }

    public static AnnouncementBuilder builder() {
        return new AnnouncementBuilder();
    }

    public static final class AnnouncementBuilder {
        private String title;
        private BigDecimal price;
        private LocalDateTime creationDate;
        private String lessor;
        private String propertyType;
        private Double flatArea;
        private Integer roomAmount;
        private Integer bathAmount;
        private String parkingAvailability;
        private Boolean isSmokingAllowed;
        private Boolean isPetFriendly;
        private String lessorName;
        private BigDecimal additionalRentCost;
        private String level;
        private String furnishing;
        private String description;
        private String provider;
        private String url;
        private String phoneNumber;

        public AnnouncementBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AnnouncementBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public AnnouncementBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AnnouncementBuilder lessor(String lessor) {
            this.lessor = lessor;
            return this;
        }

        public AnnouncementBuilder propertyType(String propertyType) {
            this.propertyType = propertyType;
            return this;
        }

        public AnnouncementBuilder flatArea(Double flatArea) {
            this.flatArea = flatArea;
            return this;
        }

        public AnnouncementBuilder roomAmount(Integer roomAmount) {
            this.roomAmount = roomAmount;
            return this;
        }

        public AnnouncementBuilder bathAmount(Integer bathAmount) {
            this.bathAmount = bathAmount;
            return this;
        }

        public AnnouncementBuilder parkingAvailability(String parkingAvailability) {
            this.parkingAvailability = parkingAvailability;
            return this;
        }

        public AnnouncementBuilder isSmokingAllowed(Boolean isSmokingAllowed) {
            this.isSmokingAllowed = isSmokingAllowed;
            return this;
        }

        public AnnouncementBuilder isPetFriendly(Boolean isPetFriendly) {
            this.isPetFriendly = isPetFriendly;
            return this;
        }

        public AnnouncementBuilder lessorName(String lessorName) {
            this.lessorName = lessorName;
            return this;
        }

        public AnnouncementBuilder additionalRentCost(BigDecimal additionalRentCost) {
            this.additionalRentCost = additionalRentCost;
            return this;
        }

        public AnnouncementBuilder level(String level) {
            this.level = level;
            return this;
        }

        public AnnouncementBuilder furnishing(String furnishing) {
            this.furnishing = furnishing;
            return this;
        }

        public AnnouncementBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AnnouncementBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public AnnouncementBuilder url(String url) {
            this.url = url;
            return this;
        }

        public AnnouncementBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Announcement build() {
            return new Announcement(this);
        }
    }
}
