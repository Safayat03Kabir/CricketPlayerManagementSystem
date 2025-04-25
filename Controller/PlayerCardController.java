package Controller;

import NetworkRequest.GetPlayerDetails;
import ClientRelatedPackage.Main;
import ClientRelatedPackage.NetworkUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import serverPackage.Player;

import java.io.IOException;
import java.io.InputStream;


public class PlayerCardController {
    private Player player;
    private Main main;
    private String PlayerName;
    private NetworkUtil networkUtil;
    //private String currnetClubName;

    @FXML
    private Label playerName;
    @FXML
    private Label playerClubName;
    @FXML
    private Label playerCountry;
    @FXML
    private Button Details;
    @FXML
    private Button Sell;
    @FXML
    private ImageView playerImage;
    @FXML
    private Button Buy;
    public void setPlayerData(Player player, Main main) {
        this.player = player;
        this.PlayerName = player.getName();
        playerName.setText(player.getName());
        playerCountry.setText(player.getCountry());
        playerClubName.setText(player.getClub());
        if(player.getLink().equals("null")){
            System.out.println("Here");
//            playerImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/" + player.getName() + ".png"))));

            String imagePath = "/resources/pictures/" + player.getName() + ".png";
            InputStream imageStream = getClass().getResourceAsStream(imagePath);

            if (imageStream == null) {
                // If the player's image is missing, load a default image
                imageStream = getClass().getResourceAsStream("/resources/pictures/Default.png");
            }

            playerImage.setImage(new Image(imageStream));



        }
        else{
            Image pImage = new Image(player.getLink(), true);
            playerImage.setImage(pImage);
        }
        System.out.println("Player set + " + player.getName());
        this.main = main;
        this.networkUtil = main.getNetworkUtil();


    }
    @FXML
    public void SellButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/SellTextBox.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        SellTextBoxController controller = fxmlLoader.getController();
        controller.init(player, main);
        Dialog dialog = new Dialog();
        dialog.setDialogPane(dialogPane);
        dialog.initStyle(StageStyle.UNDECORATED);
        controller.getBuyButton().setVisible(false);
        controller.getPriceLabel().setVisible(false);
        dialog.showAndWait();
        playerClubName.setText(main.getClubName());

    }
    public Button getSellButton() {
        return Sell;
    }
    @FXML
    void BuyPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/SellTextBox.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        SellTextBoxController controller = fxmlLoader.getController();
        controller.init(player, main);
        controller.getTextField().setVisible(false);

        controller.getPromptLabel().setText("Are sure you want to buy?");
        controller.getPriceLabel().setVisible(true);
        Dialog dialog = new Dialog();
        dialog.setDialogPane(dialogPane);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.showAndWait();


    }
    @FXML
    void DetailButtonPressed(ActionEvent event) throws IOException {

        main.writeToServer(new GetPlayerDetails(PlayerName));
    }
    public Button getBuyButton() {
        return Buy;
    }
    public ImageView getPlayerImage() {
        return playerImage;
    }


}
