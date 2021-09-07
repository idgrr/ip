package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that duke executes to end the the program
 */
public class ByeCommand extends Command{
    private final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     */
    public ByeCommand(Storage storage, TaskList taskList){
        super(storage, taskList, true);
    }

    @Override
    public String exec() throws DukeException {
        return exitMessage;
    }
}
