package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.DeadlineTask;

/**
 * A class that encapsulates a Deadline Command given to Duke.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor for the DeadlineCommand class.
     *
     * @param input The input given by the user.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Returns the proper response according to the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    public String execute(TaskList list, UserInterface ui) throws DukeException {

        int position = input.indexOf("/by");
        String newTask = input.substring(9, position - 1);
        String newTime = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty. Please try again!");
        } else if (newTime.length() == 0) {
            throw new DukeException("The date of a deadline cannot be empty. Please try again!");
        } else {
            try {
                LocalDateTime time = LocalDateTime.parse(newTime.trim(), inputFormatter);
                list.addTask(new DeadlineTask(newTask, time));
                Storage.save(list);
                return ui.showTaskAdded(newTask, 2, list.getSize() - 1, newTime.trim());
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Your time format is wrong. Please enter the time in the format DD/MM/YYYY HHMM and try again!");
            }
        }
    }
}
