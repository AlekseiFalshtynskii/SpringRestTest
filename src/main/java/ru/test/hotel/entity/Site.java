package ru.test.hotel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
public class Site {
    @Column(name = "LABEL")
    @Size(max = 255, message = "{errors.size_label}")
    private String label;

    @Column(name = "URL")
    @Size(max = 255, message = "{errors.size_url}")
    private String url;
}
