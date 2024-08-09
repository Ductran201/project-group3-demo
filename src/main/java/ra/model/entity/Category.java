package ra.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "Tên thể loại không được để trống")
    private String name;
//    @Column(name = "status")
    private Boolean status;
//    @OneToMany(mappedBy = "category")
//    @Transient
//    private List<Film> filmList;
}
