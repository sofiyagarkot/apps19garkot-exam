package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {

    private final Boolean boolvalue;

    public JsonBoolean(Boolean bool) {
        this.boolvalue = bool;
    }

    @Override
    public String toJson() {
        return this.boolvalue.toString();
    }
}
