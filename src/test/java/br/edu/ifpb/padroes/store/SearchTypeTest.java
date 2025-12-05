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
}
