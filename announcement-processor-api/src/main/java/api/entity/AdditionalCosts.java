package api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ADDITIONAL_COSTS")
public class AdditionalCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @Column(name = "COST_NAME")
    private String costName;

    @Column(name = "COST_PRICE")
    private BigDecimal costPrice;
}
