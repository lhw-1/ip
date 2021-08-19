package main.java;
import java.util.ArrayList;

public class DeadlineCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    protected int index;

    DeadlineCommand(String input, int index, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
        this.index = index;
    }

    public String reply() throws DukeException {

        int position = input.indexOf("/by");
        String newTask = input.substring(9, position);
        String newInfo = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty. Please try again!");
        } else if (newInfo.length() == 0) {
            throw new DukeException("The date of a deadline cannot be empty. Please try again!");
        } else {
            list.add(new DeadlineTask(newTask, newInfo));
            return addTask(newTask, 2, index, newInfo);
        }
    }
}