package duke.taskTypes;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidFormatException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Event Task class that sets description of task, date, time
 */
public class Event extends Task{



    // Constructor
    /**
     * Takes in a string and splits msg into based on /at pattern. Set the eventType and time of the instance
     *
     * @param input string from the user
     */
    public Event(String input, boolean isDone) throws DukeException {
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

        super.setTaskDetails(getTaskType(), formattedInput);
    }



    // Event format methods
    /**
     * Formats the input into 2 parts : taskDetails, date together with time
     *
     * @param input user input
     * @return list
     */
    private List<String> formatInput (String input) {
        return Pattern.compile("/at")
                .splitAsStream(input)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns string containing the task type of event
     *
     * @return String
     */
    private String getTaskType() {
        return "E";
    }



    // methods that returns formatted string for saving / displaying
    /**
     * Returns a string that describes the instance
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + super.getFormatDate() + ")";
    }

    /**
     * Returns a string that describes the instance for saving
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTask() {
        return super.saveTask() + " /at " + super.getSaveDate();
    }
}
