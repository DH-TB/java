package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})

public class PosMachine {
    private List<Product> productList;

    public void readDataSource(Reader reader) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        productList = objectMapper.readValue(reader, new TypeReference<List<Product>>() {});
    }


    public String printReceipt(String barcodeContent) throws IOException {
        if (productList == null) throw new IllegalStateException();

        if (barcodeContent == null) {
            barcodeContent = "[]";
        }

        final String splitLine = "------------------------------------------------------------";
        final String line = System.lineSeparator();
        StringBuilder receiptString = new StringBuilder();
        receiptString.append("Receipts").append(line).append(splitLine).append(line);

        Map<Object, Integer> barcodeCountMap = getBarcodeCountMap(barcodeContent);

        int totalPrice = getTotalPrice(line, receiptString, barcodeCountMap);
        receiptString.append(splitLine)
                .append(line)
                .append("Price: ")
                .append(totalPrice)
                .append(line);
        return receiptString.toString();
    }

    private int getTotalPrice(String line, StringBuilder receiptString, Map<Object, Integer> barcodeCountMap) {
        String template = "%-32s%-11s%d";
        int totalPrice = 0;

        for (Map.Entry<Object, Integer> barcodeCount : barcodeCountMap.entrySet()) {
            Product product = getProductByBarcode(barcodeCount);

            int price = product.getPrice();
            int count = barcodeCount.getValue();
            totalPrice += price * count;

            receiptString.append(String.format(template, product.getName(), price, count)).append(line);
        }
        return totalPrice;
    }

    private Product getProductByBarcode(Map.Entry<Object, Integer> barcodeCount) {
        return productList.stream()
                .filter(product -> product.getId().equals(barcodeCount.getKey()))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Map<Object, Integer> getBarcodeCountMap(String barcodeContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List barcodeList = objectMapper.readValue(barcodeContent, List.class);

        LinkedHashMap<Object, Integer> barcodeMap = new LinkedHashMap<>();
        for (Object barcode : barcodeList) {
            Integer result = barcodeMap.putIfAbsent(barcode, 1);
            if (result != null) {
                barcodeMap.put(barcode, result + 1);
            }
        }
        return barcodeMap;
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