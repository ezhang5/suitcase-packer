import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class WeatherData {

    private final Map<String, Hashtable<String, Integer>> tableOfAirportTables;

    /**
     * Constructor
     */
    public WeatherData() {
        List<File> listOfCSV = CSVReader.getFile("us-weather-history");
        this.tableOfAirportTables = createAirportTables(listOfCSV);
    }

    /**
     * Creates a map whose key is an airport code and value are hashtables<date, average temperature)
     * 
     * @param List<File> fileList
     * @return Map of Airport Hashtables
     */
    private static Map<String,Hashtable<String, Integer>> createAirportTables (List<File> fileList) {
        Map<String, Hashtable<String, Integer>> airportTable = new HashMap<String, Hashtable<String, Integer>>();
        for (File fileName : fileList) {
            List<String> fileLines = CSVReader.getCSVLines(fileName);
            Hashtable<String, Integer> hashTable = CSVReader.getWeatherTable(fileLines);
            airportTable.put(fileName.getName().replace(".csv",""), hashTable ); 
        }
        return airportTable;
    }

    /**
     * @param airport
     * @param date
     * @return
     */
    public int getAvgTemp (String airport, String date) {
        return tableOfAirportTables.get(airport).get(date);

    }

    /**
     * Testing 
     * 
     * @param args
     */
    public static void main(String[] args) {
        WeatherData test1 = new WeatherData();
        System.out.println("expect 81 : got " + test1.getAvgTemp("KCLT","7-1"));
        System.out.println("expect 100 : got " + test1.getAvgTemp("KPHX", "6-24"));
    }
}
