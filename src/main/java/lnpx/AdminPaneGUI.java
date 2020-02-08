package lnpx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lnpx.messages.ClientsResponseMsg;

public class AdminPaneGUI extends AnchorPane {

    protected  Separator separator;
    protected  Separator separator0;
    protected  Label infouserLabel;
    protected  UserOverviewTable infoUserTable;
    protected  Label IntroLabel;
    protected  Label SitesLabel;
    protected  CheckBox OptionRepubblica;
    protected  CheckBox OptionAnsa;
    protected  CheckBox OptionCorriere;
    protected  CheckBox OptionSky;
    protected  Button UpdateSitesButton;
    protected  Label ScrapePeriodLabel;
    protected  TextField ScrapePeriodTextField;
    protected  Label minuteLabel;
    protected  Button ScrapePeriodButton;
    protected  Button ScrapeNowButton;
    protected  Label ErrorScrapePeriod;
    protected  Label ErrorSites;

    
    private void buildUpdateSitesButton(){
        
        UpdateSitesButton.setLayoutX(547.0);
        UpdateSitesButton.setLayoutY(240.0);
        UpdateSitesButton.setMnemonicParsing(false);
        UpdateSitesButton.setPrefHeight(25.0);
        UpdateSitesButton.setPrefWidth(98.0);
        UpdateSitesButton.setText("Apply");
        
        
        UpdateSitesButton.setOnAction( e ->{
          
        boolean rep = OptionRepubblica.isSelected();
        boolean ansa = OptionAnsa.isSelected();
        boolean corr = OptionCorriere.isSelected();
        boolean sky = OptionSky.isSelected();
            
        if(rep || ansa || corr || sky){
            
                ErrorSites.setText("");
                Map<String,Boolean> sites = new HashMap<>();
                sites.put("Repubblica",rep);
                sites.put("Ansa", ansa);
                sites.put("Corriere",corr);
                sites.put("SkyTG24", sky);

                MainClass.sendSites(sites);
            
        }
        else{
                
                ErrorSites.setText("Please, select at least one site for the scrape !");
                
            }
                 
        });
        
        
        
    }
    
    private void buildScrapeNowButton(){
        
        ScrapeNowButton.setLayoutX(426.0);
        ScrapeNowButton.setLayoutY(386.0);
        ScrapeNowButton.setMnemonicParsing(false);
        ScrapeNowButton.setPrefHeight(48.0);
        ScrapeNowButton.setPrefWidth(186.0);
        ScrapeNowButton.setText("SCRAPE NOW");
        
        ScrapeNowButton.setOnAction(e -> {
            
            MainClass.scrapeNow();
            
        });
        
        
    }
    
    private void buildScrapePeriodButton(){
        
        ScrapePeriodButton.setLayoutX(121.0);
        ScrapePeriodButton.setLayoutY(418.0);
        ScrapePeriodButton.setMnemonicParsing(false);
        ScrapePeriodButton.setPrefHeight(25.0);
        ScrapePeriodButton.setPrefWidth(98.0);
        ScrapePeriodButton.setText("Update");
        
        ScrapePeriodButton.setOnAction( e -> {
            
            String period = ScrapePeriodTextField.getText();
            
            if(period.equals("")){
                
                ErrorScrapePeriod.setText("Please, specify the scrape period !");
                
            }
            else{
               
               ErrorScrapePeriod.setText(""); 
               double p = Double.parseDouble(period);
               MainClass.sendScrapePeriod(p);
            }
                
        });
        
        
        
    }
    
    private void initializationPane(){
        
        ClientsResponseMsg usersList=MainClass.requestUsersList();
        List<User> list = usersList.getClients();
        infoUserTable.setItems(list);
              
    }
    
    
    public AdminPaneGUI() {

        separator = new Separator();
        separator0 = new Separator();
        infouserLabel = new Label();
        infoUserTable = new UserOverviewTable();
        IntroLabel = new Label();
        SitesLabel = new Label();
        OptionRepubblica = new CheckBox();
        OptionAnsa = new CheckBox();
        OptionCorriere = new CheckBox();
        OptionSky = new CheckBox();
        UpdateSitesButton = new Button();
        ScrapePeriodLabel = new Label();
        ScrapePeriodTextField = new TextField();
        minuteLabel = new Label();
        ScrapePeriodButton = new Button();
        ScrapeNowButton = new Button();
        ErrorScrapePeriod = new Label();
        ErrorSites = new Label();

        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(698.0);

        buildUpdateSitesButton();
        buildScrapeNowButton();
        buildScrapePeriodButton();
        initializationPane();
        
        separator.setLayoutX(346.0);
        separator.setLayoutY(28.0);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(303.0);
        separator.setPrefWidth(8.0);

        separator0.setLayoutX(2.0);
        separator0.setLayoutY(328.0);
        separator0.setPrefHeight(6.0);
        separator0.setPrefWidth(698.0);

        infouserLabel.setLayoutX(22.0);
        infouserLabel.setLayoutY(30.0);
        infouserLabel.setPrefHeight(17.0);
        infouserLabel.setPrefWidth(125.0);
        infouserLabel.setText("About users");
        infouserLabel.setFont(new Font("System Bold", 12.0));

        infoUserTable.setLayoutX(22.0);
        infoUserTable.setLayoutY(62.0);
        infoUserTable.setPrefHeight(248.0);
        infoUserTable.setPrefWidth(296.0);

        IntroLabel.setLayoutX(280.0);
        IntroLabel.setLayoutY(6.0);
        IntroLabel.setPrefHeight(17.0);
        IntroLabel.setPrefWidth(148.0);
        IntroLabel.setText("ADMIN CONTROL PANEL");
        IntroLabel.setFont(new Font("System Bold", 12.0));

        SitesLabel.setLayoutX(389.0);
        SitesLabel.setLayoutY(30.0);
        SitesLabel.setPrefHeight(17.0);
        SitesLabel.setPrefWidth(148.0);
        SitesLabel.setText("Select newspaper's site:");
        SitesLabel.setFont(new Font("System Bold", 12.0));
        
        ErrorScrapePeriod.setLayoutX(88);
        ErrorScrapePeriod.setLayoutY(390);
        ErrorScrapePeriod.setPrefHeight(17.0);
        ErrorScrapePeriod.setPrefWidth(148.0);
        ErrorScrapePeriod.setText("");
        ErrorScrapePeriod.setTextFill(Color.RED);
        
        ErrorSites.setLayoutX(363);
        ErrorSites.setLayoutY(209);
        ErrorSites.setPrefHeight(17.0);
        ErrorSites.setPrefWidth(148.0);
        ErrorSites.setText("");
        ErrorSites.setTextFill(Color.RED);

        OptionRepubblica.setLayoutX(371.0);
        OptionRepubblica.setLayoutY(62.0);
        OptionRepubblica.setMnemonicParsing(false);
        OptionRepubblica.setPrefHeight(17.0);
        OptionRepubblica.setPrefWidth(148.0);
        OptionRepubblica.setText("Repubblica.it");

        OptionAnsa.setLayoutX(371.0);
        OptionAnsa.setLayoutY(96.0);
        OptionAnsa.setMnemonicParsing(false);
        OptionAnsa.setText("Ansa.it");

        OptionCorriere.setLayoutX(371.0);
        OptionCorriere.setLayoutY(130.0);
        OptionCorriere.setMnemonicParsing(false);
        OptionCorriere.setText("Corrieredellasera .it");

        OptionSky.setLayoutX(371.0);
        OptionSky.setLayoutY(163.0);
        OptionSky.setMnemonicParsing(false);
        OptionSky.setText("SkyTG24.it");

        

        ScrapePeriodLabel.setLayoutX(33.0);
        ScrapePeriodLabel.setLayoutY(369.0);
        ScrapePeriodLabel.setPrefHeight(17.0);
        ScrapePeriodLabel.setPrefWidth(84.0);
        ScrapePeriodLabel.setText("Scrape every :");
        ScrapePeriodLabel.setFont(new Font("System Bold", 12.0));

        ScrapePeriodTextField.setLayoutX(116.0);
        ScrapePeriodTextField.setLayoutY(365.0);
        ScrapePeriodTextField.setPrefHeight(17.0);
        ScrapePeriodTextField.setPrefWidth(108.0);

        minuteLabel.setLayoutX(233.0);
        minuteLabel.setLayoutY(369.0);
        minuteLabel.setPrefHeight(17.0);
        minuteLabel.setPrefWidth(44.0);
        minuteLabel.setText("minute");

        

        

        getChildren().add(separator);
        getChildren().add(separator0);
        getChildren().add(infouserLabel);
        getChildren().add(infoUserTable);
        getChildren().add(IntroLabel);
        getChildren().add(SitesLabel);
        getChildren().add(OptionRepubblica);
        getChildren().add(OptionAnsa);
        getChildren().add(OptionCorriere);
        getChildren().add(OptionSky);
        getChildren().add(UpdateSitesButton);
        getChildren().add(ScrapePeriodLabel);
        getChildren().add(ScrapePeriodTextField);
        getChildren().add(minuteLabel);
        getChildren().add(ScrapePeriodButton);
        getChildren().add(ScrapeNowButton);

    }
}
