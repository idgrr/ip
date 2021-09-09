package duke.exception;

/**
 * Exception to display error made by using in regards to format in general
 */
public class InvalidFormatException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occured
     */
    public InvalidFormatException(String errorDetails){
        super(errorDetails);
    }
}
