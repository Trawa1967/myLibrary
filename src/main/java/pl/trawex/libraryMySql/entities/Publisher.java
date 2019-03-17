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
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPub;
    @Column(name = "name", nullable = false)
    private String namePub;
    @Column(name = "address", nullable = false)
    private String addressPub;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            })

    private Set<Book> books = new HashSet<>();

    public Publisher(String namePub, String addressPub) {
        this.namePub = namePub;
        this.addressPub = addressPub;
    }
}
