package ClientRelatedPackage;

import NetworkRequest.*;
import Controller.HomePageController;
import Controller.PlayerCardController;
import Controller.PlayerDetailsController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import serverPackage.Player;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomePageUpdater implements Runnable {
    private final Main main;
    private final HomePageController homePageController;
    private final NetworkUtil networkUtil;
    private final String ClubName;

    public HomePageUpdater(HomePageController homePageController, Main main) {
        this.homePageController = homePageController;
        this.networkUtil = main.getNetworkUtil();
        this.ClubName = main.getClubName();
        this.main = main;
        String imagePath = "/resources/pictures/" + main.getClubName() + ".png";
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            // If the player's image is missing, load a default image
            imageStream = getClass().getResourceAsStream("/resources/pictures/Default.png");
        }
        homePageController.getClubLogo().setImage(new Image(imageStream));
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        System.out.println("Up and Running");
        while (true) {
            Object o = networkUtil.listenFromServer();
            System.out.println("Object received: " + o);

            switch (o) {
                case LoginRespond loginRespond -> Platform.runLater(() -> {
                    if (loginRespond.getIsValid()) {
                        System.out.println("Login Successful");
                        SearchByClubAndCountry searchByClubAndCountry = new SearchByClubAndCountry(loginRespond.getClubName(), "Any");
                        networkUtil.writeToServer(searchByClubAndCountry);
                    } else {
                        System.out.println("Login Failed");
                    }
                });
                case ArrayList<?> objects -> {
                    ArrayList<Player> players = new ArrayList<>((ArrayList<Player>) o);

                    Platform.runLater(() -> {
                        GridPane grid = homePageController.getGrid();
                        if (grid == null) {
                            System.out.println("GridPane is null");
                            return;
                        }

                        grid.getChildren().clear();
                        grid.getChildren().removeAll(grid.getChildren());
                        String address = "/resources/fxml/PlayerCard.fxml";
                        int columns = 3; // Number of columns per row

                        for (int i = 0; i < players.size(); i++) {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource(address));

                            try {
                                AnchorPane anchorPane = fxmlLoader.load();
                                PlayerCardController playerCard = fxmlLoader.getController();
                                playerCard.setPlayerData(players.get(i), main);

                                // Update buttons based on player's status
                                if (players.get(i).getIsBeingSold()) {
                                    playerCard.getSellButton().setVisible(false);
                                    playerCard.getBuyButton().setVisible(!players.get(i).getClub().equals(ClubName));
                                } else {
                                    playerCard.getBuyButton().setVisible(false);
                                    playerCard.getSellButton().setVisible(players.get(i).getClub().equals(ClubName));
                                }

                                // Add to grid
                                int column = i % columns;
                                int row = i / columns;
                                grid.add(anchorPane, column, row);
                                GridPane.setMargin(anchorPane, new Insets(5));

                            } catch (IOException e) {
                                System.out.println("Error loading FXML in HomePageUpdater");
                                e.printStackTrace();
                            }
                        }
                    });
                }
                case UpdateGuiRequest updateGuiRequest -> Platform.runLater(() -> {
                    if (main.getIsInMarket()) {
                        main.writeToServer(new GetSoldPlayers());
                    } else {
                        main.writeToServer(new SearchByClubAndCountry(ClubName, "Any"));
                    }
                });
                case Player player -> Platform.runLater(() -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/PlayerDetails.fxml"));
                    try {
                        DialogPane dialogPane = fxmlLoader.load();
                        PlayerDetailsController playerDetailsController = fxmlLoader.getController();
                        playerDetailsController.init(player);
                        Dialog dialog = new Dialog();
                        dialog.setDialogPane(dialogPane);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        dialog.showAndWait();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PlayerDetailsController controller = fxmlLoader.getController();
                });
                case null, default -> System.out.println("Unknown object received");
            }
        }
    }

}





