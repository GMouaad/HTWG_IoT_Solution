package de.htwg.iotstack.MqttGateway.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * This class is a utility class to convert to or from a JSON String accordingly
 *
 * @author Mgsair
 */
public class Converter {

    /**
     * This method convert any Object (POJO: Plain Old Java Object) to a formatted JSON
     * @param T The object to be converted
     * @return the resulting JSON
     */
    public static String pojo2json(Object T) {
        ObjectMapper mapper = new ObjectMapper();
        String S = null;
        try {
            S = mapper.writeValueAsString(T);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return S;
    }

    /**
     * This method convert a formatted JSON  to an Object (POJO: Plain Old Java Object)
     * @param mString The JSON String to be converted
     * @param T The Object to be converted to. This is needed to know the Class definition of the Object
     * @return The resulting Object
     */
    public static Object json2pojo(String mString, Object T) {

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Create an Instance from the corresponding Class
        try {
            T = T.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Map the object and store the data in its attributes accordingly
        try {
            T = objectMapper.readValue(mString.getBytes(), T.getClass());
            System.out.println("Converted Object from Class " + T.getClass().getName() + ":" + T);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return T;
    }
}
