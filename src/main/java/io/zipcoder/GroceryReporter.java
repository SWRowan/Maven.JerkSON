package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.List;

public class GroceryReporter {
    private final String originalFileText;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    @Override
    public String toString() {
        ItemParser parser = new ItemParser();
        StringBuilder sb = new StringBuilder();
        List<Item> itemList = parser.parseItemList(originalFileText);


        return sb.toString();
    }
}
