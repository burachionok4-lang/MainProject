package IO;

public enum FillSelectionOption {
    NONE(0),
    RANDOM(1),
    MANUAL_INPUT(2),
    FROM_FILE(3);

    private final int code;

    FillSelectionOption(int code) {
        if(ordinal() != code)
        {
            throw new IllegalArgumentException("Every constant has to be ordinal");
        }

        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static FillSelectionOption fromInt(int code)
    {
        for(var i : values())
        {
            if(i.code == code)
            {
                return i;
            }
        }

        //if nothing found
        throw new RuntimeException("Didn't find suitable constant");
    }
}
