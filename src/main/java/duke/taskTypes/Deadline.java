package duke.taskTypes;

import duke.exception.DukeException;

import duke.exception.EmptyTimeException;
import duke.exception.InvalidFormatException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Deadline Task class that sets description of task, date, time
 */
public class Deadline extends Task{



    // Constructor
    /**
     * Deadline Constructor, main method that formats input and sets details of deadline
     *
     * @param input string from the user
     */
    public Deadline(String input, boolean isDone) throws DukeException {
        super(isDone);
        List<String> formattedInput = formatInput(input);

        boolean isMissingDescriptionTimestamp = formattedInput.size() == 0;
        boolean isMissingTimestamp = formattedInput.size() == 1;

        if (isMissingDescriptionTimestamp) {
            throw new InvalidFormatException("\nMissing description and timestamp");
        }

        if (isMissingTimestamp) {
            throw new EmptyTimeException("\nInvalid timestamp format");
        }

        setTaskDetails(getTaskType(), formattedInput);
    }



    // Deadline format Methods;
    /**
     * Formats the input into 2 parts : taskDetails, date together with time
     *
     * @param input user input
     * @return list
     */
    private List<String> formatInput(String input) {
        return Pattern.compile("/by")
                .splitAsStream(input)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns string containing the task type of deadline
     *
     * @return String
     */
    private String getTaskType() {
        return "D";
    }



    // methods that returns formatted string for saving / displaying
    /**
     * Returns a string that describes the instance for display
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getFormatDate() + ")";
    }

    /**
     * Returns a string that describes the instance for saving
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTask() {
        return super.saveTask() + " /by " + super.getSaveDate();
    }
}
