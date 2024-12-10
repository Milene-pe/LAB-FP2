public enum Kingdom {
    ENGLAND("England", "\u001B[33m"),
    FRANCE("France", "\u001B[34m"),
    CASTILE_ARGON("Castile-Aragon", "\u001B[31m"),
    MOORS("Moors", "\u001B[32m"),
    HOLY_ROMAN_EMPIRE("Holy Roman Empire", "\u001B[36m");

    private final String name;
    private final String color;

    Kingdom(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}