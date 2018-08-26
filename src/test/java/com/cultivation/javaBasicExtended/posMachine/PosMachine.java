package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})

public class PosMachine {
    private List<HashMap> itemList = new ArrayList();

    public void readDataSource(Reader reader) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        String dataSource = readerToString(reader);
        stringToProductMap(dataSource);
    }

    private void stringToProductMap(String dataSource) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray(dataSource);


        for (int i = 0; i < jsonArray.length(); i++) {
            Map<String, Object> products = new HashMap<>();

            JSONObject object = jsonArray.getJSONObject(i);
            String id = object.getString("id");
            String name = object.getString("name");
            int price = object.getInt("price");
            int count = 0;

            products.put("id", id);
            products.put("name", name);
            products.put("price", price);
            products.put("count", 1);

            itemList.add((HashMap) products);
        }
    }

    private String readerToString(Reader reader) throws IOException {
        int intValueOfChar;
        StringBuilder targetString = new StringBuilder();
        while ((intValueOfChar = reader.read()) != -1) {
            targetString.append((char) intValueOfChar);
        }
        reader.close();
        return targetString.toString();
    }

    public String printReceipt(String barcodeContent) throws IOException {
        StringBuilder receiptString = new StringBuilder();
        String splitLine = "------------------------------------------------------------";
        String line = System.lineSeparator();
        receiptString.append("Receipts").append(line).append(splitLine).append(line);
        String t = "%-32s%-11s%s";


        String template = "Receipts" + line +
                "------------------------------------------------------------" + line +

                "------------------------------------------------------------" + line +
                "Price: 0" + line;
        if (barcodeContent == null || barcodeContent.equals("[]")) {
            return template;
        }

        HashMap<Object, Object> map1 = getBarcodeMap(barcodeContent);

        int totalPrice = 0;

        for (Map.Entry<Object, Object> map : map1.entrySet()) {
            HashMap item = getItem((String) map.getKey());
            int price = (int) item.get("price");
            int count = (int) map.getValue();
            totalPrice += price * count;

            receiptString.append(String.format(t, item.get("name"), price, count)).append("\n");
        }

        receiptString.append(splitLine).append(line).append("Price: ").append(totalPrice).append(line);
        return receiptString.toString();

    }

    private HashMap<Object, Object> getBarcodeMap(String barcodeContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List idList = objectMapper.readValue(barcodeContent, List.class);

        LinkedHashMap<Object, Object> map1 = new LinkedHashMap<>();

        for (Object id : idList) {
            Integer count = Collections.frequency(idList, id);
            map1.put(id, count);
        }
        return map1;
    }

    private HashMap getItem(String barcode) {
        for (HashMap item : itemList) {
            if (barcode.equals(item.get("id"))) {
               return item;
            }
        }
        throw new IllegalStateException();
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}