package main.java;

/**
 * A class that handles exceptions for the Chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the DukeException class
     */
    DukeException(String errorMessage) {
        super("☹ OOPS!!! It looks like was an error handling your request!\n\nThe error is as follows:\n" + errorMessage);
    }
}
