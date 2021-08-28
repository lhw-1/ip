package main.java.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.DeadlineTask;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) throws DukeException {

        int position = input.indexOf("/by");
        String newTask = input.substring(9, position);
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
                UserInterface.showTaskAdded(newTask, 2, list.getSize() - 1, newTime.trim());
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Your time format is wrong. Please enter the time in the format DD/MM/YYYY HHMM and try again!");
            }
        }
    }
}