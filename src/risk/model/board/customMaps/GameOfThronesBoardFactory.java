package risk.model.board.customMaps;

import risk.model.GameConstants;
import risk.model.board.Board;
import risk.model.board.Continent;
import risk.model.board.Country;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class was created to build Game of thrones custom map.
 * It does not have any responsibilities in the submission of milestone 4.
 * @athor Sarah Jaber
 */
public class GameOfThronesBoardFactory {

    private final Map<String, Country> countryMap = new HashMap<>();

    public Board build() {
        final Continent Andalos = createAndalos();
        final Continent TheDisputedLands = createTheDisputedLands();
        final Continent LandsOfLongSummer = createLandsOfLongSummer();
        final Continent SlaversBay = createSlaversBay();
        final Continent JadeSeaCoast = createJadeSeaCoast();
        final Continent TheDothrakiSea = createTheDothrakiSea();

        linkAndalosAdjacentCountries();
        linkTheDisputedLandsAdjacentCountries();
        linkLandsOfLongSummerAdjacentCountries();
        linkSlaversBayAdjacentCountries();
        linkJadeSeaCoastAdjacentCountries();
        linkTheDothrakiSeaAdjacentCountries();

        final List<Continent> continents = new LinkedList<>();
        continents.add(Andalos);
        continents.add(TheDisputedLands);
        continents.add(LandsOfLongSummer);
        continents.add(SlaversBay);
        continents.add(JadeSeaCoast);
        continents.add(TheDothrakiSea);

        return new Board(continents, new GameConstants());
    }

    /**
     * create North America
     */
    private Continent createAndalos() {
        Country BraavosianCoastLands = createCountry("BraavosianCoastLands");
        Country TheAxe = createCountry("TheAxe");
        Country HillsOfNorvos = createCountry("HillsOfNorvos");
        Country ForestOfGonor = createCountry("ForestOfGonor");
        Country UpperRnoyne = createCountry("UpperRnoyne");
        Country TheFlatLands = createCountry("TheFlatLands");
        Country TheGoldenFields = createCountry("TheGoldenFields");

        final Continent Andalos = new Continent("Andalos", 5);
        Andalos.addCountry(BraavosianCoastLands);
        Andalos.addCountry(TheAxe);
        Andalos.addCountry(HillsOfNorvos);
        Andalos.addCountry(ForestOfGonor);
        Andalos.addCountry(UpperRnoyne);
        Andalos.addCountry(TheFlatLands);
        Andalos.addCountry(TheGoldenFields);

        return Andalos;
    }

    /**
     * create South America
     */
    private Continent createTheDisputedLands() {
        Country Tyrosn = createCountry("Tyrosn");
        Country Myr = createCountry("Myr");
        Country TheOranosShore = createCountry("TheOranosShore");
        Country LowerRnoyne = createCountry("LowerRnoyne");
        Country Volantis = createCountry("Volantis");


        final Continent TheDisputedLands = new Continent("TheDisputedLands", 2);

        TheDisputedLands.addCountry(Tyrosn);
        TheDisputedLands.addCountry(Myr);
        TheDisputedLands.addCountry(TheOranosShore);
        TheDisputedLands.addCountry(LowerRnoyne);
        TheDisputedLands.addCountry(Volantis);

        return TheDisputedLands;
    }

    /**
     * create Europe
     */
    private Continent createLandsOfLongSummer() {
        Country Mantarys = createCountry("Mantarys");
        Country TheSeaOfSionts = createCountry("TheSeaOfSionts");
        Country Oros = createCountry("Oros");
        Country Valyria = createCountry("Valyria");

        final Continent LandsOfLongSummer = new Continent("LandsOfLongSummer", 5);

        LandsOfLongSummer.addCountry(Mantarys);
        LandsOfLongSummer.addCountry(TheSeaOfSionts);
        LandsOfLongSummer.addCountry(Oros);
        LandsOfLongSummer.addCountry(Valyria);

        return LandsOfLongSummer;
    }

    /**
     * create Africa
     */
    private Continent createSlaversBay() {
        Country Mereem = createCountry("Mereem");
        Country Yunkai = createCountry("Yunkai");
        Country Asyapor = createCountry("Asyapor");
        Country OldOnis = createCountry("OldOnis");
        Country TheRedWaste = createCountry("TheRedWaste");

        final Continent SlaversBay = new Continent("SlaversBay", 3);

        SlaversBay.addCountry(Mereem);
        SlaversBay.addCountry(Yunkai);
        SlaversBay.addCountry(Asyapor);
        SlaversBay.addCountry(OldOnis);
        SlaversBay.addCountry(TheRedWaste);

        return SlaversBay;
    }

    /**
     * create Asia
     */
    private Continent createJadeSeaCoast() {
        Country Oartn = createCountry("Oartn");
        Country Sayasasmad = createCountry("Sayasasmad");
        Country Samyrian = createCountry("Samyrian");
        Country NorthernJadeSea = createCountry("NorthernJadeSea");

        final Continent JadeSeaCoast = new Continent("JadeSeaCoast", 7);

        JadeSeaCoast.addCountry(Oartn);
        JadeSeaCoast.addCountry(Sayasasmad);
        JadeSeaCoast.addCountry(Samyrian);
        JadeSeaCoast.addCountry(NorthernJadeSea);

        return JadeSeaCoast;
    }

    /**
     * create Australia
     */
    private Continent createTheDothrakiSea() {
        Country TheFootPrint = createCountry("TheFootPrint");
        Country VassDothrak = createCountry("VassDothrak");
        Country EasternDothrakiSea = createCountry("EasternDothrakiSea");
        Country Lhaiar = createCountry("Lhaiar");
        Country NorthernDothrakiSea = createCountry("NorthernDothrakiSea");
        Country WesternDothrakiSea = createCountry("WesternDothrakiSea");
        Country Sarnor = createCountry("Sarnor");


        final Continent TheDothrakiSea = new Continent("TheDothrakiSea", 2);

        TheDothrakiSea.addCountry(TheFootPrint);
        TheDothrakiSea.addCountry(VassDothrak);
        TheDothrakiSea.addCountry(NorthernDothrakiSea);
        TheDothrakiSea.addCountry(Sarnor);
        TheDothrakiSea.addCountry(EasternDothrakiSea);
        TheDothrakiSea.addCountry(Lhaiar);
        TheDothrakiSea.addCountry(WesternDothrakiSea);

        return TheDothrakiSea;
    }

    /**
     * Initialize adjacent counntries in North America
     */
    private void linkAndalosAdjacentCountries() {
        getCountry("BraavosianCoastLands").addAdjacentCountry(getCountry("TheAxe"));
        getCountry("BraavosianCoastLands").addAdjacentCountry(getCountry("HillsOfNorvos"));
        getCountry("BraavosianCoastLands").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("BraavosianCoastLands").addAdjacentCountry(getCountry("TheFlatLands"));
        getCountry("TheAxe").addAdjacentCountry(getCountry("BraavosianCoastLands"));
        getCountry("TheAxe").addAdjacentCountry(getCountry("HillsOfNorvos"));
        getCountry("TheAxe").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("HillsOfNorvos").addAdjacentCountry(getCountry("BraavosianCoastLands"));
        getCountry("HillsOfNorvos").addAdjacentCountry(getCountry("TheAxe"));
        getCountry("HillsOfNorvos").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("HillsOfNorvos").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("TheAxe"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("HillsOfNorvos"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("ForestOfGonor").addAdjacentCountry(getCountry("Sarnor"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("HillsOfNorvos"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("BraavosianCoastLands"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("TheFlatLands"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("TheGoldenFields"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("UpperRnoyne").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("TheFlatLands").addAdjacentCountry(getCountry("BraavosianCoastLands"));
        getCountry("TheFlatLands").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("TheFlatLands").addAdjacentCountry(getCountry("TheGoldenFields"));
        getCountry("TheGoldenFields").addAdjacentCountry(getCountry("TheFlatLands"));
        getCountry("TheGoldenFields").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("TheGoldenFields").addAdjacentCountry(getCountry("Myr"));
        getCountry("TheGoldenFields").addAdjacentCountry(getCountry("LowerRnoyne"));

    }

    /**
     * Initialize adjacent counntries in South America
     */
    private void linkTheDisputedLandsAdjacentCountries() {
        getCountry("Tyrosn").addAdjacentCountry(getCountry("Myr"));
        getCountry("Tyrosn").addAdjacentCountry(getCountry("TheOranosShore"));
        getCountry("Myr").addAdjacentCountry(getCountry("Tyrosn"));
        getCountry("Myr").addAdjacentCountry(getCountry("TheOranosShore"));
        getCountry("Myr").addAdjacentCountry(getCountry("TheGoldenFields"));
        getCountry("Myr").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("TheOranosShore").addAdjacentCountry(getCountry("Tyrosn"));
        getCountry("TheOranosShore").addAdjacentCountry(getCountry("Myr"));
        getCountry("TheOranosShore").addAdjacentCountry(getCountry("Volantis"));
        getCountry("TheOranosShore").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("Volantis"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("TheOranosShore"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("Myr"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("TheGoldenFields"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("UpperRnoyne"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("Mantarys"));
        getCountry("LowerRnoyne").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("Volantis").addAdjacentCountry(getCountry("TheOranosShore"));
        getCountry("Volantis").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("Volantis").addAdjacentCountry(getCountry("Mantarys"));

    }

    /**
     * Initialize adjacent countries in Europe
     */
    private void linkLandsOfLongSummerAdjacentCountries() {
        getCountry("Mantarys").addAdjacentCountry(getCountry("Volantis"));
        getCountry("Mantarys").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("Mantarys").addAdjacentCountry(getCountry("TheSeaOfSionts"));
        getCountry("Mantarys").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("Mantarys").addAdjacentCountry(getCountry("Mereem"));
        getCountry("TheSeaOfSionts").addAdjacentCountry(getCountry("Mantarys"));
        getCountry("TheSeaOfSionts").addAdjacentCountry(getCountry("Oros"));
        getCountry("TheSeaOfSionts").addAdjacentCountry(getCountry("Valyria"));
        getCountry("Oros").addAdjacentCountry(getCountry("TheSeaOfSionts"));
        getCountry("Oros").addAdjacentCountry(getCountry("Valyria"));
        getCountry("Valyria").addAdjacentCountry(getCountry("Oros"));
        getCountry("Valyria").addAdjacentCountry(getCountry("TheSeaOfSionts"));

    }

    /**
     * Initialize adjacent counntries in Africa
     */
    private void linkSlaversBayAdjacentCountries() {
        getCountry("Mereem").addAdjacentCountry(getCountry("Mantarys"));
        getCountry("Mereem").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("Mereem").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("Mereem").addAdjacentCountry(getCountry("Lhaiar"));
        getCountry("Mereem").addAdjacentCountry(getCountry("Yunkai"));
        getCountry("Yunkai").addAdjacentCountry(getCountry("Lhaiar"));
        getCountry("Yunkai").addAdjacentCountry(getCountry("Mereem"));
        getCountry("Yunkai").addAdjacentCountry(getCountry("Asyapor"));
        getCountry("Yunkai").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("Asyapor").addAdjacentCountry(getCountry("Yunkai"));
        getCountry("Asyapor").addAdjacentCountry(getCountry("OldOnis"));
        getCountry("Asyapor").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("OldOnis").addAdjacentCountry(getCountry("Asyapor"));
        getCountry("OldOnis").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Yunkai"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Asyapor"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("OldOnis"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Lhaiar"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Oartn"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Sayasasmad"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("Samyrian"));
        getCountry("TheRedWaste").addAdjacentCountry(getCountry("EasternDothrakiSea"));

    }

    /**
     * Initialize adjacent counntries in Asia
     */
    private void linkJadeSeaCoastAdjacentCountries() {
        getCountry("Oartn").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("Oartn").addAdjacentCountry(getCountry("Sayasasmad"));
        getCountry("Sayasasmad").addAdjacentCountry(getCountry("Oartn"));
        getCountry("Sayasasmad").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("Sayasasmad").addAdjacentCountry(getCountry("Samyrian"));
        getCountry("Samyrian").addAdjacentCountry(getCountry("Sayasasmad"));
        getCountry("Samyrian").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("Samyrian").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("Samyrian").addAdjacentCountry(getCountry("VassDothrak"));
        getCountry("Samyrian").addAdjacentCountry(getCountry("NorthernJadeSea"));
        getCountry("NorthernJadeSea").addAdjacentCountry(getCountry("Samyrian"));
        getCountry("NorthernJadeSea").addAdjacentCountry(getCountry("VassDothrak"));
        getCountry("NorthernJadeSea").addAdjacentCountry(getCountry("TheFootPrint"));

    }

    /**
     * Initialize adjacent countries in Australia
     */
    private void linkTheDothrakiSeaAdjacentCountries() {
        getCountry("TheFootPrint").addAdjacentCountry(getCountry("NorthernJadeSea"));
        getCountry("TheFootPrint").addAdjacentCountry(getCountry("VassDothrak"));
        getCountry("TheFootPrint").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("VassDothrak").addAdjacentCountry(getCountry("NorthernJadeSea"));
        getCountry("VassDothrak").addAdjacentCountry(getCountry("Samyrian"));
        getCountry("VassDothrak").addAdjacentCountry(getCountry("TheFootPrint"));
        getCountry("VassDothrak").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("Mereem"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("Lhaiar"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("Samyrian"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("VassDothrak"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("TheFootPrint"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("NorthernDothrakiSea"));
        getCountry("EasternDothrakiSea").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("Lhaiar").addAdjacentCountry(getCountry("Mereem"));
        getCountry("Lhaiar").addAdjacentCountry(getCountry("Yunkai"));
        getCountry("Lhaiar").addAdjacentCountry(getCountry("TheRedWaste"));
        getCountry("Lhaiar").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("NorthernDothrakiSea").addAdjacentCountry(getCountry("Sarnor"));
        getCountry("NorthernDothrakiSea").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("NorthernDothrakiSea").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("LowerRnoyne"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("Mantarys"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("Mereem"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("EasternDothrakiSea"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("NorthernDothrakiSea"));
        getCountry("WesternDothrakiSea").addAdjacentCountry(getCountry("Sarnor"));
        getCountry("Sarnor").addAdjacentCountry(getCountry("ForestOfGonor"));
        getCountry("Sarnor").addAdjacentCountry(getCountry("WesternDothrakiSea"));
        getCountry("Sarnor").addAdjacentCountry(getCountry("NorthernDothrakiSea"));

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
