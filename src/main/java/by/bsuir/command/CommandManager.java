package by.bsuir.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final CommandManager INSTANCE = new CommandManager();
    private Map<String, Command> commands;

    private CommandManager(){
        commands = new HashMap<>();
    }

    public static CommandManager getInstance(){
        return INSTANCE;
    }

    public void addCommand(String commandName, Command command){
        commands.put(commandName, command);
    }

    public Command getCommand(String command){
        return commands.get(command);
    }
}
