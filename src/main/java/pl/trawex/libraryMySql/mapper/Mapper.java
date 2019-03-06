package pl.trawex.libraryMySql.mapper;

public interface Mapper<F, T> {
    T map (F from);
    //T- obiekt zmapowany, czyli DTO
    //F- obiekt DAO, czysta encja
}
