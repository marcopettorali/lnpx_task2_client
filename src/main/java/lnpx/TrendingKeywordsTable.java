/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lnpx.messages.*;

public class TrendingKeywordsTable extends TableView<Trend> {
    
    private ObservableList<Trend> TrendingKeys;
    
    public TrendingKeywordsTable(){
        
        super();
        TableColumn Keyword = new TableColumn("Keyword");
        Keyword.setCellValueFactory(new PropertyValueFactory<>("Keyword"));
        TableColumn Percentage = new TableColumn("Percentage");
        Percentage.setCellValueFactory(new PropertyValueFactory<>("Percentage"));
        
        this.getColumns().addAll(Keyword,Percentage);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
           
    }
    
    public void setItems(List<Trend> tr){
        this.getItems().clear();
        TrendingKeys=FXCollections.observableArrayList();
        TrendingKeys.addAll(tr);
        
        this.setItems(TrendingKeys);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    public Trend getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }
    
}
