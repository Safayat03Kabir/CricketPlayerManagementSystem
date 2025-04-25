package serverPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import NetworkRequest.*;

public class ClientHandler implements Runnable {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    Socket socket;
    playerDatabase pd;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    ClientHandler(Socket socket, playerDatabase pd) {
        this.socket = socket;
        this.pd = pd;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clients.add(this);
    }
    @Override
    public void run() {
        while(socket.isConnected()) {
            try {
                Object o = ois.readObject();
                System.out.println("Object received: " + o);
                if(o instanceof LoginRequest) {
                    System.out.println("LoginReqest Received");
                    LoginRespond lr;
                    if(pd.Validate(((LoginRequest) o).GetClientName(), ((LoginRequest) o).GetPassword())) {
                        lr = new LoginRespond(true, ((LoginRequest) o).GetClientName());
                        oos.writeUnshared(lr);
                    }
                    else {
                        lr = new LoginRespond(false, "BanglarBug");
                        oos.writeUnshared(lr);
                    }
                    //validate login and return necessary object

                }
                else if(o instanceof LogoutRequest){
                    System.out.println("LogoutReqest Received");
                    //close everything

                }
                else if(o instanceof SearchByName){
                    System.out.println("SearchByName Received");
                    SearchByName searchByName = (SearchByName)o;
                    Player p = pd.searchByName(searchByName.getName());
                    ArrayList<Player> players = new ArrayList<>();
                    players.add(p);
                    oos.writeUnshared(players);
                }
                else if(o instanceof SearchByClubAndCountry){
                    System.out.println("SearchByCountry Received");
                    SearchByClubAndCountry sbcc = (SearchByClubAndCountry) o; //sbcc = search by club and country
                    ArrayList<Player> array;
                    array = pd.searchByClubAndCountry(sbcc.getClubName(), sbcc.getCountryName());
                    for(Player p1 : array) {
                        System.out.println(p1);
                    }
                    oos.writeUnshared(array);
                }
                else if(o instanceof SearchByPosition){
                    System.out.println("SearchByPosition Received");
                    SearchByPosition searchByPosition = (SearchByPosition) o;
                    ArrayList<Player> array = new ArrayList<>();
                    array = pd.searchByPosition(searchByPosition.getPosition());
                    oos.writeUnshared(array);
                }
                else if(o instanceof SearchBySalaryRange){
                    System.out.println("SearchBySalaryRange Received");
                    SearchBySalaryRange searchBySalaryRange = (SearchBySalaryRange) o;
                    ArrayList<Player> array;
                    array = pd.searchBySalaryRange(searchBySalaryRange.getLowerBound(), searchBySalaryRange.getUpperBound());
                    oos.writeUnshared(array);
                }
                else if(o instanceof MaximumAgeOfClub){
                    System.out.println("MaximumAgeOfClub Received");
                    MaximumAgeOfClub maxAgeOfClub = (MaximumAgeOfClub) o;
                    ArrayList<Player> array;
                    array = pd.maxAgeOfClub(maxAgeOfClub.getClubName());
                    oos.writeUnshared(array);
                }
                else if(o instanceof MaximumSalaryOfClub){
                    System.out.println("MaximumSalaryOfClub Received");
                    MaximumSalaryOfClub maxSalaryOfClub = (MaximumSalaryOfClub) o;
                    ArrayList<Player> array;
                    array = pd.maxSalaryOfClub(maxSalaryOfClub.getClubName());
                    oos.writeUnshared(array);
                }
                else if(o instanceof MaximumHeightOfClub){
                    System.out.println("MaximumAgeOfClub Received");
                    MaximumHeightOfClub maxAgeOfClub = (MaximumHeightOfClub) o;
                    ArrayList<Player> array;
                    array = pd.maxHeightOfClub(maxAgeOfClub.getClubName());
                    oos.writeUnshared(array);
                }
                else if(o instanceof GetSoldPlayers){
                    System.out.println("GetSoldPlayers Received");
                    ArrayList<Player> array;
                    array = pd.soldPlayerList();
                    oos.writeUnshared(array);
                }
                else if(o instanceof SellPlayerRequest){
                    System.out.println("SellPlayerRequest Received");
                    SellPlayerRequest sellPlayerRequest = (SellPlayerRequest) o;
                    pd.sellPlayer(sellPlayerRequest.getPlayerName(), sellPlayerRequest.getPrice());

                    for (ClientHandler client : clients) {
                        try {
                            client.oos.writeUnshared(new UpdateGuiRequest());
                            client.oos.reset();
                            client.oos.flush();
                        } catch (IOException e) {
                            System.out.println("Error sending sell update to client: " + e.getMessage());
                        }
                    }
                }
                else if(o instanceof BuyPlayerRequest){
                    BuyPlayerRequest buyPlayerRequest = (BuyPlayerRequest) o;
                    System.out.println("Player " + buyPlayerRequest.getPlayerName() + " Buy Request" + buyPlayerRequest.getBuyerName());
                    pd.buyPlayer(buyPlayerRequest.getPlayerName(), buyPlayerRequest.getBuyerName());

                    for (ClientHandler client : clients) {
                        try {
                            client.oos.writeUnshared(new UpdateGuiRequest());
                            client.oos.reset();
                            client.oos.flush();
                        } catch (IOException e) {
                            System.out.println("Error sending sell update to client: " + e.getMessage());
                        }
                    }


                }
                else if(o instanceof GetPlayerDetails){
                    oos.writeUnshared(pd.searchByName(((GetPlayerDetails) o).getPlayerName()));
                }
                else if(o instanceof SignUpRequest){
                    SignUpRequest signUpRequest = (SignUpRequest) o;
                    pd.AddClub(signUpRequest.getClubName(),  signUpRequest.getPassword());
                }
                else if(o instanceof AddPlayerRequest){
                    AddPlayerRequest addPlayerRequest = (AddPlayerRequest) o;
                    Player p = addPlayerRequest.getPlayer();
                    pd.addPlayer(p);
                    oos.writeUnshared(new UpdateGuiRequest());
                }
                oos.reset();
                oos.flush();

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
