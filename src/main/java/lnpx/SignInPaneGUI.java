package lnpx;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;

public class SignInPaneGUI extends AnchorPane {

    protected static Label label;
    protected static Separator separator;
    protected static Separator separator0;
    protected static Label UsernameLabel;
    protected static TextField PasswordText;
    protected static Label PasswordLabel;
    protected static Label FirstNameLabel;
    protected static TextField FirstNameText;
    protected static Label LastNameLabel;
    protected static TextField LastNameText;
    protected static Label DateLabel;
    protected static TextField DateText;
    protected static TextField UsernameText;
    protected static Label RepeatLabel;
    protected static TextField RepeatText;
    protected static Label ErrorLabel;
    protected static Button SignInButton;
    protected static Button BackToLoginButton;
    protected static Separator separator1;
    protected static Separator separator2;
    protected static Label EmailLabel;
    protected static TextField EmailText;

    public static void updateSignInPane(int result){
        
        if( result == 0 ){
            ErrorLabel.setText("Registration successfully completed");
            ErrorLabel.setTextFill(javafx.scene.paint.Color.valueOf("#4314ff"));
            
        }
        if( result == -1){
            ErrorLabel.setText("Username already exists");
            ErrorLabel.setTextFill(javafx.scene.paint.Color.valueOf("#ff1212"));   
        }
        if(result == -2){
            ErrorLabel.setText("An error has occurred during the communication with the server !");
            ErrorLabel.setTextFill(javafx.scene.paint.Color.valueOf("#ff1212"));
            
        }
        
           
    }
    
    
    
    
    public void buildSignInButton(){
        
        SignInButton.setLayoutX(82.0);
        SignInButton.setLayoutY(450.0);
        SignInButton.setMnemonicParsing(false);
        SignInButton.setPrefHeight(25.0);
        SignInButton.setPrefWidth(108.0);
        SignInButton.setText("Sign in");
        
        
        
        SignInButton.setOnAction((ActionEvent e) -> {
            
        String FNString = FirstNameText.getText();
        String LNString = LastNameText.getText();
        String EmailString = EmailText.getText();
        String dobString = DateText.getText();
        String UserString = UsernameText.getText();
        String PasswordString = PasswordText.getText();
        
        
        if (FNString.equals("") || EmailString.equals("") || LNString.equals("") || dobString.equals("") || UserString.equals("") || PasswordString.equals("")) {
                ErrorLabel.setText("Some of the fields are empty");
                return;
            }
            

        for (char c : FNString.toCharArray()) {
               if (Character.isDigit(c)) {
                    ErrorLabel.setText("First name must not contain a number");
                    return;
                }
            }
            
        for (char c : LNString.toCharArray()) {
                if (Character.isDigit(c)) {
                    ErrorLabel.setText("Last name must not contain a number");
                    return;
                }
            }
            
        if (!PasswordText.getText().equals(RepeatText.getText())) {
                ErrorLabel.setText("Repeated password does not match");
                return;
            }
        Date birth;
        try{
            birth=new SimpleDateFormat("dd/MM/yyyy").parse(dobString);
        }catch(ParseException pe){
            ErrorLabel.setText("Please insert the date using the rigth format ( dd/MM/yyyy)");
            return;
            }
        
        MainClass.signIn(UserString,PasswordString,EmailString,FNString,LNString,birth);
      
        });  
        
    }
    
    public void buildBackToLoginButton(){
        
        BackToLoginButton.setLayoutX(294.0);
        BackToLoginButton.setLayoutY(450.0);
        BackToLoginButton.setMnemonicParsing(false);
        BackToLoginButton.setPrefHeight(25.0);
        BackToLoginButton.setPrefWidth(108.0);
        BackToLoginButton.setText("Back to login");
        
        BackToLoginButton.setOnAction(e -> {
            MainClass.backToLoginForm();
        });
        
    }
    
    
    
    public SignInPaneGUI() {

        label = new Label();
        separator = new Separator();
        separator0 = new Separator();
        UsernameLabel = new Label();
        PasswordText = new TextField();
        PasswordLabel = new Label();
        FirstNameLabel = new Label();
        FirstNameText = new TextField();
        LastNameLabel = new Label();
        LastNameText = new TextField();
        DateLabel = new Label();
        DateText = new TextField();
        UsernameText = new TextField();
        RepeatLabel = new Label();
        RepeatText = new TextField();
        ErrorLabel = new Label();
        SignInButton = new Button();
        BackToLoginButton = new Button();
        separator1 = new Separator();
        separator2 = new Separator();
        EmailLabel = new Label();
        EmailText = new TextField();

        setId("AnchorPane");
        setPrefHeight(537.0);
        setPrefWidth(478.0);

        label.setLayoutX(6.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(35.0);
        label.setPrefWidth(469.0);
        label.setText("Please, enter your information in order to complete the signup procedure :");
        label.setFont(new Font("System Bold", 13.0));

        separator.setLayoutX(14.0);
        separator.setLayoutY(60.0);
        separator.setPrefHeight(3.0);
        separator.setPrefWidth(452.0);

        separator0.setLayoutX(10.0);
        separator0.setLayoutY(61.0);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(452.0);
        separator0.setPrefWidth(9.0);

        UsernameLabel.setLayoutX(19.0);
        UsernameLabel.setLayoutY(286.0);
        UsernameLabel.setPrefHeight(17.0);
        UsernameLabel.setPrefWidth(72.0);
        UsernameLabel.setText("Username :");

        PasswordText.setLayoutX(98.0);
        PasswordText.setLayoutY(340.0);

        PasswordLabel.setLayoutX(16.0);
        PasswordLabel.setLayoutY(344.0);
        PasswordLabel.setPrefHeight(17.0);
        PasswordLabel.setPrefWidth(63.0);
        PasswordLabel.setText("Password :");

        FirstNameLabel.setLayoutX(19.0);
        FirstNameLabel.setLayoutY(84.0);
        FirstNameLabel.setPrefHeight(17.0);
        FirstNameLabel.setPrefWidth(72.0);
        FirstNameLabel.setText("First Name :");

        FirstNameText.setLayoutX(107.0);
        FirstNameText.setLayoutY(80.0);

        LastNameLabel.setLayoutX(19.0);
        LastNameLabel.setLayoutY(137.0);
        LastNameLabel.setPrefHeight(17.0);
        LastNameLabel.setPrefWidth(72.0);
        LastNameLabel.setText("Last Name :");

        LastNameText.setLayoutX(107.0);
        LastNameText.setLayoutY(133.0);

        DateLabel.setLayoutX(19.0);
        DateLabel.setLayoutY(193.0);
        DateLabel.setPrefHeight(17.0);
        DateLabel.setPrefWidth(79.0);
        DateLabel.setText("Date of birth :");

        DateText.setLayoutX(107.0);
        DateText.setLayoutY(189.0);

        UsernameText.setLayoutX(91.0);
        UsernameText.setLayoutY(282.0);

        RepeatLabel.setLayoutX(20.0);
        RepeatLabel.setLayoutY(393.0);
        RepeatLabel.setText("Repeat password :");

        RepeatText.setLayoutX(124.0);
        RepeatText.setLayoutY(389.0);

        ErrorLabel.setLayoutX(64.0);
        ErrorLabel.setLayoutY(422.0);
        ErrorLabel.setPrefHeight(17.0);
        ErrorLabel.setPrefWidth(236.0);
        ErrorLabel.setTextFill(javafx.scene.paint.Color.valueOf("#ff1212"));

        separator1.setLayoutX(14.0);
        separator1.setLayoutY(512.0);
        separator1.setPrefHeight(3.0);
        separator1.setPrefWidth(452.0);

        separator2.setLayoutX(463.0);
        separator2.setLayoutY(60.0);
        separator2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator2.setPrefHeight(452.0);
        separator2.setPrefWidth(9.0);

        EmailLabel.setLayoutX(19.0);
        EmailLabel.setLayoutY(239.0);
        EmailLabel.setPrefHeight(17.0);
        EmailLabel.setPrefWidth(48.0);
        EmailLabel.setText("Email : ");

        EmailText.setLayoutX(82.0);
        EmailText.setLayoutY(235.0);

        buildSignInButton();
        buildBackToLoginButton();
        
        getChildren().add(label);
        getChildren().add(separator);
        getChildren().add(separator0);
        getChildren().add(UsernameLabel);
        getChildren().add(PasswordText);
        getChildren().add(PasswordLabel);
        getChildren().add(FirstNameLabel);
        getChildren().add(FirstNameText);
        getChildren().add(LastNameLabel);
        getChildren().add(LastNameText);
        getChildren().add(DateLabel);
        getChildren().add(DateText);
        getChildren().add(UsernameText);
        getChildren().add(RepeatLabel);
        getChildren().add(RepeatText);
        getChildren().add(ErrorLabel);
        getChildren().add(SignInButton);
        getChildren().add(BackToLoginButton);
        getChildren().add(separator1);
        getChildren().add(separator2);
        getChildren().add(EmailLabel);
        getChildren().add(EmailText);

    }
}
