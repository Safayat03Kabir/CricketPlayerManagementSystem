package ClientRelatedPackage;

import NetworkRequest.SearchByClubAndCountry;
import Controller.HomePageController;
import Controller.LoginPageController;
import Controller.SignUpPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{
    //private HomePageUpdater homePageUpdater;
    private Stage primaryStage;
    private NetworkUtil networkUtil;
    private String ClubName;
    private boolean isInMarket;
    @Override
    public void start(Stage primaryStage){
        networkUtil = new NetworkUtil();

        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        //primaryStage.setOnCloseRequest(e->closeProgram());
        showLoginPage();

    }
    public void showLoginPage(){
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/fxml/LoginPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("In ShowLoginPage in Main");
            e.printStackTrace();
        }

        // Loading the controller
        LoginPageController controller = loader.getController();
        controller.initialize(this);
        //updater.setLoginController(controller);

        // Set the primary stage
        primaryStage.setTitle("IPL Manager - Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void showSignUpPage(Main main){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/fxml/SignUpPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Error in showSignUpPage");
            e.printStackTrace();
        }

        // Correct the controller type
        SignUpPageController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setTitle("IPL Manager - Sign Up");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void ShowHomePage(String clubName){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/fxml/HomePage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("In ShowHomePage in Main");
            e.printStackTrace();
        }
        HomePageController controller = loader.getController();
        controller.init(this);

        //controller.initialize(this);
        //updater.setLoginController(controller);

        // Set the primary stage
        //primaryStage.setTitle("IPL Manager - Login");
        controller.GetClubName().setText(clubName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        this.ClubName = clubName;
        this.isInMarket = false;
        HomePageUpdater homePageUpdater = new HomePageUpdater(controller, this);
        SearchByClubAndCountry searchByClubAndCountry = new SearchByClubAndCountry(clubName, "any");
        this.writeToServer(searchByClubAndCountry);


    }



    public void writeToServer(Object o){
        networkUtil.writeToServer(o);

    }
    public Object readFromServer(){
        return networkUtil.listenFromServer();
    }

    public void setClubName(String ClubName){
        this.ClubName = ClubName;
    }
    public String getClubName(){
        return ClubName;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public void setIsInMarket(Boolean isInMarket){
        this.isInMarket = isInMarket;
    }
    public boolean getIsInMarket(){
        return isInMarket;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
