package br.edu.ifpb.padroes.store;

public class SearchStrategyFactory {
    public static SearchStrategy get(SearchType type){
        return switch (type){
            case TITLE -> new SearchByTitleStrategy();
            case ARTIST -> new SearchByArtistStrategy();
            case GENRE -> new SearchByGenreStrategy();
            case TYPE -> new SearchByTypeStrategy();
        };
    }
}
