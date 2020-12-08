package risk.model.board;

import risk.model.GameConstants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AvatarBoardFactory implements BoardFactory {
    private final Map<String, Country> avatarCountryMap = new HashMap<>();

    @Override
    public Board build() {
        final Continent FireNation = createFireNation();
        final Continent WaterTribe = createWaterTribe();
        final Continent AirNation = createAirNation();
        final Continent EarthKingdom = createEarthKingdom();

        linkAirNationCountries();
        linkEarthKingdomCountries();
        linkFireNationCountries();
        linkWaterTribeCountries();

        final List<Continent> avatarContinents = new LinkedList<>();
        avatarContinents.add(FireNation);
        avatarContinents.add(WaterTribe);
        avatarContinents.add(AirNation);
        avatarContinents.add(EarthKingdom);

        return new Board(avatarContinents, new GameConstants());
    }

    private Continent createFireNation() {
        Country Capitol = createCountry("Capitol");
        Country EmberIsland = createCountry("EmberIsland");
        Country BlackCliffs = createCountry("BlackCliffs");
        Country FountainCity = createCountry("FountainCity");
        Country CrescentIsland = createCountry("CrescentIsland");

        final Continent FireNation = new Continent("FireNation", 5); // Edit the number of armies
        FireNation.addCountry(Capitol);
        FireNation.addCountry(EmberIsland);
        FireNation.addCountry(BlackCliffs);
        FireNation.addCountry(FountainCity);
        FireNation.addCountry(CrescentIsland);

        return FireNation;
    }

    private Continent createWaterTribe() {
        Country NorthernTribe = createCountry("NorthernTribe");
        Country SouthernTribe = createCountry("SouthernTribe");
        Country SouthernIceField = createCountry("SouthernIceField");
        Country NorthernIceField = createCountry("NorthernIceField");
        Country SouthPole = createCountry("SouthPole");
        Country NorthPole = createCountry("NorthPole");

        final Continent WaterTribe = new Continent("WaterTribe", 6);
        WaterTribe.addCountry(NorthernTribe);
        WaterTribe.addCountry(SouthernTribe);
        WaterTribe.addCountry(SouthernIceField);
        WaterTribe.addCountry(NorthernIceField);
        WaterTribe.addCountry(SouthPole);
        WaterTribe.addCountry(NorthPole);

        return WaterTribe;
    }

    private Continent createAirNation() {
        Country EasternAirTemple = createCountry("EasternAirTemple");
        Country PatolaMountains = createCountry("PatolaMountains");
        Country WhaleTailIsland = createCountry("WhaleTailIsland");
        Country SouthernAirTemple = createCountry("SouthernAirTemple");
        Country NorthernAirTemple = createCountry("NorthernAirTemple");
        Country WesternAirTemple = createCountry("WesternAirTemple");
        Country LairOfMasters = createCountry("LairOfMasters");

        final Continent AirNation = new Continent("AirNation", 4);
        AirNation.addCountry(EasternAirTemple);
        AirNation.addCountry(PatolaMountains);
        AirNation.addCountry(WhaleTailIsland);
        AirNation.addCountry(SouthernAirTemple);
        AirNation.addCountry(WesternAirTemple);
        AirNation.addCountry(LairOfMasters);
        AirNation.addCountry(NorthernAirTemple);

        return AirNation;

    }

    private Continent createEarthKingdom() {
        Country BaSingSe = createCountry("BaSingSe");
        Country FoggySwamp = createCountry("FoggySwamp");
        Country Omashu = createCountry("Omashu");
        Country MountainPass = createCountry("MountainPass");
        Country EastChameleonBay = createCountry("EastChameleonBay");
        Country NortherPlains = createCountry("NorthernPlains");
        Country WestChameleonBay = createCountry("WestChameleonBay");
        Country SiWongDesert = createCountry("SiWongDesert");

        final Continent EarthKingdom = new Continent("EarthKingdom", 7);
        EarthKingdom.addCountry(BaSingSe);
        EarthKingdom.addCountry(FoggySwamp);
        EarthKingdom.addCountry(Omashu);
        EarthKingdom.addCountry(MountainPass);
        EarthKingdom.addCountry(EastChameleonBay);
        EarthKingdom.addCountry(NortherPlains);
        EarthKingdom.addCountry(WestChameleonBay);
        EarthKingdom.addCountry(SiWongDesert);

        return EarthKingdom;
    }

    private void linkFireNationCountries() {
        getCountry("Capitol").addAdjacentCountry(getCountry("EastChameleonBay"));
        getCountry("Capitol").addAdjacentCountry(getCountry("EmberIsland"));
        getCountry("Capitol").addAdjacentCountry(getCountry("BlackCliffs"));

        getCountry("BlackCliffs").addAdjacentCountry(getCountry("Capitol"));
        getCountry("BlackCliffs").addAdjacentCountry(getCountry("EmberIsland"));
        getCountry("BlackCliffs").addAdjacentCountry(getCountry("FountainCity"));

        getCountry("EmberIsland").addAdjacentCountry(getCountry("Capitol"));
        getCountry("EmberIsland").addAdjacentCountry(getCountry("BlackCliffs"));

        getCountry("FountainCity").addAdjacentCountry(getCountry("BlackCliffs"));
        getCountry("FountainCity").addAdjacentCountry(getCountry("CrescentIsland"));

        getCountry("CrescentIsland").addAdjacentCountry(getCountry("FountainCity"));
        getCountry("CrescentIsland").addAdjacentCountry(getCountry("Omashu"));

    }

    private void linkWaterTribeCountries() {
        getCountry("SouthernTribe").addAdjacentCountry(getCountry("SouthernIceField"));
        getCountry("SouthernTribe").addAdjacentCountry(getCountry("NorthernTribe"));
        getCountry("SouthernTribe").addAdjacentCountry(getCountry("WhaleTailIsland"));

        getCountry("SouthernIceField").addAdjacentCountry(getCountry("SouthernTribe"));
        getCountry("SouthernIceField").addAdjacentCountry(getCountry("SouthPole"));

        getCountry("SouthPole").addAdjacentCountry(getCountry("WhaleTailIsland"));
        getCountry("SouthPole").addAdjacentCountry(getCountry("SouthernIceField"));
        getCountry("SouthPole").addAdjacentCountry(getCountry("PatolaMountains"));

        getCountry("NorthernTribe").addAdjacentCountry(getCountry("NorthPole"));
        getCountry("NorthernTribe").addAdjacentCountry(getCountry("NorthernIceField"));
        getCountry("NorthernTribe").addAdjacentCountry(getCountry("WesternAirTemple"));
        getCountry("NorthernTribe").addAdjacentCountry(getCountry("SouthernTribe"));

        getCountry("NorthernIceField").addAdjacentCountry(getCountry("NorthernAirTemple"));
        getCountry("NorthernIceField").addAdjacentCountry(getCountry("NorthPole"));
        getCountry("NorthernIceField").addAdjacentCountry(getCountry("NorthernTribe"));

        getCountry("NorthPole").addAdjacentCountry(getCountry("NorthernTribe"));
        getCountry("NorthPole").addAdjacentCountry(getCountry("NorthernIceField"));
    }

    private void linkAirNationCountries() {
        getCountry("EasternAirTemple").addAdjacentCountry(getCountry("WestChameleonBay"));
        getCountry("EasternAirTemple").addAdjacentCountry(getCountry("EastChameleonBay"));
        getCountry("EasternAirTemple").addAdjacentCountry(getCountry("PatolaMountains"));

        getCountry("PatolaMountains").addAdjacentCountry(getCountry("FoggySwamp"));
        getCountry("PatolaMountains").addAdjacentCountry(getCountry("EasternAirTemple"));
        getCountry("PatolaMountains").addAdjacentCountry(getCountry("SouthPole"));

        getCountry("WhaleTailIsland").addAdjacentCountry(getCountry("SouthPole"));
        getCountry("WhaleTailIsland").addAdjacentCountry(getCountry("SouthernAirTemple"));
        getCountry("WhaleTailIsland").addAdjacentCountry(getCountry("SouthernTribe"));
        getCountry("WhaleTailIsland").addAdjacentCountry(getCountry("FoggySwamp"));

        getCountry("SouthernAirTemple").addAdjacentCountry(getCountry("WhaleTailIsland"));

        getCountry("NorthernAirTemple").addAdjacentCountry(getCountry("NorthernPlains"));
        getCountry("NorthernAirTemple").addAdjacentCountry(getCountry("NorthernIceField"));
        getCountry("NorthernAirTemple").addAdjacentCountry(getCountry("MountainPass"));

        getCountry("WesternAirTemple").addAdjacentCountry(getCountry("NorthernTribe"));
        getCountry("WesternAirTemple").addAdjacentCountry(getCountry("LairOfMasters"));

        getCountry("LairOfMasters").addAdjacentCountry(getCountry("WesternAirTemple"));
    }

    private void linkEarthKingdomCountries() {
        getCountry("BaSingSe").addAdjacentCountry(getCountry("NorthernPlains"));
        getCountry("BaSingSe").addAdjacentCountry(getCountry("MountainPass"));

        getCountry("NorthernPlains").addAdjacentCountry(getCountry("BaSingSe"));
        getCountry("NorthernPlains").addAdjacentCountry(getCountry("NorthernAirTemple"));
        getCountry("NorthernPlains").addAdjacentCountry(getCountry("EastChameleonBay"));
        getCountry("NorthernPlains").addAdjacentCountry(getCountry("MountainPass"));

        getCountry("MountainPass").addAdjacentCountry(getCountry("BaSingSe"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("NorthernPlains"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("NorthernAirTemple"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("EastChameleonBay"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("Omashu"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("SiWongDesert"));
        getCountry("MountainPass").addAdjacentCountry(getCountry("WestChameleonBay"));

        getCountry("EastChameleonBay").addAdjacentCountry(getCountry("NorthernPlains"));
        getCountry("EastChameleonBay").addAdjacentCountry(getCountry("MountainPass"));
        getCountry("EastChameleonBay").addAdjacentCountry(getCountry("EasternAirTemple"));
        getCountry("EastChameleonBay").addAdjacentCountry(getCountry("Capitol"));

        getCountry("Omashu").addAdjacentCountry(getCountry("MountainPass"));
        getCountry("Omashu").addAdjacentCountry(getCountry("SiWongDesert"));
        getCountry("Omashu").addAdjacentCountry(getCountry("FoggySwamp"));
        getCountry("Omashu").addAdjacentCountry(getCountry("CrescentIsland"));

        getCountry("SiWongDesert").addAdjacentCountry(getCountry("MountainPass"));
        getCountry("SiWongDesert").addAdjacentCountry(getCountry("WestChameleonBay"));
        getCountry("SiWongDesert").addAdjacentCountry(getCountry("Omashu"));
        getCountry("SiWongDesert").addAdjacentCountry(getCountry("FoggySwamp"));

        getCountry("FoggySwamp").addAdjacentCountry(getCountry("Omashu"));
        getCountry("FoggySwamp").addAdjacentCountry(getCountry("SiWongDesert"));
        getCountry("FoggySwamp").addAdjacentCountry(getCountry("WhaleTailIsland"));
        getCountry("FoggySwamp").addAdjacentCountry(getCountry("PatolaMountains"));

        getCountry("WestChameleonBay").addAdjacentCountry(getCountry("EasternAirTemple"));
        getCountry("WestChameleonBay").addAdjacentCountry(getCountry("MountainPass"));
        getCountry("WestChameleonBay").addAdjacentCountry(getCountry("SiWongDesert"));

    }

    private Country createCountry(final String name) {
        final Country country = new Country(name);
        avatarCountryMap.put(name, country);
        return country;
    }

    private Country getCountry(final String name) {
        return avatarCountryMap.get(name);
    }


}
