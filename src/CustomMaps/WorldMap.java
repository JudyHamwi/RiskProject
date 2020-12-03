package CustomMaps;


import RiskModel.Board;
import RiskModel.Continent;
import RiskModel.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorldMap extends Board {
    public static final String WORLD_MAP_XML = "world_map.xml";

    public WorldMap() {
        super();
        createMap();

    }

    public void createMap() {
        //  create the Continents in the RISKModel.Game
        Continent NorthAmerica = new Continent("NorthAmerica", 5);
        Continent SouthAmerica = new Continent("SouthAmerica", 2);
        Continent Europe = new Continent("Europe", 5);
        Continent Africa = new Continent("Africa", 3);
        Continent Asia = new Continent("Asia", 7);
        Continent Australia = new Continent("Australia", 2);

        //add continents to board
        addContinent(NorthAmerica);
        addContinent(Europe);
        addContinent(Asia);
        addContinent(SouthAmerica);
        addContinent(Africa);
        addContinent(Australia);

        //create the countries in North America
        createContinentNorthAmerica(NorthAmerica);
        //create the countries in South America
        createContinentSouthAmerica(SouthAmerica);
        //create the countries in Europe
        createContinentEurope(Europe);
        //create the countries in Africa
        createContinentAfrica(Africa);
        //create the countries in Asia
        createContinentAsia(Asia);
        //create the countries in Australia
        createContinentAustralia(Australia);

        // add adjacent countries in NorthAmerica
        addNorthAmericaAdjacentCountries(NorthAmerica);
        // add adjacent countries in SouthAmerica
        addSouthAmericaAdjacentCountries(SouthAmerica);
        // add adjacent countries in Europe
        addEuropeAdjacentCountries(Europe);
        // add adjacent countries in Africa
        addAfricaAdjacentCountries(Africa);
        // add adjacent countries in Asia
        addAsiaAdjacentCountries(Asia);
        // add adjacent countries in Australia
        addAustraliaAdjacentCountries(Australia);

    }

    /**
     * create North America
     *
     * @param NorthAmerica continent
     */
    public void createContinentNorthAmerica(Continent NorthAmerica) {
        Country Alaska = new Country("Alaska");
        this.addCountryToBoard(Alaska);
        Country Alberta = new Country("Alberta");
        this.addCountryToBoard(Alberta);
        Country CentralAmerica = new Country("CentralAmerica");
        this.addCountryToBoard(CentralAmerica);
        Country EasternUnitedStates = new Country("EasternUnitedStates");
        this.addCountryToBoard(EasternUnitedStates);
        Country Greenland = new Country("Greenland");
        this.addCountryToBoard(Greenland);
        Country NorthwestTerritory = new Country("NorthwestTerritory");
        this.addCountryToBoard(NorthwestTerritory);
        Country Ontario = new Country("Ontario");
        this.addCountryToBoard(Ontario);
        Country Quebec = new Country("Quebec");
        this.addCountryToBoard(Quebec);
        Country WesternUnitedStates = new Country("WesternUnitedStates");
        this.addCountryToBoard(WesternUnitedStates);
        NorthAmerica.addCountry(Alaska);
        NorthAmerica.addCountry(Alberta);
        NorthAmerica.addCountry(CentralAmerica);
        NorthAmerica.addCountry(EasternUnitedStates);
        NorthAmerica.addCountry(Greenland);
        NorthAmerica.addCountry(NorthwestTerritory);
        NorthAmerica.addCountry(Ontario);
        NorthAmerica.addCountry(Quebec);
        NorthAmerica.addCountry(WesternUnitedStates);


    }

    /**
     * create South America
     *
     * @param SouthAmerica continent
     */
    public void createContinentSouthAmerica(Continent SouthAmerica) {
        Country Argentina = new Country("Argentina");
        this.addCountryToBoard(Argentina);
        Country Brazil = new Country("Brazil");
        this.addCountryToBoard(Brazil);
        Country Peru = new Country("Peru");
        this.addCountryToBoard(Peru);
        Country Venezuela = new Country("Venezuela");
        this.addCountryToBoard(Venezuela);
        SouthAmerica.addCountry(Argentina);
        SouthAmerica.addCountry(Brazil);
        SouthAmerica.addCountry(Peru);
        SouthAmerica.addCountry(Venezuela);


    }

    /**
     * create Europe
     *
     * @param Europe continent
     */
    public void createContinentEurope(Continent Europe) {
        Country GreatBritain = new Country("GreatBritain");
        this.addCountryToBoard(GreatBritain);
        Country Iceland = new Country("Iceland");
        this.addCountryToBoard(Iceland);
        Country NorthernEurope = new Country("NorthernEurope");
        this.addCountryToBoard(NorthernEurope);
        Country Scandinavia = new Country("Scandinavia");
        this.addCountryToBoard(Scandinavia);
        Country SouthernEurope = new Country("SouthernEurope");
        this.addCountryToBoard(SouthernEurope);
        Country Ukraine = new Country("Ukraine");
        this.addCountryToBoard(Ukraine);
        Country WesternEurope = new Country("WesternEurope");
        this.addCountryToBoard(WesternEurope);
        Europe.addCountry(GreatBritain);
        Europe.addCountry(Iceland);
        Europe.addCountry(NorthernEurope);
        Europe.addCountry(Scandinavia);
        Europe.addCountry(SouthernEurope);
        Europe.addCountry(Ukraine);
        Europe.addCountry(WesternEurope);

    }

    /**
     * create Africa
     *
     * @param Africa continent
     */
    public void createContinentAfrica(Continent Africa) {
        Country Congo = new Country("Congo");
        this.addCountryToBoard(Congo);
        Country EastAfrica = new Country("EastAfrica");
        this.addCountryToBoard(EastAfrica);
        Country Egypt = new Country("Egypt");
        this.addCountryToBoard(Egypt);
        Country Madagascar = new Country("Madagascar");
        this.addCountryToBoard(Madagascar);
        Country NorthAfrica = new Country("NorthAfrica");
        this.addCountryToBoard(NorthAfrica);
        Country SouthAfrica = new Country("SouthAfrica");
        this.addCountryToBoard(SouthAfrica);
        Africa.addCountry(Congo);
        Africa.addCountry(EastAfrica);
        Africa.addCountry(Egypt);
        Africa.addCountry(Madagascar);
        Africa.addCountry(NorthAfrica);
        Africa.addCountry(SouthAfrica);

    }

    /**
     * create Asia
     *
     * @param Asia continent
     */
    public void createContinentAsia(Continent Asia) {
        Country Afghanistan = new Country("Afghanistan");
        this.addCountryToBoard(Afghanistan);
        Country China = new Country("China");
        this.addCountryToBoard(China);
        Country India = new Country("India");
        this.addCountryToBoard(India);
        Country Irkutsk = new Country("Irkutsk");
        this.addCountryToBoard(Irkutsk);
        Country Japan = new Country("Japan");
        this.addCountryToBoard(Japan);
        Country Kamchatka = new Country("Kamchatka");
        this.addCountryToBoard(Kamchatka);
        Country MiddleEast = new Country("MiddleEast");
        this.addCountryToBoard(MiddleEast);
        Country Mongolia = new Country("Mongolia");
        this.addCountryToBoard(Mongolia);
        Country Siam = new Country("Siam");
        this.addCountryToBoard(Siam);
        Country Siberia = new Country("Siberia");
        this.addCountryToBoard(Siberia);
        Country Ural = new Country("Ural");
        this.addCountryToBoard(Ural);
        Country Yakutsk = new Country("Yakutsk");
        this.addCountryToBoard(Yakutsk);
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

    }

    /**
     * create Australia
     *
     * @param Australia continent
     */
    public void createContinentAustralia(Continent Australia) {
        Country EasternAustralia = new Country("EasternAustralia");
        this.addCountryToBoard(EasternAustralia);
        Country Indonesia = new Country("Indonesia");
        this.addCountryToBoard(Indonesia);
        Country NewGuinea = new Country("NewGuinea");
        this.addCountryToBoard(NewGuinea);
        Country WesternAustralia = new Country("WesternAustralia");
        this.addCountryToBoard(WesternAustralia);
        Australia.addCountry(EasternAustralia);
        Australia.addCountry(Indonesia);
        Australia.addCountry(NewGuinea);
        Australia.addCountry(WesternAustralia);
    }

    /**
     * Initialize adjacent counntries in North America
     *
     * @param NorthAmerica
     */
    public void addNorthAmericaAdjacentCountries(Continent NorthAmerica) {
        getCountry("Alaska").setAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Alaska").setAdjacentCountry(getCountry("Alberta"));
        getCountry("Alaska").setAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Alberta").setAdjacentCountry(getCountry("Alaska"));
        getCountry("Alberta").setAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Alberta").setAdjacentCountry(getCountry("Ontario"));
        getCountry("Alberta").setAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("CentralAmerica").setAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("CentralAmerica").setAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("CentralAmerica").setAdjacentCountry(getCountry("Venezuela"));
        getCountry("EasternUnitedStates").setAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("EasternUnitedStates").setAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("EasternUnitedStates").setAdjacentCountry(getCountry("Ontario"));
        getCountry("EasternUnitedStates").setAdjacentCountry(getCountry("Quebec"));
        getCountry("Greenland").setAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Greenland").setAdjacentCountry(getCountry("Ontario"));
        getCountry("Greenland").setAdjacentCountry(getCountry("Quebec"));
        getCountry("Greenland").setAdjacentCountry(getCountry("Iceland"));
        getCountry("NorthwestTerritory").setAdjacentCountry(getCountry("Alaska"));
        getCountry("NorthwestTerritory").setAdjacentCountry(getCountry("Alberta"));
        getCountry("NorthwestTerritory").setAdjacentCountry(getCountry("Greenland"));
        getCountry("NorthwestTerritory").setAdjacentCountry(getCountry("Ontario"));
        getCountry("Ontario").setAdjacentCountry(getCountry("WesternUnitedStates"));
        getCountry("Ontario").setAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("Ontario").setAdjacentCountry(getCountry("Greenland"));
        getCountry("Ontario").setAdjacentCountry(getCountry("Quebec"));
        getCountry("Ontario").setAdjacentCountry(getCountry("NorthwestTerritory"));
        getCountry("Ontario").setAdjacentCountry(getCountry("Alberta"));
        getCountry("Quebec").setAdjacentCountry(getCountry("Greenland"));
        getCountry("Quebec").setAdjacentCountry(getCountry("Ontario"));
        getCountry("Quebec").setAdjacentCountry(getCountry("EasternUnitedStates"));
        getCountry("WesternUnitedStates").setAdjacentCountry(getCountry("Alberta"));
        getCountry("WesternUnitedStates").setAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("WesternUnitedStates").setAdjacentCountry(getCountry("EasternUnitedStates"));

    }

    /**
     * Initialize adjacent counntries in South America
     *
     * @param SouthAmerica
     */
    public void addSouthAmericaAdjacentCountries(Continent SouthAmerica) {
        getCountry("Argentina").setAdjacentCountry(getCountry("Peru"));
        getCountry("Argentina").setAdjacentCountry(getCountry("Brazil"));
        getCountry("Brazil").setAdjacentCountry(getCountry("Argentina"));
        getCountry("Brazil").setAdjacentCountry(getCountry("Peru"));
        getCountry("Brazil").setAdjacentCountry(getCountry("Venezuela"));
        getCountry("Brazil").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("Peru").setAdjacentCountry(getCountry("Argentina"));
        getCountry("Peru").setAdjacentCountry(getCountry("Brazil"));
        getCountry("Peru").setAdjacentCountry(getCountry("Venezuela"));
        getCountry("Venezuela").setAdjacentCountry(getCountry("CentralAmerica"));
        getCountry("Venezuela").setAdjacentCountry(getCountry("Peru"));
        getCountry("Venezuela").setAdjacentCountry(getCountry("Brazil"));

    }

    /**
     * Initialize adjacent counntries in Europe
     *
     * @param Europe
     */
    public void addEuropeAdjacentCountries(Continent Europe) {
        getCountry("GreatBritain").setAdjacentCountry(getCountry("Iceland"));
        getCountry("GreatBritain").setAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("GreatBritain").setAdjacentCountry(getCountry("Scandinavia"));
        getCountry("GreatBritain").setAdjacentCountry(getCountry("WesternEurope"));
        getCountry("Iceland").setAdjacentCountry(getCountry("Greenland"));
        getCountry("Iceland").setAdjacentCountry(getCountry("GreatBritain"));
        getCountry("Iceland").setAdjacentCountry(getCountry("Scandinavia"));
        getCountry("NorthernEurope").setAdjacentCountry(getCountry("GreatBritain"));
        getCountry("NorthernEurope").setAdjacentCountry(getCountry("Scandinavia"));
        getCountry("NorthernEurope").setAdjacentCountry(getCountry("WesternEurope"));
        getCountry("NorthernEurope").setAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("NorthernEurope").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("Scandinavia").setAdjacentCountry(getCountry("Iceland"));
        getCountry("Scandinavia").setAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("Scandinavia").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("Egypt"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("WesternEurope"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("SouthernEurope").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("Scandinavia"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Ukraine").setAdjacentCountry(getCountry("Ural"));
        getCountry("WesternEurope").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("WesternEurope").setAdjacentCountry(getCountry("GreatBritain"));
        getCountry("WesternEurope").setAdjacentCountry(getCountry("NorthernEurope"));
        getCountry("WesternEurope").setAdjacentCountry(getCountry("SouthernEurope"));

    }

    /**
     * Initialize adjacent counntries in Africa
     *
     * @param Africa
     */
    public void addAfricaAdjacentCountries(Continent Africa) {
        getCountry("Congo").setAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("Congo").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Congo").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("Congo"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("Egypt"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("Madagascar"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("EastAfrica").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Egypt").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Egypt").setAdjacentCountry(getCountry("NorthAfrica"));
        getCountry("Egypt").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Egypt").setAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("Madagascar").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("Madagascar").setAdjacentCountry(getCountry("SouthAfrica"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("Congo"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("Egypt"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("Brazil"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("WesternEurope"));
        getCountry("NorthAfrica").setAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("SouthAfrica").setAdjacentCountry(getCountry("Congo"));
        getCountry("SouthAfrica").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("SouthAfrica").setAdjacentCountry(getCountry("Madagascar"));

    }

    /**
     * Initialize adjacent counntries in Asia
     *
     * @param Asia
     */
    public void addAsiaAdjacentCountries(Continent Asia) {
        getCountry("Afghanistan").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("Afghanistan").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("Afghanistan").setAdjacentCountry(getCountry("Ural"));
        getCountry("Afghanistan").setAdjacentCountry(getCountry("India"));
        getCountry("Afghanistan").setAdjacentCountry(getCountry("China"));
        getCountry("China").setAdjacentCountry(getCountry("Siam"));
        getCountry("China").setAdjacentCountry(getCountry("India"));
        getCountry("China").setAdjacentCountry(getCountry("Afghanistan"));
        getCountry("China").setAdjacentCountry(getCountry("Ural"));
        getCountry("China").setAdjacentCountry(getCountry("Siberia"));
        getCountry("China").setAdjacentCountry(getCountry("Mongolia"));
        getCountry("India").setAdjacentCountry(getCountry("Siam"));
        getCountry("India").setAdjacentCountry(getCountry("MiddleEast"));
        getCountry("India").setAdjacentCountry(getCountry("China"));
        getCountry("India").setAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Irkutsk").setAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Irkutsk").setAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Irkutsk").setAdjacentCountry(getCountry("Mongolia"));
        getCountry("Irkutsk").setAdjacentCountry(getCountry("Siberia"));
        getCountry("Japan").setAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Japan").setAdjacentCountry(getCountry("Mongolia"));
        getCountry("Kamchatka").setAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Kamchatka").setAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Kamchatka").setAdjacentCountry(getCountry("Mongolia"));
        getCountry("Kamchatka").setAdjacentCountry(getCountry("Japan"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("EastAfrica"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("Egypt"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("SouthernEurope"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("Afghanistan"));
        getCountry("MiddleEast").setAdjacentCountry(getCountry("India"));
        getCountry("Mongolia").setAdjacentCountry(getCountry("Siberia"));
        getCountry("Mongolia").setAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Mongolia").setAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Mongolia").setAdjacentCountry(getCountry("Japan"));
        getCountry("Mongolia").setAdjacentCountry(getCountry("China"));
        getCountry("Siam").setAdjacentCountry(getCountry("China"));
        getCountry("Siam").setAdjacentCountry(getCountry("India"));
        getCountry("Siam").setAdjacentCountry(getCountry("Indonesia"));
        getCountry("Siberia").setAdjacentCountry(getCountry("Yakutsk"));
        getCountry("Siberia").setAdjacentCountry(getCountry("Irkutsk"));
        getCountry("Siberia").setAdjacentCountry(getCountry("Mongolia"));
        getCountry("Siberia").setAdjacentCountry(getCountry("China"));
        getCountry("Siberia").setAdjacentCountry(getCountry("Ural"));
        getCountry("Ural").setAdjacentCountry(getCountry("Ukraine"));
        getCountry("Ural").setAdjacentCountry(getCountry("Siberia"));
        getCountry("Ural").setAdjacentCountry(getCountry("China"));
        getCountry("Ural").setAdjacentCountry(getCountry("Afghanistan"));
        getCountry("Yakutsk").setAdjacentCountry(getCountry("Siberia"));
        getCountry("Yakutsk").setAdjacentCountry(getCountry("Kamchatka"));
        getCountry("Yakutsk").setAdjacentCountry(getCountry("Irkutsk"));

    }

    /**
     * Initialize adjacent counntries in Australia
     *
     * @param Australia
     */
    public void addAustraliaAdjacentCountries(Continent Australia) {
        getCountry("EasternAustralia").setAdjacentCountry(getCountry("WesternAustralia"));
        getCountry("EasternAustralia").setAdjacentCountry(getCountry("NewGuinea"));
        getCountry("Indonesia").setAdjacentCountry(getCountry("Siam"));
        getCountry("Indonesia").setAdjacentCountry(getCountry("NewGuinea"));
        getCountry("NewGuinea").setAdjacentCountry(getCountry("EasternAustralia"));
        getCountry("NewGuinea").setAdjacentCountry(getCountry("Indonesia"));
        getCountry("NewGuinea").setAdjacentCountry(getCountry("WesternAustralia"));
        getCountry("WesternAustralia").setAdjacentCountry(getCountry("EasternAustralia"));
        getCountry("WesternAustralia").setAdjacentCountry(getCountry("NewGuinea"));
    }

    public void exportWorldMap() throws IOException {
        FileWriter fileOutputStream = new FileWriter(WORLD_MAP_XML);
        fileOutputStream.write(toXML());
        fileOutputStream.close();
    }

    public WorldMap importWorldMap() throws ParserConfigurationException, SAXException, IOException {
        File worldMapFile = new File(WORLD_MAP_XML);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        WorldMap newWorldMap = new WorldMap();

        DefaultHandler dh = new DefaultHandler() {
            private boolean board = false;
            private boolean continent = false;
            private boolean country = false;
            private boolean adjacent = false;

            public void startElement(String u, String ln, String qName, Attributes a) {
                System.out.println("Start element: " + qName);

                if (qName.equals("board")) {
                    board = true;
                }

                if (qName.equals("continent")) {
                    continent = true;
                    System.out.println("Continent Attribute: " + a.getValue(0));
                    newWorldMap.addContinent(new Continent(a.getValue(0)));
                }

                if (qName.equals("country")) {
                    continent = true;
                    System.out.println("Country Attribute: " + a.getValue(0));

                }

                if (qName.equals("adjacent")) {
                    adjacent = true;
                }
            }

            public void endElement(String uri, String localName, String qName) {
                System.out.println("End element: " + qName);
            }

            public void characters(char[] ch, int start, int length) {
                String value = new String(ch, start, length);
                System.out.println("Value: " + value);
            }

        };

        s.parse(worldMapFile, dh);
        return newWorldMap;
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        WorldMap worldMap = new WorldMap();
        worldMap.exportWorldMap();
        worldMap.importWorldMap();
    }
}
