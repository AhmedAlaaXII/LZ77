package Decompression;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
public class Decode {
    public static <string> void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.print("##########                  welcome in decompression program                    #############");
        Queue<Integer> ListPositions = new LinkedList<>();
        Queue<Integer> ListLengths = new LinkedList<>();
        Queue<Character> ListNextSymbols = new LinkedList<>();
        int NumberOfTags;
        int Position =0 , Length=0;
        char NextSymbol;
        String tag;
        System.out.println();
        System.out.print("Enter the number of tags :  ");
        NumberOfTags=input.nextInt();
        int numberOfCharacters = NumberOfTags;
        System.out.println("input a sequence of tags :  ");
        for (int i = 0,j; i < NumberOfTags; ++i) {
            tag=input2.nextLine();
            for (j = 1; tag.charAt(j)!=' '; j++) {
                Position+=Character.getNumericValue(tag.charAt(j));
                if(tag.charAt(j+1)!=' '){
                    Position*=10;
                }
            }
            for (j += 3; tag.charAt(j)!=' '; j++) {
                Length+=Character.getNumericValue(tag.charAt(j));
                if(tag.charAt(j+1)!=' ')
                    Length*=10;
            }
            j+=3;
            NextSymbol=tag.charAt(j);
            ListPositions.add(Position);
            ListLengths.add(Length);
            ListNextSymbols.add(NextSymbol);
            numberOfCharacters+=Length;
            Position=0;
            Length=0;
        }
        char array[]=new char[numberOfCharacters];
        int index = 0, pos = 0;
        for (int i = 0; i < NumberOfTags; ++i) {
            pos = index;
            for (int j = ListPositions.element(); j > 0; --j) {
                pos--;
            }
            for (int j = 0; j < ListLengths.element(); ++j) {
                array[index] = array[pos];
                index++;
                pos++;
            }
            array[index] = ListNextSymbols.element();
            ListPositions.remove();
            ListLengths.remove();
            ListNextSymbols.remove();
            index++;
        }
        System.out.print("your original text is :  ");
        String FINAL = "" ;
        for (int i = 0; i < numberOfCharacters; ++i) {
            FINAL+=array[i];
        }
        System.out.println(FINAL);
        System.out.print("##########                              THANK YOU                                       #############");
    }
}
