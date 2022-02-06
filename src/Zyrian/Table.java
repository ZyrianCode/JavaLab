package Zyrian;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table
{
    private SimpleIntegerProperty _id;
    private SimpleStringProperty _word;

    Table(int id, String word)
    {
        _word = new SimpleStringProperty(word);
        _id = new SimpleIntegerProperty(id);
    }

    public void setId(int value){ _id.set(value); }
    public int getId(){ return _id.get(); }

    public void setWord(String value){ _word.set(value); }

    public String getWord(){ return _word.get(); }
}
