package risk.model.board.customMaps;

import risk.model.GameConstants;
import risk.model.board.Board;
import risk.model.board.BoardFactory;
import risk.model.board.Continent;
import risk.model.board.Country;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class was created to build an invalid custom map.
 * This map is invalid because there's a country that doesn't have an adjacent country.
 * It does not have any responsibilities in the submission of milestone 4.
 * @athor Diana Miraflor
 */
public class InvalidBoardFactory implements BoardFactory {
    private final Map<String, Country> invalidMap = new HashMap<>();

    @Override
    public Board build() {
        final Continent GTA = createGTAMap();
        final Continent Carleton = createCarletonMap();

        linkCarleton();
        linkGTA();

        final List<Continent> carletonContinents = new LinkedList<>();
        carletonContinents.add(GTA);
        carletonContinents.add(Carleton);

        return new Board(carletonContinents, new GameConstants());

    }

    private Continent createGTAMap() {
        Country Toronto = createCountry("Toronto");
        Country Brampton = createCountry("Brampton");
        Country Vaughan = createCountry("Vaughan");

        final Continent GTA = new Continent("GTA", 3);
        GTA.addCountry(Toronto);
        GTA.addCountry(Brampton);
        GTA.addCountry(Vaughan);

        return GTA;
    }

    private Continent createCarletonMap() {
        Country Mackenzie = createCountry("Mackenzie");
        Country MacOdrum = createCountry("MacOdrum");

        final Continent CarletonUniversity = new Continent("CarletonUniversity", 2);
        CarletonUniversity.addCountry(Mackenzie);
        CarletonUniversity.addCountry(MacOdrum);

        return CarletonUniversity;
    }

    private void linkGTA() {
        getCountry("Toronto").addAdjacentCountry(getCountry("Brampton"));
        getCountry("Toronto").addAdjacentCountry(getCountry("Vaughan"));
        getCountry("Toronto").addAdjacentCountry(getCountry("MacOdrum"));

        getCountry("Brampton").addAdjacentCountry(getCountry("Vaughan"));
        getCountry("Brampton").addAdjacentCountry(getCountry("Toronto"));

        getCountry("Vaughan").addAdjacentCountry(getCountry("Toronto"));
        getCountry("Vaughan").addAdjacentCountry(getCountry("Brampton"));
    }

    private void linkCarleton() {
        getCountry("MacOdrum").addAdjacentCountry(getCountry("Toronto"));

        // Mackenzie is unreachable
    }

    private Country createCountry(final String name) {
        final Country country = new Country(name);
        invalidMap.put(name, country);
        return country;
    }

    private Country getCountry(final String name) {
        return invalidMap.get(name);
    }

}
