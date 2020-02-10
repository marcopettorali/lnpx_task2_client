/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author rnoce
 */
public class UserOverviewTable extends TableView<User> {
    
    private ObservableList<User> UsersInformation;
    
    public UserOverviewTable(){
        
        super();
        TableColumn Username = new TableColumn("UserID");
        Username.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        TableColumn FirstName = new TableColumn("firstName");
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn LastName = new TableColumn("lastName");
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn dateOfBirth = new TableColumn("dateOfBirth");
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        TableColumn email = new TableColumn("email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        this.getColumns().addAll(Username,FirstName,LastName,dateOfBirth,email);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }
    
    public void setItems(List<User> ArrayResult) {
        UsersInformation = FXCollections.observableArrayList();
        UsersInformation.addAll(ArrayResult);
        this.getItems().clear();
        this.setItems(UsersInformation);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public User getSelected() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void relaseSelection() {
        this.getSelectionModel().clearSelection();
    }
}
