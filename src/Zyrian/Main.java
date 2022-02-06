package Zyrian;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.beans.property.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        final ObservableList<Table> _wordsList = FXCollections.observableArrayList(); //Список объектов
        final FileChooser _fileChooser = new FileChooser();

        TableView<Table> table = new TableView<Table>(_wordsList); //Таблица
        table.setPrefSize(300, 250);

        TextField textField = new TextField(); //Текстовое поле для добавления текста в таблицу
        textField.setPrefSize(200, 50);
        textField.setLayoutX(500);


        TableColumn<Table, Integer> idColumn = new TableColumn<Table, Integer>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<Table, Integer>("id"));
        table.getColumns().add(idColumn);

        TableColumn<Table, String> wordColumn = new TableColumn<Table, String>("Word");
        wordColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("word"));

        table.getColumns().add(wordColumn);

        Button importBtn = new Button("Import Words"); //Кнопка импортирования
        importBtn.setPrefSize(120, 50);
        importBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                File file = _fileChooser.showOpenDialog(stage);
                CustomReader customReader = new CustomReader();
                customReader.Initialize(file);
                customReader.ReadData(_wordsList);
            }
        });

        Button addBtn = new Button("Add button"); //Кнопка добавления
        addBtn.setPrefSize(120, 50);
        addBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent) {
                String line = textField.getText();
                _wordsList.add(new Table(_wordsList.size(), line));
            }
        });

        Button clearBtn = new Button("Clear All Words"); //Кнопка полного удаления
        clearBtn.setPrefSize(120, 50);
        clearBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                table.getItems().clear();
            }
        });

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button clearSelectedWordBtn = new Button("Clear selected Words"); //Кнопка удаления выбранных элементов
        clearSelectedWordBtn.setPrefSize(120, 50);
        clearSelectedWordBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                ObservableList<Table> selectedRows = table.getSelectionModel().getSelectedItems();
                ArrayList<Table> rows = new ArrayList<>(selectedRows);
                rows.forEach(row -> table.getItems().remove(row));
            }
        });


        RadioButton oneBtn = new RadioButton("One");
        RadioButton twoBtn = new RadioButton("Two");

        ToggleGroup group = new ToggleGroup();
        oneBtn.setToggleGroup(group);
        twoBtn.setToggleGroup(group);

        Label selectedLbl = new Label();

        oneBtn.setOnAction(event -> selectedLbl.setText("One Button"));
        twoBtn.setOnAction(event -> selectedLbl.setText("Two Button"));
        
        FlowPane root = new FlowPane(5, 5, table, importBtn, addBtn, clearBtn, clearSelectedWordBtn, oneBtn, twoBtn, selectedLbl, textField);

        Scene scene = new Scene(root, 900, 700);

        stage.setScene(scene);
        stage.setTitle("JavaLab");
        stage.show();
    }
}