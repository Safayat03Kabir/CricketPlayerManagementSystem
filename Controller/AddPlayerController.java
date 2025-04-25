package Controller;

import NetworkRequest.AddPlayerRequest;
import ClientRelatedPackage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import serverPackage.Player;

public class AddPlayerController {
    private Main main;
    @FXML private TextField PlayerNameField;
    @FXML private TextField PlayerCountryField;
    @FXML private TextField PlayerAgeField;
    @FXML private TextField PlayerPositionField;
    @FXML private TextField PlayerNumberField;
    @FXML private TextField PlayerHeightField;
    @FXML private TextField PlayerSalaryField;

    @FXML
    void AddPlayer(ActionEvent event) {
        String name = PlayerNameField.getText();
        String country = PlayerCountryField.getText();
        String age = PlayerAgeField.getText();
        String position = PlayerPositionField.getText();
        String number = PlayerNumberField.getText();
        String height = PlayerHeightField.getText();
        String salary = PlayerSalaryField.getText();

        if (name.isEmpty() || country.isEmpty() || age.isEmpty() || position.isEmpty()) {
            showAlert("Error", "Please fill all required fields!", Alert.AlertType.ERROR);
            return;
        }
        Player player = null;
        try {
            // Convert age, height, and salary
            int Age = Integer.parseInt(age);
            double Height = Double.parseDouble(height);
            double Salary = Double.parseDouble(salary);
            int Number = Integer.parseInt(number);

            // Create Player object
            player = new Player(name, country, main.getClubName(), position, Age, Number, Salary, Height, false, 0.0, "null");

            // Success message
            showAlert("Success", "Player Added Successfully!", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid numeric input! Please enter valid numbers for Age, Height, and Salary.", Alert.AlertType.ERROR);
        }

        // Save player logic here (send to backend or store in a list)
        System.out.println("Player Added: " + name);

        showAlert("Success", "Player Added Successfully!", Alert.AlertType.INFORMATION);
        main.writeToServer(new AddPlayerRequest(player));
    }

    @FXML
    void Cancel(ActionEvent event) {
        // Close the window or return to the previous screen
        PlayerNameField.getScene().getWindow().hide();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    void setMain(Main main) {
        this.main = main;
    }
}
