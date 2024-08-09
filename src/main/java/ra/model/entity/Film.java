package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Film {
    @Id
//    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "film_name", unique = true)
    private String name;
//    @Column(name = "film_description")
    private String description;
//    @Column(name = "film_image")
    private String image;
//    @Column(name = "trailer_url")
    private String trailerUrl;
//    @Column(name = "view_count")
    private Long viewCount;
//    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
//    @Column(name = "director")
    private String director;
//    @Column(name = "main_actor_name")
    private String mainActorName;
//    @Column(name = "main_actress_name")
    private String mainActressName;
//    @Column(name = "language")
//    private String language;
////    @Column(name = "series_single")
//    private Boolean seriesSingle;
////    @Column(name = "total_episode")
    private Integer totalEpisode;
//    @Column(name = "is_free")
    private Boolean isFree;
//    @Column(name = "status")
////    @Range(min = 1, max = 3)
//    private Integer status; //Status 1 = Đang chiếu, 2 = Sắp chiếu, 3 = Ngừng chiếu

    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
//    @ManyToOne
////    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
//    @JoinColumn(name = "country_id")
//    private Country country;
    @ManyToMany()
    @JoinTable(name = "actor_film",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<Actor> actorList;
}
