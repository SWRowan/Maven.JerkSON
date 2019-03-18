package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroceryReporter {
    private final String originalFileText;
    private Map<String, Integer> valueMap = new HashMap<>();
    private ItemParser parser = new ItemParser();

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }

    private void getValueMap(){
        valueMap.put("milk", 0);
        valueMap.put("bread", 0);
        valueMap.put("cookies", 0);
        valueMap.put("apples",0);
        List<Item> itemList = parser.parseItemList(originalFileText);

        for(Item item : itemList){
            valueMap.get(item.getName());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}
