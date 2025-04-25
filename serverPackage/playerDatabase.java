package serverPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class playerDatabase {
    private static volatile ArrayList<Player> Players;
    private static volatile HashMap<String, String> Passwords;
    public playerDatabase(){
        Players = new ArrayList<>();
        try{
            File playersFile = new File("src/textFiles/Players.txt");
            File passwordFile = new File("src/textFiles/Passwords.txt");


            BufferedReader PlayerFileReader = new BufferedReader(new FileReader(playersFile));
            BufferedReader PasswordFileReader = new BufferedReader(new FileReader(passwordFile));

            String line;
            while((line = PlayerFileReader.readLine()) != null){
                String[] args = line.split(",");
                Player p = new Player(args[0], args[1], args[4], args[5], Integer.parseInt(args[2]), args[6].isEmpty() ? -1 : Integer.parseInt(args[6]), Double.parseDouble(args[7]), Double.parseDouble(args[3]), args.length >= 9 && Boolean.parseBoolean(args[8]), args.length == 10 ? Double.parseDouble(args[9]): 0.0, args.length == 11 ? args[10] : null);

                Players.add(p);

            }
            PlayerFileReader.close();
            Passwords = new HashMap<>();
            while ((line = PasswordFileReader.readLine()) != null){
                String[] args = line.split(",");
                Passwords.put(args[0], args[1]);
            }

        }
        catch(Exception e){
            System.out.println("Error loading players for database : " + e.getMessage());
        }

    }

    public synchronized Player searchByName(String name) {
        for (Player player : Players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }
    public synchronized ArrayList<Player> searchByClubAndCountry(String club, String country){
        ArrayList<Player> playerList = new ArrayList<>();
        for(Player p : Players){
            if((club.equalsIgnoreCase("Any") || p.getClub().equalsIgnoreCase(club)) && (country.equalsIgnoreCase("Any") || p.getCountry().equalsIgnoreCase(country))){
                playerList.add(p);
            }
        }
        return playerList;
    }
    public synchronized ArrayList<Player> searchByPosition(String position){
        ArrayList<Player> playerList = new ArrayList<>();
        for(Player p : Players){
            if(p.getPosition().equalsIgnoreCase(position)){
                playerList.add(p);
            }
        }
        return playerList;

    }
    public synchronized ArrayList<Player> searchBySalaryRange(double lower, double upper){
        ArrayList<Player> playerList = new ArrayList<>();
        for(Player p : Players){
            if(p.getWeeklySalary() >= lower && p.getWeeklySalary() <= upper){
                playerList.add(p);
            }
        }
        return playerList;

    }

    public synchronized  HashMap<String, Integer> countryWisePlayerCount(){
        HashMap<String, Integer> map = new HashMap<>();
        for(Player p : Players){
            String country = p.getCountry().toLowerCase();
            if (map.containsKey(country)){
                map.put(country, map.get(country) + 1);
            }
            else{
                map.put(country, 1);
            }
        }
        return map;
    }


    public synchronized void addPlayer(Player player){
        for(Player p : Players){
            if(p.getName().equalsIgnoreCase(player.getName())){
                System.out.println("Player already exists");
                return;
            }
        }
        Players.add(player);
    }
    void showDatabase(){
        for(Player p : Players){
            System.out.println(p.toString());
        }
    }
    public synchronized  ArrayList<Player> maxSalaryOfClub(String clubName) {
        ArrayList<Player> maxSalaryPlayers = new ArrayList<>();
        double maxSalary = 0;
        for(Player p : Players){
            if(p.getWeeklySalary() > maxSalary && p.getClub().equalsIgnoreCase(clubName)){
                maxSalary = p.getWeeklySalary();
            }
        }
        if(maxSalary == 0){

            return maxSalaryPlayers;
        }
        for(Player p : Players){
            if(p.getWeeklySalary() == maxSalary && p.getClub().equalsIgnoreCase(clubName)){
                maxSalaryPlayers.add(p);
            }
        }
        return maxSalaryPlayers;
    }
    public synchronized ArrayList<Player> maxHeightOfClub(String clubName) {
        ArrayList<Player> maxHeightPlayers = new ArrayList<>();
        double maxHeight = 0;
        for(Player p : Players){
            if(p.getHeight() > maxHeight && p.getClub().equalsIgnoreCase(clubName)){
                maxHeight = p.getHeight();
            }
        }
        if(maxHeight == 0){
            System.out.println("No Such club exists");
            return maxHeightPlayers;
        }
        for(Player p : Players){
            if(p.getHeight() == maxHeight && p.getClub().equalsIgnoreCase(clubName)){
                maxHeightPlayers.add(p);
            }
        }
        return maxHeightPlayers;
    }
    public synchronized ArrayList<Player> maxAgeOfClub(String clubName) {
        ArrayList<Player> maxAgePlayers = new ArrayList<>();
        double maxAge = 0;
        for(Player p : Players){
            if(p.getAge() > maxAge && p.getClub().equalsIgnoreCase(clubName)){
                maxAge = p.getAge();
            }
        }
        if(maxAge == 0){
            System.out.println("No Such club exists");
            return maxAgePlayers;
        }
        for(Player p : Players){
            if(p.getAge() == maxAge && p.getClub().equalsIgnoreCase(clubName)){
                maxAgePlayers.add(p);
            }
        }
        return maxAgePlayers;
    }
    public synchronized double totalSalary(String clubName){
        double yearlySal = 0;
        for(Player p : Players){
            if(p.getClub().equalsIgnoreCase(clubName)){
                yearlySal += p.getWeeklySalary();

            }
        }
        if(yearlySal == 0){
            System.out.println("No such club exists");
        }
        return yearlySal;
    }
    public synchronized Boolean Validate(String ClubName, String Password){
        if(Passwords.containsKey(ClubName) && Passwords.get(ClubName).equals(Password)){
            return true;
        }
        else{
            return false;
        }
    }
    public synchronized ArrayList<Player> soldPlayerList(){
        ArrayList<Player> soldPlayers = new ArrayList<>();
        for(Player p : Players){
            if(p.getIsBeingSold()) soldPlayers.add(p);
        }
        return soldPlayers;
    }
    public synchronized void sellPlayer(String playerName, Double price){
        for (Player p : Players) {
            if (p.getName().equalsIgnoreCase(playerName)) {
                p.setIsBeingSold(true);
                p.setPrice(price);
                System.out.println("Player " + playerName + " is now marked as sold.");
                return;
            }
        }
    }
    public synchronized void buyPlayer(String playerName, String clubName){
        for (Player p : Players) {
            if (p.getName().equalsIgnoreCase(playerName)) {
                p.setIsBeingSold(false);
                p.setClub(clubName);
                System.out.println("Player " + playerName + "is Sold to " + p.getClub());


            }
        }
    }
    public void savePlayersToFile(){
        try{
            File playersFile = new File("src/textFiles/Players.txt");
            FileWriter fileWriter = new FileWriter(playersFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            DecimalFormat df = new DecimalFormat("#.##");
            for(Player p : Players){
                String formattedSalary = df.format(p.getWeeklySalary());
                writer.write(p.getName() + "," + p.getCountry() + "," + p.getAge() + "," + p.getHeight() + "," + p.getClub() + "," + p.getPosition() + "," + p.getNumber() + "," + formattedSalary + "," + p.getIsBeingSold() + "," + p.getPrice() + "," + p.getLink());
                writer.newLine();
            }


            writer.close();

        }
        catch(Exception e){
            System.out.println("Error loading players File for adding player: " + e.getMessage());
        }
    }
    public void savePasswordsToFile() throws IOException {
        File playersFile = new File("src/textFiles/Passwords.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playersFile, false))) {
            for (String ClubName : Passwords.keySet()) {
                writer.write(ClubName + "," + Passwords.get(ClubName));
                writer.newLine();
            }
        }
    }

    public void AddClub(String clubName, String password){
        Passwords.put(clubName, password);
    }
}
