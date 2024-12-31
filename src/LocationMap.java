import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    static {

        FileLogger fileLogger = new FileLogger();



        ConsoleLogger consoleLogger = new ConsoleLogger();



        try (BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            ArrayList<String> numbers = new ArrayList<>();
            ArrayList<String> sentences = new ArrayList<>();
            StringBuilder toLogger = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                int index = 0;
                String numHolder = "";
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != ',') {
                        numHolder += s.charAt(i);
                        index++;
                    } else {
                        break;
                    }
                }
                sentences.add(s.substring(index + 1));
                numbers.add(numHolder);
            }
            toLogger.append("Available locations:\n");
            for (int i = 0; i < numbers.size(); i++) {
                toLogger.append(numbers.get(i)).append(": ").append(sentences.get(i)).append("\n");
            }
            toLogger.setLength(toLogger.length() - 1);
            fileLogger.log(toLogger.toString());
            consoleLogger.log(toLogger.toString());
            int[] numbersParsed = new int[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                numbersParsed[i] = Integer.parseInt(numbers.get(i));
            }

            for (int i = 0; i < numbers.size(); i++) {
                locations.put(numbersParsed[i], new Location(numbersParsed[i], sentences.get(i), new LinkedHashMap<String, Integer>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        try (BufferedReader br = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {

            int rows = 0;
            String iFuckWith2DArrays;
            while ((iFuckWith2DArrays = br.readLine()) != null) {
                rows++;
            }

            String[][] directions = new String[rows][3];
            StringBuilder toLogger = new StringBuilder();

            ArrayList<Integer> locs = new ArrayList<>();
            ArrayList<String> directs = new ArrayList<>();
            ArrayList<Integer> dests = new ArrayList<>();


            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
                int index = 0;
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    directions[index] = s.split(",");
                    index++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            toLogger.append("\nAvailable directions:\n");
            int k = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 3; j++) {
                    toLogger.append(directions[i][j]).append(": ");
                    if (j == 0) {
                        locs.add(Integer.parseInt(directions[i][j]));
                    } else if (j == 1) {
                        directs.add(directions[i][j]);
                    } else {
                        dests.add(Integer.parseInt(directions[i][j]));
                    }

                    if (k % 3 == 0) {
                        toLogger.setLength(toLogger.length() - 2);
                        toLogger.append("\n");
                    }
                    k++;
                }
            }
//            toLogger.deleteCharAt(toLogger.length() - 1);

            fileLogger.log(toLogger.toString());
            consoleLogger.log(toLogger.toString());

            for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
                if (entry.getKey() < locations.size()) {
                    for (int j = 0; j < locs.size(); j++) {
                        if (Objects.equals(entry.getKey(), locs.get(j))) {
                            entry.getValue().addExit(directs.get(j), dests.get(j));
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
