/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

/**
 *
 * @author rnoce
 */
public class ArticlesTable extends TableView<Article> {
    
    private ObservableList<Article> articles;
    
    public ArticlesTable(){
        
        super();
        TableColumn Title = new TableColumn("Title");
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        /*TableColumn Link = new TableColumn("Link");
        Link.setCellValueFactory(new PropertyValueFactory<>("Link"));*/
        /*TableColumn Topic = new TableColumn("Topic");
        Topic.setCellValueFactory(new PropertyValueFactory<>("Topic"));*/
        /*TableColumn Authors = new TableColumn("Authors");
        Authors.setCellValueFactory(new PropertyValueFactory<>("Authors"));*/
        TableColumn Newspaper = new TableColumn("Newspaper");
        Newspaper.setCellValueFactory(new PropertyValueFactory<>("Newspaper"));
        /*TableColumn Text = new TableColumn("Text");
        Text.setCellValueFactory(new PropertyValueFactory<>("Text"));*/
        TableColumn Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        /*TableColumn Country = new TableColumn("Country");
        Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        TableColumn Region = new TableColumn("Region");
        Region.setCellValueFactory(new PropertyValueFactory<>("Region"));*/
        TableColumn City = new TableColumn("City");
        City.setCellValueFactory(new PropertyValueFactory<>("City"));
        
        this.getColumns().addAll(Title,/*Link,Topic,Authors,*/Newspaper,/*Text,*/Date,/*Country,Region,*/City);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }
    
     public void setItems(List<Article> ArrayResult) {
        articles = FXCollections.observableArrayList();
        articles.addAll(ArrayResult);
        this.getItems().clear();
        this.setItems(articles);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public Article getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }
}
