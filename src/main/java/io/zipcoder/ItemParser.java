package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private String namePattern = "(name)[:@*^%!](\\w+)";
    private String pricePattern = "(price)[:@*^%!](\\d+\\.\\d{1,2})";
    private String typePattern = "(type)[:@*^%!](\\w+)";
    private String expirPattern = "(Expiration)[:@*^%!](\\d/.*)";
    private int exceptionCount = 0;


//    public List<Item> parseItemList(String valueToParse) {
//        List<Item> itemList = new ArrayList<>();
//        String[] array = valueToParse.toLowerCase().split("##");
//        for(String str : array){
//            try {
//                itemList.add(parseSingleItem(str));
//            } catch (ItemParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return itemList;
//    }
//
//    public Item parseSingleItem(String singleItem) throws ItemParseException {
//        List<String> stringList = new ArrayList<>();
//        Pattern pattern = Pattern.compile("");
//        String[] array = singleItem.toLowerCase().split(";");
//
//        System.out.println(Arrays.toString(array));
//        try {
//            for (String str : array) {
//                String temp = str.replaceAll("[@*^%!]", ":");
//                //System.out.println(removeFirst);
//                stringList.add(str.split("[:@^*%#]")[1]);
//
//            }
//        }catch (ArrayIndexOutOfBoundsException exception){
//            throw new ItemParseException();
//        }
//        System.out.println(stringList);
//            Item item = new Item(stringList.get(0), Double.valueOf(stringList.get(1)), stringList.get(2), stringList.get(3));
//
//        return item;
//    }


    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String newStr = singleItem.split("##")[0];
        String name, price, type, date;
        String str = newStr.toLowerCase();

            name = regexChecker(namePattern, str);
            price = regexChecker(pricePattern, str);
            type = regexChecker(typePattern, str);
            date = regexChecker(expirPattern, str);

            if(name == null || price == null || type == null || date == null){
                throw new ItemParseException();

            }

        Item item = new Item(name, Double.valueOf(price),type, date);
        return item;
    }

    public List<Item> parseItemList(String valueToParse) {
        List<Item> itemList = new ArrayList<>();
        String[] array = valueToParse.toLowerCase().split("##");
        System.out.println(Arrays.toString(array));
        for(String str : array){
            try {
                Item item = parseSingleItem(str);
                itemList.add(item);
            } catch (ItemParseException e) {
                exceptionCount++;
            }
        }
        return itemList;
    }


    public static String regexChecker(String theRegex, String strToCheck){
        Pattern checkRegex = Pattern.compile(theRegex,Pattern.CASE_INSENSITIVE);
        Matcher regexMatcher = checkRegex.matcher(strToCheck);

        while(regexMatcher.find()){
            if(regexMatcher.group().length() != 0){
                return regexMatcher.group(2);
            }
        }
        return null;
    }

    public int getExceptionCount(){
        return exceptionCount;
    }

    public static void main(String[] args) {
        ItemParser parser = new ItemParser();
        String str = "naMe:teleVision;price:323.5;type:electRoniCs;expiration:3/25/2019##";
        System.out.println(parser.regexChecker("(naMe)[:@*^%!](\\w+)", str.toLowerCase()));
        System.out.println(parser.regexChecker("(price)[:@*^%!](\\d+\\.\\d{1,2})",str.toLowerCase()));
        System.out.println(parser.regexChecker("(type)[:@*^%!](\\w+)", str.toLowerCase()));
        //System.out.println(parser.regexChecker("(Expiration)[:@*^%!](\\d{1,2}\\/\\d{1,2}\\/\\d{4})", str.toLowerCase()));
        System.out.println(parser.regexChecker("(Expiration)[:@*^%!](.*?)(##)", str.toLowerCase()));



    }


}
