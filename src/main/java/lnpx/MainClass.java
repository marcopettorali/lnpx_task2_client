package lnpx;

import java.util.Date;
import java.util.Map;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.*;
import lnpx.messages.*;

public class MainClass extends Application {

    private static Stage MainStage;
    private static Stage LoginStage;
    private static Stage SignUpStage;
    private static Stage AdminStage;

    private static void loadLoginStage() {

        MainStage.close();
        SignUpStage.close();
        AdminStage.close();
        LoginStage.setTitle("Login");
        LoginStage.setResizable(false);
        LoginStage.setScene(new Scene(new Group(new LoginPaneGUI())));
        LoginStage.sizeToScene();
        LoginStage.show();

    }

    public static void loadMainForm() {
        loadMainStage();
    }

    private static void loadMainStage() {

        SignUpStage.close();
        AdminStage.close();
        LoginStage.close();
        MainStage = new Stage();
        MainStage.setTitle("Task2");
        MainStage.setResizable(false);
        MainStage.setScene(new Scene(new Group(new UserPaneGUI())));
        MainStage.sizeToScene();
        MainStage.show();

    }

    private static void loadSignupStage() {

        LoginStage.close();
        AdminStage.close();
        MainStage.close();

        SignUpStage = new Stage();
        SignUpStage.setTitle("Sign up");
        SignUpStage.setResizable(false);
        SignUpStage.setScene(new Scene(new Group(new SignInPaneGUI())));
        SignUpStage.sizeToScene();
        SignUpStage.show();

    }

    public static void loadSignupForm() {
        loadSignupStage();
    }

    public static void backToLoginForm() {
        loadLoginStage();
    }

    public static void searchContent(String keyword, Map<String, String> filters) {

        FindMsg search = new FindMsg(keyword, filters);
        ConnectionToServer.sendSearchRequest(search);

    }

    public static void requestTrendingKeywords() {

        ConnectionToServer.sendTrendingRequest();

    }

    public static void requestRecommendedArticles() {

        ConnectionToServer.sendRecommendedRequest();

    }

    public static void sendViewedArticle(String link, Map<String, String> filters) {

        ViewMsg v = new ViewMsg(link, filters);
        ConnectionToServer.sendViewedRequest(v);

    }

    public static void sendSites(Map<String, Boolean> sites) {

        ChangeSitesMsg csm = new ChangeSitesMsg(sites);
        ConnectionToServer.sendSitesRequest(csm);

    }

    public static void scrapeNow() {

        ConnectionToServer.sendScrapeNowRequest();

    }

    public static void sendScrapePeriod(double period) {

        ChangePeriodMsg cpm = new ChangePeriodMsg(period);
        ConnectionToServer.sendScrapePeriodRequest(cpm);

    }

    public static void requestUsersList() {

        ConnectionToServer.sendUserListRequest();

    }

    public static void loadAdminStage() {

        LoginStage.close();
        SignUpStage.close();
        MainStage.close();

        AdminStage = new Stage();
        AdminStage.setTitle("Admin control panel");
        AdminStage.setResizable(false);
        AdminStage.setScene(new Scene(new Group(new AdminPaneGUI())));
        AdminStage.sizeToScene();
        AdminStage.show();

    }

    public static void login(String username, String password) {

        LoginMsg msg = new LoginMsg(username, password);
        ConnectionToServer.loginToServer(msg);

    }

    public static void signIn(String user, String pwd, String email, String fn, String ln, Date d) {

        SignInMsg sign = new SignInMsg(user, fn, ln, d, email, pwd, false);
        ConnectionToServer.signInToServer(sign);

    }

    @Override
    public void start(Stage primaryStage) {

        MainStage = new Stage();
        AdminStage = new Stage();
        SignUpStage = new Stage();
        LoginStage = new Stage();
        ConnectionToServer.createConnection();
        loadLoginStage();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        ConnectionToServer.closeConnection();
    }

}
