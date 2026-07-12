package com.github.Luiztins1.mixs.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "music")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Music extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_song", nullable = false)
    private String nameSong;

    @Column(name = "band", nullable = false)
    private String band;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
}
