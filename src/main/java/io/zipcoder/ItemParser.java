package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemParser {

    public List<Item> parseItemList(String valueToParse) {
        List<Item> itemList = new ArrayList<>();
        String[] array = valueToParse.toLowerCase().split("##");
        for(String str : array){
            try {
                itemList.add(parseSingleItem(str));
            } catch (ItemParseException e) {
                e.printStackTrace();
            }
        }
        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        List<String> stringList = new ArrayList<>();
        String[] array = singleItem.toLowerCase().split(";");
        try {
            for (String str : array) {
                stringList.add(str.split("[:@^*%#]")[1]);

            }
        }catch (ArrayIndexOutOfBoundsException exception){
            throw new ItemParseException();
        }
            Item item = new Item(stringList.get(0), Double.valueOf(stringList.get(1)), stringList.get(2), stringList.get(3));

        return item;
    }
}
