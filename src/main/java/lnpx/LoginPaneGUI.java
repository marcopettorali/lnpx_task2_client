package lnpx;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginPaneGUI extends AnchorPane {

    private static VBox loginVBox;
    private static Label usernameLabel;
    private static TextField usernameTextField;
    private static Label passwordLabel;
    private static PasswordField passwordTextField;
    private static TextField visiblePasswordTextField;
    private static CheckBox showPasswordCheckBox;
    private static Button loginButton;
    private static Label errorLabel;
    private static Label orLabel;
    private static Button signupButton;
    
    
    public static void updateLoginPane(int res){
        
        if (res == 0){
            MainClass.loadMainForm();
        }
        
        if (res == -1) {
                errorLabel.setText("Username or password not valid");
            }
        if (res == -2) {
                errorLabel.setText("Username or password not valid");
            }
        
    } 
            
            
    public void buildLoginButton() {
        loginButton = new Button();
        loginButton.setId("loginButton");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setText("LOGIN");
        VBox.setMargin(loginButton, new Insets(10.0, 0.0, 0.0, 0.0)); 
        loginButton.setOnAction(e -> {
            MainClass.login(usernameTextField.getText(), passwordTextField.getText());
                     
        });

    }
    
    public void buildSignupButton() {
        signupButton = new Button();
        signupButton.setId("signupButton");
        signupButton.setMaxWidth(Double.MAX_VALUE);
        signupButton.setText("SIGN UP");
        VBox.setMargin(signupButton, new Insets(0.0, 0.0, 20.0, 0.0));
        signupButton.setOnAction(e -> {
            MainClass.loadSignupForm();
        });

    }

    public LoginPaneGUI() {

        loginVBox = new VBox();
        usernameLabel = new Label();
        usernameTextField = new TextField();
        passwordLabel = new Label();
        passwordTextField = new PasswordField();
        visiblePasswordTextField = new TextField();
        showPasswordCheckBox = new CheckBox();
        errorLabel = new Label("");
        orLabel = new Label();

        buildLoginButton();
        buildSignupButton();
        
        setId("loginPane");
        setPrefHeight(370.0);
        setPrefWidth(339.0);

        loginVBox.setId("loginVBox");
        loginVBox.setLayoutX(60.0);
        loginVBox.setLayoutY(20.0);
        loginVBox.setPrefHeight(114.0);
        loginVBox.setPrefWidth(220.0);
        loginVBox.setSpacing(15.0);

        usernameLabel.setId("usernameLabel");
        usernameLabel.setText("Username:");

        usernameTextField.setId("usernameTextField");

        passwordLabel.setId("passwordLabel");
        passwordLabel.setText("Password:");

        passwordTextField.setId("passwordTextField");

        showPasswordCheckBox.setId("showPasswordCheckBox");
        showPasswordCheckBox.setMnemonicParsing(false);
        showPasswordCheckBox.setText("Show password");

        visiblePasswordTextField.setManaged(false);
        visiblePasswordTextField.setVisible(false);

        visiblePasswordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        visiblePasswordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());

        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());
        
        orLabel.setId("orLabel");
        orLabel.setText("or");

        visiblePasswordTextField.textProperty().bindBidirectional(passwordTextField.textProperty());

        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER_LEFT);

        loginVBox.getChildren().add(usernameLabel);
        loginVBox.getChildren().add(usernameTextField);
        loginVBox.getChildren().add(passwordLabel);
        loginVBox.getChildren().add(passwordTextField);
        loginVBox.getChildren().add(visiblePasswordTextField);
        loginVBox.getChildren().add(showPasswordCheckBox);
        loginVBox.getChildren().add(errorLabel);
        loginVBox.getChildren().add(loginButton);
        loginVBox.getChildren().add(orLabel);
        loginVBox.getChildren().add(signupButton);
        

        getChildren().add(loginVBox);

    }
}
