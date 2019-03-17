package pl.trawex.libraryMySql.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kinds")
public class Kind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKind;

    @Column(name = "name_of_kind", unique = true, nullable = false)
    private String nameOfKind;

    @JsonIgnore
    @OneToMany(mappedBy = "kind",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            })

    private Set<Book> books = new HashSet<>();

    public Kind(String nameOfKind) {
        this.nameOfKind = nameOfKind;
    }
}
