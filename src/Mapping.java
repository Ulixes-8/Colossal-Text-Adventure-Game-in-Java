import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Mapping {

    public static final int INITIAL_LOCATION = 95;


    static LocationMap locationMap = new LocationMap();


    Map<String, String> vocabulary = new HashMap<String, String>();


    FileLogger fileLogger = new FileLogger();


    ConsoleLogger consoleLogger = new ConsoleLogger();

    public Mapping() {

        //QUIT
        vocabulary.put("QUIT", "Q");
        vocabulary.put("Q", "Q");
        //DOWN
        vocabulary.put("DOWN", "D");
        vocabulary.put("D", "D");
        //UP
        vocabulary.put("U", "U");
        vocabulary.put("UP", "U");
        //NORTH
        vocabulary.put("NORTH", "N");
        vocabulary.put("N", "N");
        //NORTHWEST
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("NW", "NW");
        //NORTHEAST
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NE", "NE");
        //SOUTH
        vocabulary.put("SOUTH", "S");
        vocabulary.put("S", "S");
        //SOUTHWEST
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("SW", "SW");
        //SOUTHEAST
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SE", "SE");
        //WEST
        vocabulary.put("WEST", "W");
        vocabulary.put("W", "W");
        //EAST
        vocabulary.put("EAST", "E");
        vocabulary.put("E", "E");

    }

    public void mapping() {


        Scanner userInput = new Scanner(System.in);


        Location location = locationMap.get(INITIAL_LOCATION);


        while (true) {




            fileLogger.log(location.getDescription());
            consoleLogger.log(location.getDescription());





            if (!location.getExits().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\nAvailable exits are ");
                for (String keys : location.getExits().keySet()) {
                    sb.append(keys).append(", ");
                }
                sb.append("\n");
                fileLogger.log(sb.toString());
                consoleLogger.log(sb.toString());
            }



            String input = userInput.nextLine().toUpperCase().strip();


//            String[] words = new String[1];
//            if (input.contains(" ")) {
//                words = input.split(" ");
////                for (int j = 0; j < words.length; j++) {
////                    if (words[j].charAt(words[j].length() - 1) == ',') {
////                        words[j] = words[j].substring(0, words[j].length() - 1);
////                    }
////                }
//            } else {
//                words[0] = input.strip();
//            }

            String[] words = input.split(" ");


            //TRY FLIPPING IT
            int hitIndex = -1;
            for (String keys : vocabulary.keySet()) {
                for (int j = 0; j < words.length; j++) {
                    if (keys.equals(words[j])) {
                        hitIndex = j;
                    }
                }
            }

            if (hitIndex != -1 && words[hitIndex].length() < 3 && words.length > 1 && !words[hitIndex].equals("Q") && !words[hitIndex].equals("UP")) {
                words[hitIndex] = "NONSENSE";
            }




            if (hitIndex == -1 || words[hitIndex].equals("NONSENSE")) {
                String message = "You cannot go in that direction\n";
                consoleLogger.log(message);
                fileLogger.log(message);

            } else {


                switch (words[hitIndex]) {
                    case "SOUTH":
                        words[hitIndex] = "S";
                        break;
                    case "QUIT":
                        words[hitIndex] = "Q";
                        break;
                    case "DOWN":
                        words[hitIndex] = "D";
                        break;
                    case "UP":
                        words[hitIndex] = "U";
                        break;
                    case "NORTH":
                        words[hitIndex] = "N";
                        break;
                    case "NORTHEAST":
                        words[hitIndex] = "NE";
                        break;
                    case "NORTHWEST":
                        words[hitIndex] = "NW";
                        break;
                    case "SOUTHWEST":
                        words[hitIndex] = "SW";
                        break;
                    case "SOUTHEAST":
                        words[hitIndex] = "SE";
                        break;
                    case "WEST":
                        words[hitIndex] = "W";
                        break;
                    case "EAST":
                        words[hitIndex] = "E";
                        break;

                }

                boolean match = false;
                for (String keys : location.getExits().keySet()) {
                    if (keys.equals(words[hitIndex])) {
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    String message = "You cannot go in that direction\n";
                    consoleLogger.log(message);
                    fileLogger.log(message);
                } else {
                    location = locationMap.get(location.getExits().get(words[hitIndex]));
                }
                if (words[hitIndex].equals("Q") || words[hitIndex].equals("QUIT")) {
                    consoleLogger.log(location.getDescription());
                    fileLogger.log(location.getDescription());
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {


        Mapping game = new Mapping();
        game.mapping();
        System.out.println();

    }

}
