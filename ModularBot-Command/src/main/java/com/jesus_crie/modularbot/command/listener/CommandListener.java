package com.jesus_crie.modularbot.command.listener;

import com.jesus_crie.modularbot.command.AccessLevel;
import com.jesus_crie.modularbot.command.CommandEvent;
import com.jesus_crie.modularbot.command.exception.CommandExecutionException;
import com.jesus_crie.modularbot.command.exception.CommandProcessingException;
import com.jesus_crie.modularbot.command.exception.UnknownOptionException;
import com.jesus_crie.modularbot.command.processing.CommandProcessor;
import com.jesus_crie.modularbot.command.processing.Options;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.utils.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

@SuppressWarnings("EmptyMethod")
public interface CommandListener {

    /**
     * Triggered every time a command is typed (= any message that starts with the prefix
     *
     * @param event The event that has triggered the listener.
     */
    void onCommandReceived(@Nonnull final MessageReceivedEvent event);

    /**
     * Triggered when a command was typed and it was a real command.
     * But nothing has been executed yet and no checks have been performed.
     *
     * @param command The actual event.
     */
    void onCommandFound(@Nonnull final CommandEvent command);

    /**
     * Triggered when a message starts with the prefix but it was a false alert, the command doesn't exist.
     * An error 404 in a manner.
     *
     * @param name    The name of the command that was typed.
     * @param message The message containing the wrong command.
     */
    void onCommandNotFound(@Nonnull final String name, @Nonnull final Message message);

    /**
     * Triggered when a command is found but the user doesn't satisfy the {@link AccessLevel AccessLevel}
     * associated with this command.
     *
     * @param event The event that was fired.
     */
    void onTooLowAccessLevel(@Nonnull final CommandEvent event);

    /**
     * Triggered when a command is found, the user have the correct privileges and what he just typed has been
     * successfully parsed.
     *
     * @param event            The event that was fired.
     * @param processedContent The output of the command processor.
     * @see CommandProcessor
     * @see CommandProcessor#process(String)
     */
    void onCommandSuccessfullyProcessed(@Nonnull final CommandEvent event, @Nonnull final Pair<List<String>, Map<String, String>> processedContent);

    /**
     * Triggered when the processing of the command has failed, usually a syntax error.
     *
     * @param event The event that was fired.
     * @param error The error from the command processor.
     * @see CommandProcessor
     */
    void onCommandFailedProcessing(@Nonnull final CommandEvent event, @Nonnull final CommandProcessingException error);

    /**
     * Triggered when the command was successfully processed but one of the option is not registered in the command.
     *
     * @param event The event that was fired.
     * @param error The error from the {@link Options}.
     * @see Options
     */
    void onCommandFailedUnknownOption(@Nonnull final CommandEvent event, @Nonnull final UnknownOptionException error);

    /**
     * Triggered when the command failed to execute because the provided arguments doesn't match any registered pattern.
     *
     * @param event     The actual event.
     * @param options   The provided options.
     * @param arguments The provided arguments.
     */
    void onCommandFailedNoPatternMatch(@Nonnull final CommandEvent event, @Nonnull final Options options, @Nonnull final List<String> arguments);

    /**
     * Triggered when an error is thrown by the command during its execution.
     *
     * @param event     The event that has triggered the command.
     * @param options   The options passed with the command.
     * @param arguments The arguments passed with the command.
     * @param error     The error that was thrown.
     */
    void onCommandExecutionFailed(@Nonnull final CommandEvent event, @Nonnull final Options options,
                                  @Nonnull final List<String> arguments, @Nonnull final CommandExecutionException error);

    /**
     * Triggered when the command is successfully executed.
     *
     * @param event The event that triggered the command in the first place.
     */
    void onCommandSuccess(@Nonnull final CommandEvent event);
}
