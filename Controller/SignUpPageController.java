package Controller;

import NetworkRequest.SignUpRequest;
import ClientRelatedPackage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpPageController {
    private Main main;
    @FXML
    private TextField clubName;

    @FXML
    private PasswordField password;
    @FXML
    private Button SignUpButton;
    @FXML
    private Button Cancel;


    @FXML
    void signUpAction(ActionEvent event) {
        String club = clubName.getText();
        String pass = password.getText();

        if (club.isEmpty() || pass.isEmpty()) {
            showAlert("Error", "Club Name and Password cannot be empty!", Alert.AlertType.ERROR);
            return;
        }
        SignUpRequest signUpRequest = new SignUpRequest(club, pass);
        main.writeToServer(signUpRequest);
        // TODO: Store the club name and password in a database or file (implement as needed)
        System.out.println("Club Registered: " + club);

        showAlert("Success", "Sign Up Successful!", Alert.AlertType.INFORMATION);
        main.showLoginPage();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    void CancelButtonPressed(ActionEvent event) {
        main.showLoginPage();
    }

}
