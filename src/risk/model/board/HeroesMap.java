package risk.model.board;

import risk.model.GameConstants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HeroesMap implements BoardFactory {
    private final Map<String, Country> countryMap = new HashMap<>();

    @Override
    public Board build() {
        final Continent Marvel = createMarvel();
        final Continent DC = createDC();
        final Continent Disney = createDisney();
        final Continent Wonderland = createWonderland();

        linkMarvelAdjacentCountries();
        linkDCAdjacentCountries();
        linkDisneyAdjacentCountries();
        linkWonderlandAdjacentCountries();

        final List<Continent> continents = new LinkedList<>();
        continents.add(Marvel);
        continents.add(Disney);
        continents.add(DC);
        continents.add(Wonderland);

        return new Board(continents, new GameConstants());
    }

    private Continent createMarvel() {
        Country IronMan = createCountry("IronMan");
        Country CaptainAmerica = createCountry("CaptainAmerica");
        Country Hulk = createCountry("Hulk");
        Country Thor = createCountry("Thor");
        Country BlackWidow = createCountry("BlackWidow");
        Country Hawkeye = createCountry("Hawkeye");

        final Continent Marvel = new Continent("Marvel", 6);
        Marvel.addCountry(IronMan);
        Marvel.addCountry(CaptainAmerica);
        Marvel.addCountry(Hulk);
        Marvel.addCountry(Thor);
        Marvel.addCountry(BlackWidow);
        Marvel.addCountry(Hawkeye);

        return Marvel;
    }

    private Continent createDisney() {
        Country Hercules = createCountry("Hercules");
        Country Tarzan = createCountry("Tarzan");
        Country Mowgli = createCountry("Mowgli");
        Country Mulan = createCountry("Mulan");

        final Continent Disney = new Continent("Disney", 4);

        Disney.addCountry(Hercules);
        Disney.addCountry(Tarzan);
        Disney.addCountry(Mowgli);
        Disney.addCountry(Mulan);

        return Disney;
    }

    private Continent createDC() {
        Country SuperMan = createCountry("SuperMan");
        Country BatMan = createCountry("BatMan");
        Country WonderWoman = createCountry("WonderWoman");
        Country Flash = createCountry("Flash");
        Country AquaMan = createCountry("AquaMan");

        final Continent DC = new Continent("DC", 5);

        DC.addCountry(SuperMan);
        DC.addCountry(BatMan);
        DC.addCountry(WonderWoman);
        DC.addCountry(Flash);
        DC.addCountry(AquaMan);

        return DC;
    }

    private Continent createWonderland() {
        Country Pocahontas = createCountry("Pocahontas");
        Country Alice = createCountry("Alice");
        Country Cindyrella = createCountry("Cindyrella");
        Country Belle = createCountry("Belle");
        Country Ariel = createCountry("Ariel");
        Country Elsa = createCountry("Elsa");

        final Continent DC = new Continent("DC", 3);

        DC.addCountry(Pocahontas);
        DC.addCountry(Alice);
        DC.addCountry(Cindyrella);
        DC.addCountry(Belle);
        DC.addCountry(Ariel);
        DC.addCountry(Elsa);

        return DC;
    }

    private void linkMarvelAdjacentCountries() {
        getCountry("IronMan").addAdjacentCountry(getCountry("CaptainAmerica"));
        getCountry("IronMan").addAdjacentCountry(getCountry("Hulk"));
        getCountry("IronMan").addAdjacentCountry(getCountry("Tarzan"));
        getCountry("CaptainAmerica").addAdjacentCountry(getCountry("IronMan"));
        getCountry("CaptainAmerica").addAdjacentCountry(getCountry("Thor"));
        getCountry("CaptainAmerica").addAdjacentCountry(getCountry("Hulk"));
        getCountry("Hulk").addAdjacentCountry(getCountry("IronMan"));
        getCountry("Hulk").addAdjacentCountry(getCountry("CaptainAmerica"));
        getCountry("Hulk").addAdjacentCountry(getCountry("Hawkeye"));
        getCountry("Hulk").addAdjacentCountry(getCountry("Hercules"));
        getCountry("Hulk").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("Thor").addAdjacentCountry(getCountry("CaptainAmerica"));
        getCountry("Thor").addAdjacentCountry(getCountry("Hawkeye"));
        getCountry("Thor").addAdjacentCountry(getCountry("BlackWidow"));
        getCountry("Hawkeye").addAdjacentCountry(getCountry("Thor"));
        getCountry("Hawkeye").addAdjacentCountry(getCountry("Hulk"));
        getCountry("Hawkeye").addAdjacentCountry(getCountry("BlackWidow"));
        getCountry("Hawkeye").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("BlackWidow").addAdjacentCountry(getCountry("Hawkeye"));
        getCountry("BlackWidow").addAdjacentCountry(getCountry("Thor"));
        getCountry("BlackWidow").addAdjacentCountry(getCountry("SuperMan"));
    }

    private void linkDCAdjacentCountries() {
        getCountry("SuperMan").addAdjacentCountry(getCountry("BlackWidow"));
        getCountry("SuperMan").addAdjacentCountry(getCountry("BatMan"));
        getCountry("SuperMan").addAdjacentCountry(getCountry("WonderWoman"));
        getCountry("SuperMan").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("BatMan").addAdjacentCountry(getCountry("SuperMan"));
        getCountry("BatMan").addAdjacentCountry(getCountry("WonderWoman"));
        getCountry("BatMan").addAdjacentCountry(getCountry("Pocahontas"));
        getCountry("BatMan").addAdjacentCountry(getCountry("Cindyrella"));
        getCountry("WonderWoman").addAdjacentCountry(getCountry("SuperMan"));
        getCountry("WonderWoman").addAdjacentCountry(getCountry("BatMan"));
        getCountry("WonderWoman").addAdjacentCountry(getCountry("AquaMan"));
        getCountry("WonderWoman").addAdjacentCountry(getCountry("Flash"));
        getCountry("Flash").addAdjacentCountry(getCountry("AquaMan"));
        getCountry("Flash").addAdjacentCountry(getCountry("WonderWoman"));
        getCountry("Flash").addAdjacentCountry(getCountry("Cindyrella"));
        getCountry("AquaMan").addAdjacentCountry(getCountry("Flash"));
        getCountry("AquaMan").addAdjacentCountry(getCountry("WonderWoman"));
        getCountry("AquaMan").addAdjacentCountry(getCountry("Elsa"));
    }

    private void linkDisneyAdjacentCountries() {
        getCountry("Tarzan").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("Tarzan").addAdjacentCountry(getCountry("Hercules"));
        getCountry("Tarzan").addAdjacentCountry(getCountry("IronMan"));
        getCountry("Hercules").addAdjacentCountry(getCountry("Tarzan"));
        getCountry("Hercules").addAdjacentCountry(getCountry("Hulk"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("Tarzan"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("Hawkeye"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("Hulk"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("Alice"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("Pocahontas"));
        getCountry("Mowgli").addAdjacentCountry(getCountry("SuperMan"));
    }

    private void linkWonderlandAdjacentCountries() {
        getCountry("Pocahontas").addAdjacentCountry(getCountry("BatMan"));
        getCountry("Pocahontas").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("Pocahontas").addAdjacentCountry(getCountry("Alice"));
        getCountry("Pocahontas").addAdjacentCountry(getCountry("Cindyrella"));
        getCountry("Alice").addAdjacentCountry(getCountry("Pocahontas"));
        getCountry("Alice").addAdjacentCountry(getCountry("Mowgli"));
        getCountry("Alice").addAdjacentCountry(getCountry("Belle"));
        getCountry("Cindyrella").addAdjacentCountry(getCountry("BatMan"));
        getCountry("Cindyrella").addAdjacentCountry(getCountry("Flash"));
        getCountry("Cindyrella").addAdjacentCountry(getCountry("Pocahontas"));
        getCountry("Cindyrella").addAdjacentCountry(getCountry("Belle"));
        getCountry("Belle").addAdjacentCountry(getCountry("Cindyrella"));
        getCountry("Belle").addAdjacentCountry(getCountry("Alice"));
        getCountry("Belle").addAdjacentCountry(getCountry("Ariel"));
        getCountry("Ariel").addAdjacentCountry(getCountry("Belle"));
        getCountry("Ariel").addAdjacentCountry(getCountry("Elsa"));
        getCountry("Elsa").addAdjacentCountry(getCountry("Ariel"));
        getCountry("Elsa").addAdjacentCountry(getCountry("AquaMan"));

    }

    private Country createCountry(final String name) {
        final Country country = new Country(name);
        countryMap.put(name, country);
        return country;
    }

    private Country getCountry(final String name) {
        return countryMap.get(name);
    }

}
