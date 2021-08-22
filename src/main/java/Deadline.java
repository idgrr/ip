import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Deadline extends Task{
    /**
     * Takes in a string and splits msg into based on /by pattern. Set the eventType and time of the instance
     * @param input string from the user
     */
    public Deadline(String input) throws InvalidDeadlineFormatException {
        super();
        List<String> results = Pattern.compile("/by").splitAsStream(input).map(x->x.trim()).collect(Collectors.toList());
        String key;

        if (results.size() == 0){
            throw new InvalidDeadlineFormatException("Missing description and timestamp");
        } else if (results.size() == 1){
            throw new InvalidDeadlineFormatException("Invalid timestamp format");
        }

        key = results.get(0);
        if (key.equals("")){
            throw new InvalidDeadlineFormatException("Missing description ");
        }

        super.setEventType("D");
        super.setDescription(key);
        super.setTime(results.get(1));
    }

    /**
     * Returns a string that describes the instance
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        return super.toString() + " (by: " + super.getTime() + ")";
    }
}
