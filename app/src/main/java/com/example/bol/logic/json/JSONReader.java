package com.example.bol.logic.json;

import com.example.bol.domain.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONReader {

    public static ArrayList<Product> procesJSON(String JSONString){
        Product product = new Product();
        ArrayList<Product> productArr = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(JSONString);
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);

                JSONObject offerData = object.getJSONObject("offerData");
                JSONArray  offers = offerData.getJSONArray("offers");

                productArr.add(JSONReader.processOffer(offers, JSONReader.processData(object, product)));
                product = new Product();
            }
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }

        return productArr;
    }

    private static Product processData(JSONObject object, Product product) throws JSONException, MalformedURLException {
        product.setId(Long.parseLong(object.getString("id")));
        product.setTitle(object.getString("title"));
        product.setSummary(object.getString("summary"));
        product.setRating(Integer.parseInt(object.getString("rating")));
        product.setShortDescription(object.getString("shortDescription"));

        JSONArray imgArr = object.getJSONArray("images");
        for (int i1 = 0; i1 < imgArr.length(); i1++) {
            String size = imgArr.getJSONObject(i1).getString("key");

            if (size.equals("XL")) {
                product.setImageUrlSmall(imgArr.getJSONObject(i1).getString("url"));
            }

            if (size.equals("XXL")){
                product.setImageUrlBig(imgArr.getJSONObject(i1).getString("url"));
            }
        }

        return product;
    }

    private static Product processOffer(JSONArray offers, Product product) throws JSONException {
        for (int i1 = 0; i1 < offers.length(); i1++) {
//            product.setCurrentPrice(offers.getJSONObject(i1).getDouble("listPrice"));
            product.setNormalPrice(offers.getJSONObject(i1).getDouble("price"));
        }
        return product;
    }
}
