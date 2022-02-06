package Zyrian;

import Zyrian.Exceptions.EmptyFileException;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class CustomReader {

    private File _file;
    private FileReader _fileReader;
    private BufferedReader _bufferReader;

    public void Initialize(@NotNull File file)
    {
        try {

            _file = file;
            _fileReader = new FileReader(_file);
            _bufferReader = new BufferedReader(_fileReader);

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
    public void ReadData(ObservableList<Table> containerToFill)
    {
        try {
            String line = _bufferReader.readLine();
            int id = CheckContainerSize(containerToFill);

            while (line != null) {
                containerToFill.add(new Table(id, line));
                line = _bufferReader.readLine();
                id++;
            }

        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    private int CheckContainerSize(ObservableList<Table> containerToFill) {
        return containerToFill.size();
    }
}
