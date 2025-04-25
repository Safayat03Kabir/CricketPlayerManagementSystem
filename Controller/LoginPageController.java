package Controller;

import NetworkRequest.LoginRequest;
import NetworkRequest.LoginRespond;
import ClientRelatedPackage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;

public class LoginPageController {
    private Main mainFxml;
    @FXML
    private Button loginButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private PasswordField password;
    @FXML
    private javafx.scene.control.TextField clubName;
    @FXML
    public javafx.scene.image.ImageView imageView;




    @FXML
    void loginAction(ActionEvent event) {
        System.out.println("Login Button clicked");
        String clubName = this.clubName.getText().strip();
        String password = this.password.getText();
        LoginRequest loginRequest = new LoginRequest(clubName, password);
        mainFxml.writeToServer(loginRequest);
        LoginRespond loginRespond = (LoginRespond) mainFxml.readFromServer();

        System.out.println("Login Button clicked");
        System.out.println("Respond received");
        if(loginRespond.getIsValid()){
            System.out.println("Respond received for club + " + loginRespond.getClubName());
            mainFxml.ShowHomePage(loginRespond.getClubName());
            System.out.println("successfully logged in");

        }



    }

    @FXML
    void SignUpPressed(ActionEvent event) {
        mainFxml.showSignUpPage(mainFxml);

    }
    public void initialize(Main mainFxml) {
        this.mainFxml = mainFxml;
        //Image myImage = new Image(getClass().getResourceAsStream("loginpage.png"));
        //image.setImage()
//        Image image = new Image(getClass().getResourceAsStream("/Controller/loginpage.png"));
//        imageView.setImage(image);

    }


}
