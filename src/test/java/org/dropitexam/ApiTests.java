package org.dropitexam;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseApiTestRunner{

    @Test
    public void createNewPet(){

        Map<String, Object> data = new HashMap<>();
        Map<Object, String> category = new HashMap<>();
        SortedSet<String> photoUrls = new TreeSet<>();
        SortedMap<Object, String> tags = new TreeMap<>();

        data.put("id", 1);

        category.put(1, "Category1");
        data.put("category", category);

        data.put("name", "Pet1");

        photoUrls.add("path1");
        data.put("photoUrls", photoUrls);

        tags.put(1,"Tag1");
        data.put("tags", tags);

        data.put("status", "available");

        APIResponse apiResponse = apiRequestContext.post(BASE_URL + "pet/addPet",
                RequestOptions.create()
                        .setData(data)
        );

        assertEquals(200,apiResponse.status());

        //Update the pet status to sold
        FormData form = FormData.create()
                .set("petId ", 1)
                .set("name", "Pet1")
                .set("status", "sold");

        apiResponse = apiRequestContext.post(BASE_URL + "pet/updatePetWithForm",
                RequestOptions.create()
                        .setForm(form)
        );

        assertEquals(200,apiResponse.status());
    }

    @Test
    public void findPetByStatusAvailable(){


        APIResponse apiResponse = apiRequestContext.get(BASE_URL + "pet/findPetsByStatus?status=available");

        assertEquals(200,apiResponse.status());
    }
}
