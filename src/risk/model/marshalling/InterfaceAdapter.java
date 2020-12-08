package risk.model.marshalling;

import com.google.gson.*;

import java.lang.reflect.Type;

public class  InterfaceAdapter <T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final String CLASSNAME = "__CLASSNAME__";
    private static final String DATA = "__DATA__";

    @Override
    public T deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        final String className = prim.getAsString();
        final Class<T> clazz = getObjectClass(className);

        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), clazz);
    }

    @Override
    public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }

    /******
     * Helper method to get the className of the object to be deserialized
     *****/
    @SuppressWarnings("unchecked")
    private Class<T> getObjectClass(final String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
