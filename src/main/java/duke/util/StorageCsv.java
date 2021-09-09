package duke.util;

import duke.exception.DukeException;
import duke.exception.FileNotFoundException;
import duke.taskTypes.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class StorageCsv implements Storage {

    private File csvFile;
    private final static String COMMA_DELIMITER = ",";
    private final static int NUMBER_CSV_COLUMN = 6;

    /**
     * Constructor for StorageTxt and sets the file that contains previous state
     *
     * @param filePath File location that contains text file containing previous state
     * @throws DukeException Thrown when file does not exist
     */
    public StorageCsv(String filePath) {
        File dir = new File(filePath);
        dir.mkdirs();
        File csvFile = new File(filePath + "/csvFile.csv");
        if (!csvFile.exists()) {
            try{
                csvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.csvFile = csvFile;
    }

    /**
     * Reads and returns a list of previous task
     *
     * @throws DukeException
     */
    public List<String> loadSaved() throws DukeException{
        List<String> pastCommand = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                List<String> fields = new ArrayList<>();
                try (Scanner rowScanner = new Scanner(line)) {
                    rowScanner.useDelimiter(COMMA_DELIMITER);
                    while (rowScanner.hasNext()) {
                        fields.add(rowScanner.next());
                    }
                }
                System.out.println(convertCsvRowToTaskDetails(fields));
                pastCommand.add(convertCsvRowToTaskDetails(fields));
            }

        } catch (IOException e) {
            throw new FileNotFoundException("\nInvalid FilePath");
        }
        return pastCommand;
    }

    public String convertCsvRowToTaskDetails(List<String> rowDetails) {
        String row = "";
        for (String k : rowDetails) {
            row = row + " " + k;
        }
        return  row.trim();
    }

    /**
     * Saves newly added task into storageTxt
     * @param task Newly added task
     * @throws DukeException Thrown when file does not exist
     */
    public void saveAddedTask(Task task) throws DukeException {
        String msg = task.saveTaskCsv();
        try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

    /**
     * Updates and saves the state of the changed task
     * @param taskList current state
     * @throws DukeException Thrown when file does not exist
     */
    public void saveUpdateTask(TaskList taskList) throws DukeException {
        String[] currentState = taskList.saveStateCsv();
        try {

            FileWriter fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(System.lineSeparator());
            for( String msg : currentState){
                fileWriter.write(msg);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

}
