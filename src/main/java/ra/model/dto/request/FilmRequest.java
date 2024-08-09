package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Actor;
import ra.model.entity.Category;
import ra.model.entity.Type;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilmRequest {
    private Integer id;
    //    @Column(name = "film_name", unique = true)
    private String name;
    //    @Column(name = "film_description")
    private String description;
    //    @Column(name = "film_image")
    private MultipartFile multipartFile;
    //    @Column(name = "trailer_url")
    private String trailerUrl;
    //    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    //    @Column(name = "director")
    private String director;
    //    @Column(name = "main_actor_name")
    private String mainActorName;
    //    @Column(name = "main_actress_name")
    private String mainActressName;
    //    @Column(name = "language")
    private Integer totalEpisode;
    //    @Column(name = "is_free")
    private Boolean isFree;
//    @ManyToOne
////    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
//    @JoinColumn(name = "category_id")
    private Category category;
    private Type type;
    private Set<Long> actorsId;
//    @ManyToOne
////    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
//    @JoinColumn(name = "country_id")
//    private Country country;
}
