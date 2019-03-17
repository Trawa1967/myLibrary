package pl.trawex.libraryMySql.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.trawex.libraryMySql.entities.Book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {

    private int idPub;
    private String namePub;
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

    private Set<BookDto> book = new HashSet<>();
}
