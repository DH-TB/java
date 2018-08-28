package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})

public class PosMachine {
    private List<HashMap> productList = new ArrayList<>();

    public void readDataSource(Reader reader) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        String dataSource = convertReaderToString(reader);
        convertStringToProductList(dataSource);
    }

    private void convertStringToProductList(String dataSource) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<HashMap> dataList = objectMapper.readValue(dataSource, List.class);
        for(HashMap data : dataList){
            Map<String, Object> products = new HashMap<>();

            products.put("id", data.get("id"));
            products.put("name", data.get("name"));
            products.put("price", data.get("price"));

            productList.add((HashMap) products);
        }
    }

    private String convertReaderToString(Reader reader) throws IOException {
        int intValue;
        StringBuilder targetString = new StringBuilder();
        while ((intValue = reader.read()) != -1) {
            targetString.append((char) intValue);
        }
        reader.close();
        return targetString.toString();
    }

    public String printReceipt(String barcodeContent) throws IOException {
        if (barcodeContent == null) {
            barcodeContent = "[]";
        }

        final String splitLine = "------------------------------------------------------------";
        final String line = System.lineSeparator();
        StringBuilder receiptString = new StringBuilder();

        receiptString.append("Receipts").append(line).append(splitLine).append(line);
        int totalPrice = buildReceipt(barcodeContent, receiptString, line);
        receiptString.append(splitLine)
                .append(line)
                .append("Price: ")
                .append(totalPrice)
                .append(line);

        return receiptString.toString();
    }

    private int buildReceipt(String barcodeContent, StringBuilder receiptString, String line) throws IOException {
        String template = "%-32s%-11s%d";
        int totalPrice = 0;

        HashMap<Object, Object> barcodeMap = getBarcodeMap(barcodeContent);
        for (Map.Entry<Object, Object> map : barcodeMap.entrySet()) {
            HashMap item = getItem((String) map.getKey());

            int price = (int) item.get("price");
            int count = (int) map.getValue();
            totalPrice += price * count;

            receiptString.append(String.format(template, item.get("name"), price, count)).append(line);
        }

        return totalPrice;
    }

    private HashMap<Object, Object> getBarcodeMap(String barcodeContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List barcodeList = objectMapper.readValue(barcodeContent, List.class);

        LinkedHashMap<Object, Object> barcodeMap = new LinkedHashMap<>();

        for (Object barcode : barcodeList) {
            Integer count = Collections.frequency(barcodeList, barcode);
            barcodeMap.put(barcode, count);
        }
        return barcodeMap;
    }

    private HashMap getItem(String barcode) {
        for (HashMap item : productList) {
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