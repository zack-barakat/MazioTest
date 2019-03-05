package com.android.maziotest.testUtils;

import com.android.maziotest.data.model.Pizza;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class TestDataGenerator {
    private static Gson mGson = new Gson();

    public static ArrayList<Pizza> getPizzas() {
        return mGson.fromJson("[\n" +
                "    {\n" +
                "        \"name\": \"Mozzarella\",\n" +
                "        \"price\": 10\n" +
                "    }, {\n" +
                "        \"name\": \"Pepperoni\",\n" +
                "        \"price\": 12\n" +
                "    }, {\n" +
                "        \"name\": \"Vegetarian\",\n" +
                "        \"price\": 9.5\n" +
                "    }, {\n" +
                "        \"name\": \"Super cheese\",\n" +
                "        \"price\": 11\n" +
                "    }\n" +
                "]", new TypeToken<ArrayList<Pizza>>() {
        }.getType());
    }
}
