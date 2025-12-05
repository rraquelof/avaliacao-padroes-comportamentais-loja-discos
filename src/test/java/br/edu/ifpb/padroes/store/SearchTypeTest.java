package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.music.AgeRestriction;
import br.edu.ifpb.padroes.music.Album;
import br.edu.ifpb.padroes.music.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTypeTest {
    private MusicStore store;

    @BeforeEach
    void setUp() {
        this.store = new MusicStore();
    }

    @Test
    @DisplayName("Should return empty list when search finds no matches")
    void testSearchNoResults() {
        Album album1 = new Album("Man's Best Friend","Sabrina Carpenter", MediaType.CD,
                50.00, LocalDate.of(2024, Month.JULY, 1), AgeRestriction.GENERAL,
                "Pop", 5);
        store.addMusic(album1);
        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.TITLE);
        List<Album> result = store.searchMusic(strategy, "AlbumThatDoesNotExist");
        assertTrue(result.isEmpty(), "Search should return empty list when no titles match");
    }

    @Test
    @DisplayName("Should search albums ignoring case sensitivity")
    void testSearchCaseInsensitive() {
        Album album1 = new Album("Man's Best Friend","Sabrina Carpenter", MediaType.CD,
                50.00, LocalDate.of(2024, Month.JULY, 1), AgeRestriction.GENERAL,
                "Pop", 5);
        store.addMusic(album1);
        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.TITLE);
        List<Album> result = store.searchMusic(strategy, "MAn's Best Friend");

        assertEquals(1, result.size());
        assertEquals("Man's Best Friend", result.getFirst().getTitle());
    }
    @Test
    @DisplayName("Should return multiple albums when more than one matches the search")
    void testSearchMultipleMatches() {
        Album album1 = new Album("Man's Best Friend","Sabrina Carpenter", MediaType.CD,
                50.00, LocalDate.of(2024, Month.JULY, 1), AgeRestriction.GENERAL,
                "Pop", 5);
        Album album2 = new Album("Short n' Sweet","Sabrina Carpenter", MediaType.CD,
                50.00, LocalDate.of(2024, Month.JULY, 1), AgeRestriction.GENERAL,
                "Pop", 5);

        store.addMusic(album1);
        store.addMusic(album2);

        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.ARTIST);
        List<Album> result = store.searchMusic(strategy, "Sabrina Carpenter");

        assertEquals(2, result.size());
        assertTrue(result.contains(album1));
        assertTrue(result.contains(album2));
    }
    @Test
    @DisplayName("Should search albums by partial genre")
    void testSearchByPartialGenre() {
        Album album1 = new Album("Man's Best Friend","Sabrina Carpenter", MediaType.CD,
                50.00, LocalDate.of(2024, Month.JULY, 1), AgeRestriction.GENERAL,
                "pop country", 5);
        store.addMusic(album1);
        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.GENRE);
        List<Album> result = store.searchMusic(strategy, "country");

        assertEquals(1, result.size());
        assertEquals("pop country", result.getFirst().getGenre());
    }

    @Test
    @DisplayName("Should search albums by title")
    void testSearchByTitle(){
        Album album1 = new Album("Ten", "Pearl Jam", MediaType.CD, 50.00,LocalDate.of(1991, Month.AUGUST, 27),AgeRestriction.GENERAL, "Grunge",5);
        Album album2 = new Album("Stadium Arcadium", "Red Hot Chili Peppers", MediaType.VINYL, 120.00, LocalDate.of(2006, Month.MAY, 9), AgeRestriction.GENERAL, "Rock", 7);

        store.addMusic(album1);
        store.addMusic(album2);

        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.TITLE);
        List<Album> result = store.searchMusic(strategy, "Ten");
        assertEquals(1, result.size(), "Only the album 'Ten' should be returned.");
        assertEquals("Ten", result.getFirst().getTitle());
    }

    @Test
    @DisplayName("Should search albums by artist")
    void testSearchByArtist(){
        Album album1 = new Album("Ten", "Pearl Jam", MediaType.CD, 50.00,LocalDate.of(1991, Month.AUGUST, 27),AgeRestriction.GENERAL, "Grunge",5);
        Album album2 = new Album("Stadium Arcadium", "Red Hot Chili Peppers", MediaType.VINYL, 120.00, LocalDate.of(2006, Month.MAY, 9), AgeRestriction.GENERAL, "Rock", 7);

        store.addMusic(album1);
        store.addMusic(album2);

        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.ARTIST);
        List<Album> result = store.searchMusic(strategy, "Red Hot Chili Peppers");

        assertEquals(1, result.size());
        assertEquals("Red Hot Chili Peppers", result.getFirst().getArtist());
    }

    @Test
    @DisplayName("Should search albums by genre")
    void testSearchByGenre(){
        Album album1 = new Album("Ten", "Pearl Jam", MediaType.CD, 50.00,LocalDate.of(1991, Month.AUGUST, 27),AgeRestriction.GENERAL, "Grunge",5);
        Album album2 = new Album("Stadium Arcadium", "Red Hot Chili Peppers", MediaType.VINYL, 120.00, LocalDate.of(2006, Month.MAY, 9), AgeRestriction.GENERAL, "Rock", 7);

        store.addMusic(album1);
        store.addMusic(album2);

        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.GENRE);
        List<Album> result = store.searchMusic(strategy, "Grunge");

        assertEquals(1, result.size());
        assertEquals("Grunge", result.getFirst().getGenre());
        assertTrue(result.contains(album1));
    }

    @Test
    @DisplayName("Should search albums by media type")
    void testSearhByType(){
        Album album1 = new Album("Ten", "Pearl Jam", MediaType.CD, 50.00,LocalDate.of(1991, Month.AUGUST, 27),AgeRestriction.GENERAL, "Grunge",5);
        Album album2 = new Album("Stadium Arcadium", "Red Hot Chili Peppers", MediaType.VINYL, 120.00, LocalDate.of(2006, Month.MAY, 9), AgeRestriction.GENERAL, "Rock", 7);

        store.addMusic(album1);
        store.addMusic(album2);

        SearchStrategy strategy = SearchStrategyFactory.get(SearchType.TYPE);
        List<Album> result = store.searchMusic(strategy, "vinyl");

        assertEquals(1, result.size());
        assertEquals(MediaType.VINYL, result.getFirst().getType());
        assertTrue(result.contains(album2));
    }
}
