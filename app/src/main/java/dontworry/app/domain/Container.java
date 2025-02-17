package dontworry.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name = "Containers")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Container {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long containerId;

    @Column(nullable = false)
    private String ownerCode;

    @Column(nullable = false)
    private String serialCode;

    @Column(nullable = false)
    private String checkDigit;

    @Column(nullable = false)
    private String disembarkationShipName;

    @Column(nullable = false)
    private Integer inspectionResult;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    public Container() {
    }

    public Container(String ownerCode, String serialCode, String checkDigit, String disembarkationShipName, Integer inspectionResult) {
        this.ownerCode = ownerCode;
        this.serialCode = serialCode;
        this.checkDigit = checkDigit;
        this.disembarkationShipName = disembarkationShipName;
        this.inspectionResult = inspectionResult;
    }
}
