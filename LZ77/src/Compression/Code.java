package Compression;

import java.util.ArrayList;
import java.util.Scanner;
public class Code {
    public static <string> void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("##########                  welcome in compression program                    #############");
        ArrayList<Integer> positions = new ArrayList<Integer>();
        ArrayList<Integer> listOfLength = new ArrayList<Integer>();
        ArrayList<Character> listNextSymbol = new ArrayList<Character>();
        String word;
        int searchSize;
        int lookSize;
        System.out.print("Input text :  ");
        word = input.next();
        System.out.print("Input the search size:  ");
        searchSize = input.nextInt();
        System.out.print("Input the Look a head size:  ");
        lookSize = input.nextInt();
        int wordSize = word.length();
        for (int i = 0; i < wordSize; i++) {// ( i ) is iterator always stop at the start of the look ahead buffer
            String search, look;
            if (i < searchSize) search = word.substring(0, i);

            else search = word.substring(i - searchSize, i);//the search size form i-searchSize to i

            if (wordSize - i < lookSize)
                look = word.substring(i, wordSize);//when the look on the right side of the text

            else look = word.substring(i, i + lookSize);//when the look on the left side or in middle of the text

            int position = 0;
            //System.out.println(search+" -> "+look);
            for (int index = 1; index <= look.length(); index++) {//index 1 and <= because the last index in substring not included
                int lastIndexOf = search.lastIndexOf(look.substring(0, index));
                if (lastIndexOf == -1 || index == look.length()) {// ||index==look.length() to collect the last index in look
                    positions.add(position);
                    // the length of symbol we find
                    listOfLength.add(index - 1);
                    listNextSymbol.add(look.charAt(index - 1));
                    i += index - 1;
                    break;
                } else {
                    int indexOfSymbol = lastIndexOf;//index of the symbol in the search
                    position = search.length() - indexOfSymbol;//the length between the look and the symbol
                }
            }
        }
        int numberOfTags=0;
        int biggestPosition=0 , biggestLength=0;
        for (int tag = 0; tag < positions.size(); tag++) {
            int position = positions.get(tag);
            int length = listOfLength.get(tag);
            System.out.println("<" + position + " , " + length + " , " + listNextSymbol.get(tag) + ">");
            numberOfTags++;
            if(biggestPosition<position){
                biggestPosition=position;
            }
            if(biggestLength<length){
                biggestLength=length;
            }
        }
        int bitsOfPosition = 0;
        int bitsOfLength =  0 ;
        for (int i = 1; i < biggestPosition; i*=2) {
            bitsOfPosition++;
        }
        for (int i = 1; i < biggestLength; i*=2) {
            bitsOfLength++;
        }
        int originalData = (word.length())*8;
        int compressionSize = numberOfTags*(bitsOfPosition+bitsOfLength+8);
        System.out.print("the number of tags : ");
        System.out.println(numberOfTags);
        System.out.print("the compression size is : ");
        System.out.println(compressionSize);
        System.out.print("the original data size is : ");
        System.out.println(originalData);
        System.out.print("##########                             THANK YOU                               #############");
    }
}

