import java.util.ArrayList;
public class Dictionary {
	protected ArrayList<Word> arr_Word = new ArrayList<Word>();

    public void setArr_Word(ArrayList<Word> arr_Word) {
        this.arr_Word = arr_Word;
    }

    public ArrayList<Word> getArr_Word() {
        return arr_Word;
    }


    protected void add(Word word) {
        arr_Word.add(word);
    }

}
