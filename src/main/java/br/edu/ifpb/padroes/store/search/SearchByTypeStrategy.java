package br.edu.ifpb.padroes.store;

import br.edu.ifpb.padroes.music.Album;

import java.util.ArrayList;
import java.util.List;

public class SearchByTypeStrategy implements SearchStrategy{
    @Override
    public List<Album> search(List<Album> inventory, String searchTerm) {
        List<Album> results = new ArrayList<>();
        for (Album album : inventory) {
            if (album.getType().name().equalsIgnoreCase(searchTerm)) {
                results.add(album);
            }
        }
        return results;
    }
}
