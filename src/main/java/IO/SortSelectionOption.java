package IO;

public enum SortSelectionOption {
    BY_MODEL(1),
    BY_POWER(2),
    BY_YEAR(3),
    BY_ALL(4);

    private final int code;

    SortSelectionOption(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SortSelectionOption fromInt(int code) {
        for (SortSelectionOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        throw new IllegalArgumentException("Не найдена опция сортировки для кода: " + code);
    }
}