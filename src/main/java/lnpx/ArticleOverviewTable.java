/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.util.List;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author rnoce
 */
public class ArticleOverviewTable extends TableView<SingleWordAnalysis> {
    
    private ObservableList<SingleWordAnalysis> content;
    
    public ArticleOverviewTable(){
        
        super();
        TableColumn Word = new TableColumn("Word");
        Word.setCellValueFactory(new PropertyValueFactory<>("Word"));
        TableColumn Occurrence = new TableColumn("Occurrence");
        Occurrence.setCellValueFactory(new PropertyValueFactory<>("Occurrence"));
        
        this.getColumns().addAll(Word,Occurrence);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }
    
    public void setItems(List<SingleWordAnalysis> ArrayList){
        
        content = FXCollections.observableArrayList();
        content.addAll(ArrayList);
        this.getItems().clear();
        this.setItems(content);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }
    
    public SingleWordAnalysis getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }
    
}
