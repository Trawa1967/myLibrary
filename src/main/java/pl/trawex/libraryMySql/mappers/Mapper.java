package pl.trawex.libraryMySql.mappers;

public interface Mapper<F, T> {
    T map(F from);
}
