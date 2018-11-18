package ru.test.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "HOTEL")
@Data
public class Hotel {
    @Id
    @Column(name = "ID", length = 12, nullable = false, unique = true)
    @NotNull(message = "{errors.required_id}")
    @Size(max = 12, message = "{errors.size_id}")
    private String id;

    @Column(name = "NAME", nullable = false)
    @NotNull(message = "{errors.required_name}")
    @Size(max = 255, message = "{errors.size_name}")
    private String name;

    @Column(name = "CATEGORY", length = 12)
    @Size(max = 12, message = "{errors.size_catid}")
    private String catid;

    @Column(name = "COORDINATES")
    @Embedded
    private Point point;

    @Column(name = "ADDRESS", nullable = false)
    @NotNull(message = "{errors.required_address}")
    @Size(max = 255, message = "{errors.size_address}")
    private String addr;

    @Column(name = "IMAGE")
    @Size(max = 255, message = "{errors.size_img}")
    private String img;

    @Column(name = "SITE")
    @Embedded
    private Site site;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "HOTEL_SERVICE",
            joinColumns = @JoinColumn(name = "HOTEL_ID")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "SERVICE")
    private Set<HotelService> services;

    @Version
    @Column(name = "VERSION")
    private long version;

    public enum HotelService {
        restaurant,
        transfer,
        bar,
        fitness,
        beach
    }
}
