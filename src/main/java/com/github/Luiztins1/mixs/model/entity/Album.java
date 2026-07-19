package com.github.Luiztins1.mixs.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "album")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Album extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year", nullable = false)
    private LocalDate year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artist;


   @ManyToMany(mappedBy = "albumList", fetch = FetchType.LAZY)
   private List<Music> musicList;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "description", nullable = false)
    private String description;


}
