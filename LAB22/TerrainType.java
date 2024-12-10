
import java.util.List;
import java.util.Arrays;

public enum TerrainType {
    FOREST("Forest", Kingdom.ENGLAND, Kingdom.HOLY_ROMAN_EMPIRE),
    OPEN_FIELD("Open Field", Kingdom.FRANCE, Kingdom.HOLY_ROMAN_EMPIRE),
    MOUNTAIN("Mountain", Kingdom.CASTILE_ARGON),
    DESERT("Desert", Kingdom.MOORS),
    BEACH("Beach", Kingdom.HOLY_ROMAN_EMPIRE);

    private final String name;
    private final List<Kingdom> kingdomsWithBonus;

    TerrainType(String name, Kingdom... kingdomsWithBonus) {
        this.name = name;
        this.kingdomsWithBonus = Arrays.asList(kingdomsWithBonus);
    }

    public String getName() {
        return name;
    }

    public List<Kingdom> getKingdomsWithBonus() {
        return kingdomsWithBonus;
    }

    public boolean hasBonus(Kingdom kingdom) {
        return kingdomsWithBonus.contains(kingdom);
    }
}