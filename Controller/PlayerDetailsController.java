package Controller;

import ClientRelatedPackage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import serverPackage.Player;
import java.text.DecimalFormat;
import java.io.InputStream;

public class PlayerDetailsController {
    private Main main;
    @FXML
    private Label PlayerName;
    @FXML
    private Label PlayerAge;
    @FXML
    private Label PlayerNumber;
    @FXML
    private Label PlayerWeeklySalary;
    @FXML
    private Label PlayerHeight;
    @FXML
    private Label PlayerCountry;
    @FXML
    private Label PlayerPosition;
    @FXML
    private ImageView PlayerImage;
    @FXML
    private Button Cancel;
    public void init(Player player) {
        this.PlayerName.setText(player.getName());
        this.PlayerAge.setText(String.valueOf(player.getAge()));
        this.PlayerNumber.setText(String.valueOf(player.getNumber()));
//        this.PlayerWeeklySalary.setText(String.valueOf(player.getWeeklySalary()));
        DecimalFormat df = new DecimalFormat("$#.00");
        String formattedSalary = df.format(player.getWeeklySalary());

        this.PlayerWeeklySalary.setText(formattedSalary);
        this.PlayerHeight.setText(String.valueOf(player.getHeight()));
        this.PlayerCountry.setText(String.valueOf(player.getCountry()));
        this.PlayerPosition.setText(String.valueOf(player.getPosition()));
        if(player.getLink().equals("null")) {
            System.out.println("Here");
//            playerImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/" + player.getName() + ".png"))));

            String imagePath = "/resources/pictures/" + player.getName() + ".png";
            InputStream imageStream = getClass().getResourceAsStream(imagePath);

            if (imageStream == null) {
                // If the player's image is missing, load a default image
                imageStream = getClass().getResourceAsStream("/resources/pictures/Default.png");
            }

            PlayerImage.setImage(new Image(imageStream));
        }

    }
    @FXML
    private void CancelButtonPressed(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
}
