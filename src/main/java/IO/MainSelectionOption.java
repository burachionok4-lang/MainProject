package IO;

public enum MainSelectionOption {
    NONE(0),
    FILL_AND_SORT(1),
    EXIT(2);

    private final int code;

    MainSelectionOption(int code) {
        if(ordinal() != code)
        {
            throw new IllegalArgumentException("Every constant has to be ordinal");
        }

        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MainSelectionOption fromInt(int code)
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

