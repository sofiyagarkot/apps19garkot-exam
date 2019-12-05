package json;
import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    Map<String, Json> map = new HashMap<String,Json>();
    String jsonString = "";

    public JsonObject(JsonPair... jsonPairs) {
        for (int i = 0; i < jsonPairs.length; i++) {
            JsonPair jp = jsonPairs[i];
            map.put(jp.key, jp.value);
            if (i < jsonPairs.length-1) {
                jsonString += jp.key + ": " + jp.value.toJson() + ", ";
            } else {
                jsonString += jp.key + ": " + jp.value.toJson();
            }
        }
    }

    @Override
    public String toJson() {
        String result = "{"+ jsonString + "}";
        return result;
    }


    public void add(JsonPair jsonPair) {
        map.put(jsonPair.key, jsonPair.value);
        if (jsonString != "") {
            jsonString += ", " + jsonPair.key + ": " + jsonPair.value.toJson();
        } else {
            jsonString += jsonPair.key + ": " + jsonPair.value.toJson();
        }
    }

    public boolean contains(String name) {
        Set set = map.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {

            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getKey().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Json find(String name) throws Exception {
//        if (!(this.contains(name))) {
//            throw new Exception();
//        }

        Json result = null;
        Set set = map.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getKey().equals(name)) {
                result = (Json) entry.getValue();
            }

        }
        return result;
    }

    public JsonObject projection(String... names) {
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        JsonObject newObject = new JsonObject();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            for (String name : names) {
                if (name.equals(entry.getKey())) {
                    newObject.add( new JsonPair((String) entry.getKey(), (Json) entry.getValue()));
                }
            }
        }
        return newObject;
    }
}
