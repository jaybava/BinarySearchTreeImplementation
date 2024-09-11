// import necessary libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interface {

    // create a BST dictionary
    private static final BSTDictionary dictionary = new BSTDictionary();
    // use StringReader to read user input
    private static final StringReader keyboard = new StringReader();
    // use given classes to implement SoundPlayer, PictureViewer and HTML browser
    private static final SoundPlayer player = new SoundPlayer();
    private static final PictureViewer viewer = new PictureViewer();
    private static final ShowHTML browser = new ShowHTML();


    // main method
    public static void main(String[] args) {
        //  check if there is only one command-line argument
        if (args.length != 1) {
            // if not prints proper usage of program and exits program
            System.out.println("Usage: java Interface inputFile");
            return;
        }

        // get the input file name and store under string inputFile
        String inputFile = args[0];

        // attempt to open the input file with the Buffered Reader
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // read line by line into the label variable
            String label;
            // read until end of file is reached
            while ((label = reader.readLine()) != null) {
                // read the line after label line and store in variable dataLine
                String dataLine = reader.readLine();
                // if the dataLine is equal to null break out of while loop
                if (dataLine == null) {
                    break;
                }
                // pass the label and dataLine to the processRecord function
                processRecord(label, dataLine);
            }
        // if we cannot open the file throw an Exception error
        } catch (IOException | DictionaryException e) {
            e.printStackTrace();
        }

        // after records are processed we enter the commandLoop which handles user commands
        commandLoop();
    }

    // method to process records when reading the input file
    private static void processRecord(String label, String dataLine) throws DictionaryException {
        // filter if either label or dataLine is null
        if(label == null || dataLine == null){
            System.out.println("Invalid record: Label or Data is null.");
            return;
        }

        // create two variables the type and data
        int type;
        String data;

        // check if dataLine starts with certain symbols and assign correct type
        if (dataLine.startsWith("-")) {
            type = 3;
            data = dataLine.substring(1);
        } else if (dataLine.startsWith("+")) {
            type = 4;
            data = dataLine.substring(1);
        } else if (dataLine.startsWith("*")) {
            type = 5;
            data = dataLine.substring(1);
        } else if (dataLine.startsWith("/")) {
            type = 2;
            data = dataLine.substring(1);
        // check if dataLine ends with a certain file extension and assign correct type
        } else if (dataLine.endsWith(".gif")) {
            type = 7;
            data = dataLine;
        } else if (dataLine.endsWith(".jpg")) {
            type = 6;
            data = dataLine;
        } else if (dataLine.endsWith(".html")) {
            type = 8;
            data = dataLine;
        // if dataLine hasn't been filtered out already assign the type of 1
        } else {
            type = 1;
            data = dataLine;
        }

        // create a new record, and new key with correct parameters
        Record record = new Record(new Key(label, type), data);
        // put the record in the BST dictionary
        dictionary.put(record);
    }

    // method to process all the commands given by user
    private static void commandLoop() {
        // enter infinite loop
        while (true) {
            // read input from user
            String line = keyboard.read("Enter next command: ");
            // if input is null or empty go to next iteration of loop
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            // split input into two parts using the space character
            String[] parts = line.split(" ", 2);
            // assign first part as command and second part as argument if it is greater than 1 in length or an empty string otherwise
            String command = parts[0].trim();
            String argument = (parts.length > 1) ? parts[1].trim() : ""; // trim used to remove trailing whitespace

            // switch statement to handle different commands
            switch (command) {
                // if command is "define"
                case "define":
                    // fetch record with correct key type and argument
                    Record recordD = dictionary.get(new Key(argument, 1));
                    // if the record isn't null
                    if (recordD != null){
                        // print the definition
                        System.out.println(recordD.getDataItem());
                    // if record is null print output to user
                    } else {
                        System.out.println("The word " + argument + " is not in the dictionary");
                    }
                    break;
                // if command is "translate"
                case "translate":
                    // similar logic to define
                    Record recordT = dictionary.get(new Key(argument, 2));
                    if (recordT != null){
                        System.out.println(recordT.getDataItem());
                    } else {
                        System.out.println("There is no definition for word " + argument);

                    }
                    break;
                // if command is "sound"
                case "sound":
                    // fetch correct record
                    Record recordS = dictionary.get(new Key(argument,3));
                    // if the record isn't null
                    if (recordS != null){
                        // try to play the record with SoundPlayer class
                        try {
                            player.play(recordS.getDataItem());
                        // if record cannot be played print exception
                        } catch (MultimediaException e){
                            System.out.println(e.getMessage());
                        }
                    // if record is null print output to user
                    } else {
                        System.out.println("There is no sound file for " + argument);
                    }
                    break;
                // if command is "play"
                case "play":
                    // similar logic to "sound" command
                    Record recordP = dictionary.get(new Key(argument, 4));
                    if (recordP != null){
                        try {
                            player.play(recordP.getDataItem());
                        } catch (MultimediaException e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("There is no music file for " + argument);
                    }
                    break;
                // if command is "say"
                case "say":
                    // similar logic to "sound" command
                    Record recordSay = dictionary.get(new Key(argument, 5));
                    if (recordSay != null){
                        try {
                            player.play(recordSay.getDataItem());
                        } catch (MultimediaException e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("There is no voice file for " + argument);
                    }
                    break;
                // if command is "show"
                case "show":
                    // fetch correct record
                    Record recordShow = dictionary.get(new Key(argument, 6));
                    // if record is not null
                    if (recordShow != null){
                        // try to show the picture with PictureViewer class
                        try {
                            viewer.show(recordShow.getDataItem());
                        } catch (MultimediaException e){
                            System.out.println(e.getMessage());
                        }
                    // if record is null
                    } else {
                        // print output to user
                        System.out.println("There is no image file for " + argument);
                    }
                    break;
                // if command is animate
                case "animate":
                    // similar logic to "show" command
                    Record recordAni = dictionary.get(new Key(argument,7));
                    if (recordAni != null){
                        try {
                            viewer.show(recordAni.getDataItem());
                        } catch (MultimediaException e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("There is no animated image file for " + argument);
                    }
                    break;
                // if command is "browse"
                case "browse":
                    // fetch correct record
                    Record recordB = dictionary.get(new Key(argument,8));
                    // if record isn't null
                    if (recordB != null){
                        // try to show the record with ShowHTML class
                        try {
                            browser.show(recordB.getDataItem());
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    // if record is null
                    } else {
                        // print output tio user
                        System.out.println("There is no webpage called " + argument);
                    }
                    break;
                // if command is "delete"
                case "delete":
                    // split argument into two parts based on space character
                    String[] partsArg = argument.split(" ", 2);
                    // if the length of partsArg is 2
                    if(partsArg.length == 2){
                        // set string w to the first part
                        String w = partsArg[0];
                        // set string w to the second part
                        String k = partsArg[1];
                        // convert the string into an integer for the k value
                        int kInt = Integer.parseInt(k);
                        // fetch the correct record
                        Record recordDelete = dictionary.get(new Key(w, kInt));
                        // if the record isn't null
                        if(recordDelete != null){
                            // try to remove the record
                            try{
                                dictionary.remove(new Key(w, kInt));
                            // if a DictionaryException is tripped
                            } catch (DictionaryException e){
                                // output error to user
                                System.out.println(e.getMessage());
                            }
                        // if found record is null
                        } else {
                            // print output to user
                            System.out.println("No record in the ordered dictionary has key (" + w +","+ k + ")" );
                        }
                    // if the length of partsArg isn't 2
                    } else {
                        // output correct format
                        System.out.println("Invalid input format. Expected: 'delete w k'");
                    }
                    break;
                // if command is "add"
                case "add":
                    // split argument into three parts
                    String[] partsArgAdd = argument.split(" ", 3);
                    // if the argument is successfully in 3 parts
                    if(partsArgAdd.length == 3){
                        // set first part to String w
                        String w = partsArgAdd[0];
                        // set second part to String t
                        String t = partsArgAdd[1];
                        // convert String t into int
                        int tInt = Integer.parseInt(t);
                        // set third part to String c
                        String c = partsArgAdd[2];
                        // try to get the record to see if it exists already
                        Record recordAdd = dictionary.get(new Key(w, tInt));
                        // if the record gotten is null
                        if(recordAdd == null){
                            // try to put the record in the dictionary
                            try{
                                dictionary.put(new Record(new Key(w,tInt), c));
                            } catch (DictionaryException e){
                                System.out.println(e.getMessage());
                            }
                        // if the record already exists
                        } else {
                            // print output to user
                            System.out.println("A record with the given key (" + w + t + ") is already in the ordered dictionary");
                        }
                    // if argument cannot be split into 3 parts
                    } else {
                        // print output to user
                        System.out.println("Invalid input format. Expected: 'add w t c'");
                    }
                    break;
                // if command is "list"
                case "list":
                    // create an ArrayList of type record and call it result to hold all the records that match prefix
                    List<Record> result = new ArrayList<>();
                    // set the current record to the smallest record in dictionary
                    Record current = dictionary.smallest();

                    // loop through all the record in dictionary
                    while (current != null){
                        // fetch label of current record
                        String label = current.getKey().getLabel();
                        // check if label starts with argument
                        if(label.startsWith(argument)){
                            result.add(current);
                        }

                        // set current record to successor of current record
                        current = dictionary.successor(current.getKey());
                    }

                    // if result is empty
                    if(result.isEmpty()){
                        // print output to user
                        System.out.println("No label attributes in the ordered dictionary start with prefix " + argument);
                    // if only one record is found
                    } else if (result.size() == 1) {
                        // print the record with specified print output
                        for (Record record: result){
                            Key k = record.getKey();
                            System.out.println(k.getLabel());
                        }
                    // if more than one record is found
                    } else {
                        // print the records with specified print output
                        for (Record record: result){
                            Key key = record.getKey();
                            System.out.print(key.getLabel() + ", ");
                        }
                        System.out.println();
                    }
                    break;
                // if command is "first"
                case "first":
                    // get the record with the smallest key
                    Record first = dictionary.smallest();
                    // get the key from the record
                    Key k = first.getKey();
                    // output the record to user
                    System.out.println(k.getLabel() + "," + k.getType() + "," + first.getDataItem());
                    break;
                // if command is "last"
                case "last":
                    // get record with the largest key
                    Record last = dictionary.largest();
                    // get the key from the record
                    Key k1 = last.getKey();
                    // output record to user
                    System.out.println(k1.getLabel() + "," + k1.getType() + "," + last.getDataItem());
                    break;
                // if command is "exit"
                case "exit":
                    // break infinite while loop
                    return;
                // if command given is not recognized
                default:
                    // output feedback to user
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
