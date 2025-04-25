package Controller;

import NetworkRequest.BuyPlayerRequest;
import NetworkRequest.GetSoldPlayers;
import NetworkRequest.SearchByClubAndCountry;
import NetworkRequest.SellPlayerRequest;
import ClientRelatedPackage.Main;
import ClientRelatedPackage.NetworkUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import serverPackage.Player;

public class SellTextBoxController {
    String PlayerName;
    NetworkUtil networkUtil;
    Main main;
    @FXML
    private Label playerNameLabel;
    @FXML
    private TextField textField;
    @FXML
    public Button sellButton;
    @FXML
    public Button buyButton;
    @FXML
    public Label PromptLabel;
    @FXML
    public Label PriceLabel;
    @FXML
    public Button Cancel;
    @FXML
    public void SellButtonPressed(ActionEvent actionEvent) {
        Double value = Double.parseDouble(textField.getText());
        SellPlayerRequest sellPlayerRequest = new SellPlayerRequest(PlayerName, value);
        networkUtil.writeToServer(sellPlayerRequest);
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();

        networkUtil.writeToServer(new SearchByClubAndCountry(main.getClubName(), "any"));


    }

    @FXML
    public void CancelButtonPressed(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }
    public void init(Player player, Main main) {
        PlayerName = player.getName();
        this.main = main;
        this.networkUtil = main.getNetworkUtil();
        playerNameLabel.setText(PlayerName);
        PriceLabel.setText(player.getPrice() + "$");
    }
    @FXML
    public void BuyPressed(ActionEvent actionEvent) {
        //Double value = Double.parseDouble(textField.getText());
        System.out.println("Buy pressed");
        BuyPlayerRequest buyPlayerRequest = new BuyPlayerRequest(PlayerName, main.getClubName());
        networkUtil.writeToServer(buyPlayerRequest);
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
        networkUtil.writeToServer(new GetSoldPlayers());
    }
    @FXML
    public Button getSellButton() {
        return sellButton;
    }
    @FXML
    public Button getBuyButton() {
        return this.buyButton;
    }
    public TextField getTextField() {
        return textField;
    }
    public Label getPromptLabel() {
        return PromptLabel;
    }
    public Label getPriceLabel() {
        return PriceLabel;
    }
}
