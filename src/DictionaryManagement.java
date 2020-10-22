import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;

public class DictionaryManagement extends Dictionary {

    public void insertFromCommandline(){
        try (Scanner input = new Scanner(System.in)) {
        	int n = Integer.parseInt(input.nextLine());
			for (int i = 0;i<n; i++){
			    String w_Target = input.nextLine();
			    String w_Explain = input.nextLine();
			    Word new_word = new Word(w_Target,w_Explain);
			    arr_Word.add(new_word);
			}
		}
    }

    public void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("./Data/DictEV.dic"),"UTF-8");
        while(scanner.hasNextLine()){
            String[] nd = scanner.nextLine().split("=");
            Word w = new Word(nd[0], nd[1]);
            arr_Word.add(w);
        }
        scanner.close();
    }

    public String dictionaryLookup(String word_seach) {
        String s = "Not Found";
        for (Word i : arr_Word) {
            if (i.getWord_target().equals(word_seach)) {
                s = i.getWord_explain();
                return s;
            }
        }
        return s;
    }
    public void them_word(Word them){
        arr_Word.add(them);
    }

    public void xoa_word(String target){
    	int index = 0;
        for(int i=0;i<arr_Word.size();i++) {
        	if(arr_Word.get(i).getWord_target().equals(target)) {
        		index = i;
        	}
        }
        arr_Word.remove(index);
    }

    public  void sua_word(String target, Word w){
    	int index = 0;
        for(int i=0;i<arr_Word.size();i++) {
        	if(arr_Word.get(i).getWord_target().equals(target)) {
        		index = i;
        	}
        }
        arr_Word.set(index, w);
    }

    public void dictionaryExportToFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try { 
            File file = new File("Data/DictEV.dic");
            fw = new FileWriter(file.getAbsoluteFile(), StandardCharsets.UTF_8,false);
            bw = new BufferedWriter(fw);
            for(Word i : arr_Word){
                bw.write(i.getWord_target() + "=" + i.getWord_explain() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
