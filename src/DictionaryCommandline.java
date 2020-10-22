import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;
public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords(){
        System.out.println("No\tEnglish\t\tVietnamese");
        for(int i = 0;i < arr_Word.size();i++){
            System.out.println(i+1 + "\t" + arr_Word.get(i).getWord_target() + "\t\t" + arr_Word.get(i).getWord_explain());
        }
    }

    public void dictionaryBasic(){
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        insertFromFile();
        showAllWords();
        //dictionaryLookup();
    }

    public Dictionary dictionarySearcher(String search){
    	/*
        Scanner scanner = new Scanner(System.in);
        String starts_text = scanner.nextLine();
        */
        Dictionary Searcher = new Dictionary();
        for (Word i : arr_Word) {
            if (i.getWord_target().startsWith(search)) {
                Searcher.add(i);
            }
        }
        return Searcher;
    }
    public void printDic(Dictionary dic) {
    	for(Word i : dic.arr_Word) {
    		System.out.println(i.getWord_target());
    	}
    }

	

}