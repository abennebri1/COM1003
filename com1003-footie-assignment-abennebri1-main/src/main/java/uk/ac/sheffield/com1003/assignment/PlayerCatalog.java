package uk.ac.sheffield.com1003.assignment;

import uk.ac.sheffield.com1003.assignment.codeprovided.*;

import java.util.*;

/**
 * SKELETON IMPLEMENTATION
 */
public class PlayerCatalog extends AbstractPlayerCatalog
{
    /**
     * Constructor
     */
    public PlayerCatalog(String eplFilename, String ligaFilename) {
        super(eplFilename, ligaFilename);
    }

    @Override
    public PlayerPropertyMap parsePlayerEntryLine(String line) throws IllegalArgumentException
    {
        String[] values = line.split(",");
        if (values.length < 29) {
            throw new IllegalArgumentException("Error, invalid number of columns in line: " + line);
        }
        PlayerPropertyMap playerPropertyMap = new PlayerPropertyMap();

        double[] doubleArray = new double[values.length-4];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = Double.parseDouble(values[i+4]);
        }
    
        
        playerPropertyMap.putDetail(PlayerDetail.PLAYER, values[0]);
        playerPropertyMap.putDetail(PlayerDetail.NATION, values[1]);
        playerPropertyMap.putDetail(PlayerDetail.POSITION, values[2]);
        playerPropertyMap.putDetail(PlayerDetail.TEAM, values[3]);
        playerPropertyMap.put(PlayerProperty.AGE, doubleArray[0]);
        playerPropertyMap.put(PlayerProperty.MATCHES, doubleArray[1]);
        playerPropertyMap.put(PlayerProperty.MINUTES, doubleArray[2]);
        playerPropertyMap.put(PlayerProperty.YELLOWCARDS, doubleArray[3]);
        playerPropertyMap.put(PlayerProperty.REDCARDS, doubleArray[4]);
        playerPropertyMap.put(PlayerProperty.GOALS, doubleArray[5]);
        playerPropertyMap.put(PlayerProperty.PKGOALS, doubleArray[6]);
        playerPropertyMap.put(PlayerProperty.PKATTEMPTS, doubleArray[7]);
        playerPropertyMap.put(PlayerProperty.ASSISTS, doubleArray[8]);
        playerPropertyMap.put(PlayerProperty.OWNGOALS, doubleArray[9]);
        playerPropertyMap.put(PlayerProperty.PASSATTEMPTED, doubleArray[10]);
        playerPropertyMap.put(PlayerProperty.PASSCOMPLETED, doubleArray[11]);
        playerPropertyMap.put(PlayerProperty.AERIALSWON, doubleArray[12]);
        playerPropertyMap.put(PlayerProperty.AERIALSLOST, doubleArray[13]);
        playerPropertyMap.put(PlayerProperty.AERIALSWONPERC, doubleArray[14]);
        playerPropertyMap.put(PlayerProperty.TACKLES, doubleArray[15]);
        playerPropertyMap.put(PlayerProperty.TACKLESWON, doubleArray[16]);
        playerPropertyMap.put(PlayerProperty.CLEARANCES, doubleArray[17]);
        playerPropertyMap.put(PlayerProperty.FOULSCOMMITTED, doubleArray[18]);
        playerPropertyMap.put(PlayerProperty.PKCONCEDED, doubleArray[19]);
        playerPropertyMap.put(PlayerProperty.SHOTS, doubleArray[20]);
        playerPropertyMap.put(PlayerProperty.SHOTSTARGET, doubleArray[21]);
        playerPropertyMap.put(PlayerProperty.FOULSDRAWN, doubleArray[22]);
        playerPropertyMap.put(PlayerProperty.CROSSES, doubleArray[23]);
        playerPropertyMap.put(PlayerProperty.PKWON, doubleArray[24]);

    
        return playerPropertyMap;
    }

    @Override
    public void updatePlayerCatalog() {
        List<PlayerEntry> tempList = readDataFile("C:/Users/Adam/Downloads/com1003-footie-assignment-abennebri1-main/com1003-footie-assignment-abennebri1-main/src/main/resources/laliga-2223.csv", League.ALL); //Creates a temporary list with the data from Laliga
        playerEntriesMap.put(League.ALL, new ArrayList<>(readDataFile("C:/Users/Adam/Downloads/com1003-footie-assignment-abennebri1-main/com1003-footie-assignment-abennebri1-main/src/main/resources/epl-2223.csv", League.ALL))); //Creates an array list within PlayerEntriesMap which cointains data from the EPL
        playerEntriesMap.get(League.ALL).addAll(tempList);
    }

    @Override
    public double getMinimumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
            double lowest = 100000;
            int count = 0;
        for (PlayerEntry playerEntry : playerEntryList) {   //For each player entry within the player entries list
            Double value = playerEntry.getProperty(playerProperty); //Uses getProperty to set the 'value' variable to the player property
            if (value<lowest){
                lowest = value;
                count++;
            }
        }
            if (count == 0) {
                throw new NoSuchElementException("There are no values for " + playerProperty.name());
                }   
                //If the count is 0, meaning there are no values for the property in the list, it throws a NoSuchElementException and tells the user
        
           return lowest;
    }

    @Override
    public double getMaximumValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
            throws NoSuchElementException {
        double highest = -1;
            int count = 0;
        for (PlayerEntry playerEntry : playerEntryList) {   //For each player entry within the player entries list
            Double value = playerEntry.getProperty(playerProperty); //Uses getProperty to set the 'value' variable to the player property
            if (value>highest){
                highest = value;
                count++;
            }
        }
            if (count == 0) {
                throw new NoSuchElementException("There are no values for " + playerProperty.name());
                }   
                //If the count is 0, meaning there are no values for the property in the list, it throws a NoSuchElementException and tells the user
        
           return highest;
    }

    @Override
    public double getMeanAverageValue(PlayerProperty playerProperty, List<PlayerEntry> playerEntryList)
    throws NoSuchElementException {

    double total = 0.0;
    int count = 0;
    //Creates a total and a count variable and sets them both to 0
    for (PlayerEntry playerEntry : playerEntryList) {   //For each player entry within the player entries list
        Double value = playerEntry.getProperty(playerProperty); //Uses getProperty to set the 'value' variable to the player property
   
        total += value; //adds value to the total
        count++;
    
    }
    if (count == 0) {
        throw new NoSuchElementException("There are no values for " + playerProperty.name());
        }   
        //If the count is 0, meaning there are no values for the property in the list, it throws a NoSuchElementException and tells the user
    return total / count; 
    //Returns the total divided by the count, which is the average
}

    

    @Override
    public List<PlayerEntry> getFirstFivePlayerEntries(League type)
    {
        // TODO implement
        return new ArrayList<>();
    }

}
