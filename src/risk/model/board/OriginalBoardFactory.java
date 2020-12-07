package risk.model.board;

import risk.model.GameConstants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OriginalBoardFactory implements BoardFactory {
    private final Map<String, Country> countryMap = new HashMap<>();

    @Override
    public Board build() {
        final Continent NorthAmerica = createNorthAmerica();
        final Continent SouthAmerica = createSouthAmerica();
        final Continent Europe = createEurope();
        final Continent Africa = createAfrica();
        final Continent Asia = createAsia();
        final Continent Australia = createAustralia();

        linkNorthAmericaAdjacentCountries();
        linkSouthAmericaAdjacentCountries();
        linkEuropeAdjacentCountries();
        linkAfricaAdjacentCountries();
        linkAsiaAdjacentCountries();
        linkAustraliaAdjacentCountries();

        final List<Continent> continents = new LinkedList<>();
        continents.add(NorthAmerica);
        continents.add(SouthAmerica);
        continents.add(Europe);
        continents.add(Africa);
        continents.add(Asia);
        continents.add(Australia);

        return new Board(continents, new GameConstants());
    }

    /**
     * create North America
     */
    private Continent createNorthAmerica() {
        Country Alaska = createCountry("Alaska");
        Country Alberta = createCountry("Alberta");
        Country CentralAmerica = createCountry("CentralAmerica");
        Country EasternUnitedStates = createCountry("EasternUnitedStates");
        Country Greenland = createCountry("Greenland");
        Country NorthwestTerritory = createCountry("NorthwestTerritory");
        Country Ontario = createCountry("Ontario");
        Country Quebec = createCountry("Quebec");
        Country WesternUnitedStates = createCountry("WesternUnitedStates");

        final Continent NorthAmerica = new Continent("NorthAmerica", 5);
        NorthAmerica.addCountry(Alaska);
        NorthAmerica.addCountry(Alberta);
        NorthAmerica.addCountry(CentralAmerica);
        NorthAmerica.addCountry(EasternUnitedStates);
        NorthAmerica.addCountry(Greenland);
        NorthAmerica.addCountry(NorthwestTerritory);
        NorthAmerica.addCountry(Ontario);
        NorthAmerica.addCountry(Quebec);
        NorthAmerica.addCountry(WesternUnitedStates);

        return NorthAmerica;
    }

    /**
     * create South America
     */
    private Continent createSouthAmerica() {
        Country Argentina = createCountry("Argentina");
        Country Brazil = createCountry("Brazil");
        Country Peru = createCountry("Peru");
        Country Venezuela = createCountry("Venezuela");

        final Continent SouthAmerica = new Continent("SouthAmerica", 2);

        SouthAmerica.addCountry(Argentina);
        SouthAmerica.addCountry(Brazil);
        SouthAmerica.addCountry(Peru);
        SouthAmerica.addCountry(Venezuela);

        return SouthAmerica;
    }

    /**
     * create Europe
     */
    private Continent createEurope() {
        Country GreatBritain = createCountry("GreatBritain");
        Country Iceland = createCountry("Iceland");
        Country NorthernEurope = createCountry("NorthernEurope");
        Country Scandinavia = createCountry("Scandinavia");
        Country SouthernEurope = createCountry("SouthernEurope");
        Country Ukraine = createCountry("Ukraine");
        Country WesternEurope = createCountry("WesternEurope");

        final Continent Europe = new Continent("Europe", 5);

        Europe.addCountry(GreatBritain);
        Europe.addCountry(Iceland);
        Europe.addCountry(NorthernEurope);
        Europe.addCountry(Scandinavia);
        Europe.addCountry(SouthernEurope);
        Europe.addCountry(Ukraine);
        Europe.addCountry(WesternEurope);

        return Europe;
    }

    /**
     * create Africa
     */
    private Continent createAfrica() {
        Country Congo = createCountry("Congo");
        Country EastAfrica = createCountry("EastAfrica");
        Country Egypt = createCountry("Egypt");
        Country Madagascar = createCountry("Madagascar");
        Country NorthAfrica = createCountry("NorthAfrica");
        Country SouthAfrica = createCountry("SouthAfrica");

        final Continent Africa = new Continent("Africa", 3);

        Africa.addCountry(Congo);
        Africa.addCountry(EastAfrica);
        Africa.addCountry(Egypt);
        Africa.addCountry(Madagascar);
        Africa.addCountry(NorthAfrica);
        Africa.addCountry(SouthAfrica);

        return Africa;
    }

    /**
     * create Asia
     */
    private Continent createAsia() {
        Country Afghanistan = createCountry("Afghanistan");
        Country China = createCountry("China");
        Country India = createCountry("India");
        Country Irkutsk = createCountry("Irkutsk");
        Country Japan = createCountry("Japan");
        Country Kamchatka = createCountry("Kamchatka");
        Country MiddleEast = createCountry("MiddleEast");
        Country Mongolia = createCountry("Mongolia");
        Country Siam = createCountry("Siam");
        Country Siberia = createCountry("Siberia");
        Country Ural = createCountry("Ural");
        Country Yakutsk = createCountry("Yakutsk");

        final Continent Asia = new Continent("Asia", 7);

        Asia.addCountry(Afghanistan);
        Asia.addCountry(China);
        Asia.addCountry(India);
        Asia.addCountry(Irkutsk);
        Asia.addCountry((Japan));
        Asia.addCountry(Kamchatka);
        Asia.addCountry(MiddleEast);
        Asia.addCountry(Mongolia);
        Asia.addCountry(Siam);
        Asia.addCountry(Siberia);
        Asia.addCountry(Ural);
        Asia.addCountry(Yakutsk);

        return Asia;
    }

    /**
     * create Australia
     */
    private Continent createAustralia() {
        Country EasternAustralia = createCountry("EasternAustralia");
        Country Indonesia = createCountry("Indonesia");
        Country NewGuinea = createCountry("NewGuinea");
        Country WesternAustralia = createCountry("WesternAustralia");

        final Continent Australia = new Continent("Australia", 2);

        Australia.addCountry(EasternAustralia);
        Australia.addCountry(Indonesia);
        Australia.addCountry(NewGuinea);
        Australia.addCountry(WesternAustralia);

        return Australia;
    }

    /**
     * Initialize adjacent counntries in North America
     */
    private void linkNorthAmericaAdjacentCountries() {
        getCountry("Alaska").addAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Alaska").addAdjacentCountry(getCountry("Alberta"));
        getCountry("Alaska").addAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Alberta").addAdjacentCountry(getCountry("Alaska"));
        getCountry("Alberta").addAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Alberta").addAdjacentCountry(getCountry("Ontario"));
        getCountry("Alberta").addAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("CentralAmerica").addAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("CentralAmerica").addAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("CentralAmerica").addAdjacentCountry(getCountry("Venezuela"));
        getCountry("EasternUnitedStates").addAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("EasternUnitedStates").addAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("EasternUnitedStates").addAdjacentCountry(getCountry("Ontario"));
        getCountry("EasternUnitedStates").addAdjacentCountry(getCountry("Quebec"));
        getCountry("Greenland").addAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Greenland").addAdjacentCountry(getCountry("Ontario"));
        getCountry("Greenland").addAdjacentCountry(getCountry("Quebec"));
        getCountry("Greenland").addAdjacentCountry(getCountry("Iceland"));
        getCountry("NorthwestTerritory").addAdjacentCountry(getCountry("Alaska"));
        getCountry("NorthwestTerritory").addAdjacentCountry(getCountry("Alberta"));
        getCountry("NorthwestTerritory").addAdjacentCountry(getCountry("Greenland"));
        getCountry("NorthwestTerritory").addAdjacentCountry(getCountry("Ontario"));
        getCountry("Ontario").addAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("Ontario").addAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("Ontario").addAdjacentCountry(getCountry("Greenland"));
        getCountry("Ontario").addAdjacentCountry(getCountry("Quebec"));
        getCountry("Ontario").addAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Ontario").addAdjacentCountry(getCountry("Alberta"));
        getCountry("Quebec").addAdjacentCountry(getCountry("Greenland"));
        getCountry("Quebec").addAdjacentCountry(getCountry("Ontario"));
        getCountry("Quebec").addAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("WesternUnitedStates").addAdjacentCountry(getCountry("Alberta"));
        getCountry("WesternUnitedStates").addAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("WesternUnitedStates").addAdjacentCountry(getCountry("EasternUnitedStates"));

    }

    /**
     * Initialize adjacent counntries in South America
     */
    private void linkSouthAmericaAdjacentCountries() {
        getCountry("Argentina").addAdjacentCountry(getCountry("Peru"));
        getCountry("Argentina").addAdjacentCountry(getCountry("Brazil"));
        getCountry("Brazil").addAdjacentCountry(getCountry("Argentina"));
        getCountry("Brazil").addAdjacentCountry(getCountry("Peru"));
        getCountry("Brazil").addAdjacentCountry(getCountry("Venezuela"));
        getCountry("Brazil").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("Peru").addAdjacentCountry(getCountry("Argentina"));
        getCountry("Peru").addAdjacentCountry(getCountry("Brazil"));
        getCountry("Peru").addAdjacentCountry(getCountry("Venezuela"));
        getCountry("Venezuela").addAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("Venezuela").addAdjacentCountry(getCountry("Peru"));
        getCountry("Venezuela").addAdjacentCountry(getCountry("Brazil"));

    }

    /**
     * Initialize adjacent countries in Europe
     */
    private void linkEuropeAdjacentCountries() {
        getCountry("GreatBritain").addAdjacentCountry(getCountry("Iceland"));
        getCountry("GreatBritain").addAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("GreatBritain").addAdjacentCountry(getCountry("Scandinavia"));
        getCountry("GreatBritain").addAdjacentCountry(getCountry("WesternEurope"));
        getCountry("Iceland").addAdjacentCountry(getCountry("Greenland"));
        getCountry("Iceland").addAdjacentCountry(getCountry("GreatBritain"));
        getCountry("Iceland").addAdjacentCountry(getCountry("Scandinavia"));
        getCountry("NorthernEurope").addAdjacentCountry(getCountry("GreatBritain"));
        getCountry("NorthernEurope").addAdjacentCountry(getCountry("Scandinavia"));
        getCountry("NorthernEurope").addAdjacentCountry(getCountry("WesternEurope"));
        getCountry("NorthernEurope").addAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("NorthernEurope").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("Scandinavia").addAdjacentCountry(getCountry("Iceland"));
        getCountry("Scandinavia").addAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("Scandinavia").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("Egypt"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("WesternEurope"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("SouthernEurope").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("Scandinavia"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Ukraine").addAdjacentCountry(getCountry("Ural"));
        getCountry("WesternEurope").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("WesternEurope").addAdjacentCountry(getCountry("GreatBritain"));
        getCountry("WesternEurope").addAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("WesternEurope").addAdjacentCountry(getCountry("SouthernEurope"));

    }

    /**
     * Initialize adjacent counntries in Africa
     */
    private void linkAfricaAdjacentCountries() {
        getCountry("Congo").addAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("Congo").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Congo").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("Congo"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("Egypt"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("Madagascar"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("EastAfrica").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Egypt").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Egypt").addAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("Egypt").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Egypt").addAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("Madagascar").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Madagascar").addAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("Congo"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("Egypt"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("Brazil"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("WesternEurope"));
        getCountry("NorthAfrica").addAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("SouthAfrica").addAdjacentCountry(getCountry("Congo"));
        getCountry("SouthAfrica").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("SouthAfrica").addAdjacentCountry(getCountry("Madagascar"));

    }

    /**
     * Initialize adjacent counntries in Asia
     */
    private void linkAsiaAdjacentCountries() {
        getCountry("Afghanistan").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("Afghanistan").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Afghanistan").addAdjacentCountry(getCountry("Ural"));
        getCountry("Afghanistan").addAdjacentCountry(getCountry("India"));
        getCountry("Afghanistan").addAdjacentCountry(getCountry("China"));
        getCountry("China").addAdjacentCountry(getCountry("Siam"));
        getCountry("China").addAdjacentCountry(getCountry("India"));
        getCountry("China").addAdjacentCountry(getCountry("Afghanistan"));
        getCountry("China").addAdjacentCountry(getCountry("Ural"));
        getCountry("China").addAdjacentCountry(getCountry("Siberia"));
        getCountry("China").addAdjacentCountry(getCountry("Mongolia"));
        getCountry("India").addAdjacentCountry(getCountry("Siam"));
        getCountry("India").addAdjacentCountry(getCountry("MiddleEast"));
        getCountry("India").addAdjacentCountry(getCountry("China"));
        getCountry("India").addAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Irkutsk").addAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Irkutsk").addAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Irkutsk").addAdjacentCountry(getCountry("Mongolia"));
        getCountry("Irkutsk").addAdjacentCountry(getCountry("Siberia"));
        getCountry("Japan").addAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Japan").addAdjacentCountry(getCountry("Mongolia"));
        getCountry("Kamchatka").addAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Kamchatka").addAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Kamchatka").addAdjacentCountry(getCountry("Mongolia"));
        getCountry("Kamchatka").addAdjacentCountry(getCountry("Japan"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("EastAfrica"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("Egypt"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("Afghanistan"));
        getCountry("MiddleEast").addAdjacentCountry(getCountry("India"));
        getCountry("Mongolia").addAdjacentCountry(getCountry("Siberia"));
        getCountry("Mongolia").addAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Mongolia").addAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Mongolia").addAdjacentCountry(getCountry("Japan"));
        getCountry("Mongolia").addAdjacentCountry(getCountry("China"));
        getCountry("Siam").addAdjacentCountry(getCountry("China"));
        getCountry("Siam").addAdjacentCountry(getCountry("India"));
        getCountry("Siam").addAdjacentCountry(getCountry("Indonesia"));
        getCountry("Siberia").addAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Siberia").addAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Siberia").addAdjacentCountry(getCountry("Mongolia"));
        getCountry("Siberia").addAdjacentCountry(getCountry("China"));
        getCountry("Siberia").addAdjacentCountry(getCountry("Ural"));
        getCountry("Ural").addAdjacentCountry(getCountry("Ukraine"));
        getCountry("Ural").addAdjacentCountry(getCountry("Siberia"));
        getCountry("Ural").addAdjacentCountry(getCountry("China"));
        getCountry("Ural").addAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Yakutsk").addAdjacentCountry(getCountry("Siberia"));
        getCountry("Yakutsk").addAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Yakutsk").addAdjacentCountry(getCountry("Irkutsk"));

    }

    /**
     * Initialize adjacent countries in Australia
     */
    private void linkAustraliaAdjacentCountries() {
        getCountry("EasternAustralia").addAdjacentCountry(getCountry("WesternAustralia"));
        getCountry("EasternAustralia").addAdjacentCountry(getCountry("NewGuinea"));
        getCountry("Indonesia").addAdjacentCountry(getCountry("Siam"));
        getCountry("Indonesia").addAdjacentCountry(getCountry("NewGuinea"));
        getCountry("Indonesia").addAdjacentCountry(getCountry("WesternAustralia"));
        getCountry("Indonesia").addAdjacentCountry(getCountry("EasternAustralia"));
        getCountry("NewGuinea").addAdjacentCountry(getCountry("EasternAustralia"));
        getCountry("NewGuinea").addAdjacentCountry(getCountry("Indonesia"));
        getCountry("NewGuinea").addAdjacentCountry(getCountry("WesternAustralia"));
        getCountry("WesternAustralia").addAdjacentCountry(getCountry("EasternAustralia"));
        getCountry("WesternAustralia").addAdjacentCountry(getCountry("NewGuinea"));
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