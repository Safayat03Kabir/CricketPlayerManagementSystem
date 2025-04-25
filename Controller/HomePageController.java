package Controller;

import NetworkRequest.*;
import ClientRelatedPackage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomePageController {
    private Main mainFxml;
    private int SearchOption = 0;
    @FXML
    private AnchorPane homePage;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button SearchByClubAndCountry;
    @FXML
    private TextField Search1;
    @FXML
    private TextField Search2;
    @FXML
    private Button Cancel;
    @FXML
    private Button Search;
    @FXML
    private Button Name;
    @FXML
    private Button Position;
    @FXML
    private Button SalaryRange;
    @FXML
    private Button Market;
    @FXML
    private Button MaximumSalary;
    @FXML
    private Button MaximumAge;
    @FXML
    private Button MaximumHeight;
    @FXML
    private Button AddPlayerButton;
    @FXML
    private Label ClubName;
    @FXML
    private ImageView ClubLogo;

    public GridPane getGrid() {
        return grid;
    }
    public ScrollPane getScroll() {
        return scroll;
    }
    public ImageView getClubLogo(){return ClubLogo;}

    @FXML
    void SearchByClubAndCountryPressed(ActionEvent event) {
        SearchOption = 3;
        Search1.clear();
        Search2.clear();
        Search2.setVisible(true);
        Search1.setPromptText("Enter Club Name");
        Search2.setPromptText("Enter Country");
        Search.setVisible(true);
        Cancel.setVisible(true);

    }
    @FXML
    void CancelPressed(ActionEvent event) {
        SearchOption = 0;
        mainFxml.writeToServer(new SearchByClubAndCountry(mainFxml.getClubName(), "Any"));
        //mainFxml.updateGUI((ArrayList<Player>) mainFxml.writeToServer(new SearchByClubAndCountry(mainFxml.getClubName(), "Any")));
        Search2.setVisible(false);
        Cancel.setVisible(false);
        Search.setVisible(false);
        Search1.clear();
        Search2.clear();
        mainFxml.setIsInMarket(false);
    }
    @FXML
    void SearchPressed(ActionEvent event) {
        if(SearchOption == 1) {
            String PlayerName = Search1.getText();
            SearchByName searchByName = new SearchByName(PlayerName);
            mainFxml.writeToServer(searchByName);
            //mainFxml.updateGUI((ArrayList<Player>) mainFxml.writeToServer(searchByName));
        }
        else if(SearchOption == 2) {
            String PlayerPosition = Search1.getText();
            SearchByPosition searchByPosition = new SearchByPosition(PlayerPosition);
            mainFxml.writeToServer(searchByPosition);
            //mainFxml.updateGUI((ArrayList<Player>) mainFxml.writeToServer(searchByPosition));
        }
        else if(SearchOption == 3) {
            String PlayerClub = Search1.getText();
            String PlayerCountry = Search2.getText();
            SearchByClubAndCountry searchByClubAndCountry = new SearchByClubAndCountry(PlayerClub, PlayerCountry);
            mainFxml.writeToServer(searchByClubAndCountry);

            //mainFxml.updateGUI((ArrayList<Player>) mainFxml.writeToServer(searchByClubAndCountry));
        }
        else if(SearchOption == 4) {
            Double max = Double.parseDouble(Search1.getText());
            Double min = Double.parseDouble(Search2.getText());
            mainFxml.writeToServer(new SearchBySalaryRange(max, min));
        }
        else if(SearchOption >= 5) {
            String Club = Search1.getText();
            if(SearchOption == 5) mainFxml.writeToServer(new MaximumSalaryOfClub(Club));
            else if(SearchOption == 6) mainFxml.writeToServer(new MaximumAgeOfClub(Club));
            else if(SearchOption == 7) {mainFxml.writeToServer(new MaximumHeightOfClub(Club));}
            //else if(SearchOption == 8) {mainFxml.writeToServer(new MaximumSalaryOfClub(Club));}
        }
    }
    @FXML
    void NamePressed(ActionEvent event) {
        SearchOption = 1;
        Search2.setVisible(false);
        Search1.setVisible(true);
        Search1.clear();
        Search1.setPromptText("Enter Player Name");
        Search.setVisible(true);
        Cancel.setVisible(true);

    }
    @FXML
    void PositionPressed(ActionEvent event) {
        SearchOption = 2;
        Search1.setVisible(true);
        Search2.setVisible(false);
        Search1.clear();
        Search1.setPromptText("Enter Player Position");
        Cancel.setVisible(true);
        Search.setVisible(true);

    }
    @FXML
    void ShowMarketPressed(ActionEvent event) {
        mainFxml.writeToServer(new GetSoldPlayers());
        mainFxml.setIsInMarket(true);
        Cancel.setVisible(true);
        //mainFxml.updateGUI((ArrayList<Player>) mainFxml.writeToServer(new GetSoldPlayers()));
    }
    @FXML
    void SalaryRangePressed(ActionEvent event) {
        SearchOption = 4;
        Search1.setVisible(true);
        Search2.setVisible(true);
        Search1.setPromptText("Enter Maximum Salary");
        Search2.setPromptText("Enter Minimum Salary");
        Cancel.setVisible(true);
        Search.setVisible(true);
    }
    @FXML
    void MaximumSalaryPressed(ActionEvent event) {
        SearchOption = 5;
        Search1.setVisible(true);
        Search2.setVisible(false);
        Cancel.setVisible(true);
        Search.setVisible(true);
        Search1.setPromptText("Enter ClubName");
    }
    @FXML
    void MaximumAgePressed(ActionEvent event) {
        SearchOption = 6;
        Search1.setVisible(true);
        Search2.setVisible(false);

        Cancel.setVisible(true);
        Search.setVisible(true);
        Search1.setPromptText("Enter ClubName");
    }
    @FXML
    void MaximumHeightPressed(ActionEvent event) {
        SearchOption = 7;
        Search1.setVisible(true);
        Search2.setVisible(false);
        Cancel.setVisible(true);
        Search.setVisible(true);
        Search1.setPromptText("Enter ClubName");

    }
    public void init(Main mainFxml) {
        this.mainFxml = mainFxml;
    }
    @FXML
    public void TotalSalaryPressed(ActionEvent event) {
        SearchOption = 8;
        Search1.setVisible(true);
        Search2.setVisible(false);
        Cancel.setVisible(true);
        Search.setVisible(true);
        Search1.setPromptText("Enter ClubName");
    }
    public Label GetClubName() {
        return ClubName;
    }
    @FXML
    void AddPlayerPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/AddPlayer.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        AddPlayerController controller = fxmlLoader.getController();
        //controller.init(player, main);
        controller.setMain(mainFxml);
        Dialog dialog = new Dialog();
        dialog.setDialogPane(dialogPane);
        dialog.initStyle(StageStyle.UNDECORATED);

        dialog.showAndWait();

    }



}
