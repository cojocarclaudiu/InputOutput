
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\claud\\IdeaProjects\\ReadAndCountFromFile\\src\\input.txt");
            Scanner scanner2 = new Scanner(file);
            String text = "";
            while(scanner2.hasNextLine()){
                text += scanner2.nextLine();
            }
            scanner2.close();
            System.out.print("Find a word (1) or count the words(2)?");
            Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.print("Word to find:");
                        String wordToFind = scanner1.nextLine();
                        find(wordToFind, text);
                        break;
                    case 2:
                        trim(text);
                        break;
                    default:
                        System.out.println("Out of range...");
                }
        } catch (IOException e) {
            System.err.printf("Error: %s", e);
        }

    }

    public static void find(String word, String text) {

        int counter = 0;
        for(int i =0 ; i < text.length();i++){
            String wordToCompare = "";
            while(text.charAt(i)!=' '&& text.charAt(i)!='.'&& text.charAt(i)!='!'&& text.charAt(i)!=','){
                wordToCompare += text.charAt(i);
                i++;
                if(i>=text.length()) break;
            }
            System.out.println(wordToCompare+" "+word);
            if(wordToCompare.equalsIgnoreCase(word)) counter++;
        }
        if (counter >0) {
            System.out.printf("%s exist in this text and appear %d times...", word, counter);
        } else {
            System.out.printf("%s it's not in this text...", word);
        }
    }
    public static void trim(String text){
        Map<String,Integer> wordMap = new HashMap<>();
        for(int i = 0 ; i < text.length() ; i++){
            String wordToCompare = "";
            while(text.charAt(i)!=' '&& text.charAt(i)!='.'&& text.charAt(i)!='!'&& text.charAt(i)!=','){
                wordToCompare += text.charAt(i);
                i++;
                if(text.length()<=i) break;
            }
            if(wordToCompare.isEmpty()){
                continue;
            }else{
                if(wordMap.containsKey(wordToCompare)) wordMap.put(wordToCompare,wordMap.get(wordToCompare)+1);
                else wordMap.put(wordToCompare,1);
            }
        }
        try {
            FileWriter output = new FileWriter("C:\\Users\\claud\\IdeaProjects\\ReadAndCountFromFile\\src\\output.txt");
            for(Map.Entry<String,Integer> entry: wordMap.entrySet()){
                output.write(entry.getKey()+" appears "+ entry.getValue()+" times.\n");
            }
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}