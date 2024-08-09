package ra.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
@Builder
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
    private String name;
}
