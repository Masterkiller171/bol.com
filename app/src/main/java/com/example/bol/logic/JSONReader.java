package com.example.bol.logic;

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

                product.setId(Long.parseLong(object.getString("id")));
                product.setEAN(Long.parseLong(object.getString("ean")));
                product.setGpc(object.getString("gpc"));
                product.setTitle(object.getString("title"));
                product.setSubTitle(object.getString("subtitle"));
                product.setSummary(object.getString("summary"));
                product.setRating(Integer.parseInt(object.getString("rating")));
                product.setShortDescription(object.getString("shortDescription"));
                product.setLongDescrition(object.getString("longDescription"));

                JSONArray imgArr = object.getJSONArray("images");
                for (int i1 = 0; i1 < imgArr.length(); i1++) {
                    String size = imgArr.getJSONObject(i1).getString("key");

                    if (size.equals("M")) {
                        product.setImageUrlSmall(new URL(imgArr.getJSONObject(i1).getString("url")));
                    }

                    if (size.equals("XL")){
                        product.setImageUrlBig(new URL(imgArr.getJSONObject(i1).getString("url")));
                    }
                }

                JSONObject offerData = object.getJSONObject("offerData");
                JSONArray  offers = offerData.getJSONArray("offers");

                for (int i1 = 0; i1 < offers.length(); i1++) {
                   product.setCurrentPrice(offers.getJSONObject(i1).getDouble("price"));
                   product.setNormalPrice(offers.getJSONObject(i1).getDouble("listPrice"));
                }

                productArr.add(product);
                product = new Product();
            }
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }

        return productArr;
    }
}
