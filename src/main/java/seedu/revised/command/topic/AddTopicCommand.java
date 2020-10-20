package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.TopicList;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;
import seedu.revised.ui.Ui;


/**
 * Adds an instance of the <code>Topic</code> class into a <code>TopicList</code>.
 */
public class AddTopicCommand extends TopicCommand {
    private String fullCommand;

    public AddTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public Topic execute(Subject subject) throws NoTopicException, RepeatedTopicException {
        int startOfMessage = 4;
        int endOfMessage = fullCommand.length();
        TopicList topicList = subject.getTopics();
        if (endOfMessage <= startOfMessage) {
            throw new NoTopicException(Ui.printNoTopicError());
        }
        String title = fullCommand.substring(startOfMessage, endOfMessage);
        if (title.isEmpty()) {
            throw new NoTopicException(Ui.printNoTopicError());
        }
        for (Topic topic : topicList.getList()) {
            if (topic.getTitle().equals(title)) {
                throw new RepeatedTopicException(Ui.printRepeatedTopicError());
            }
        }
        Topic t = new Topic(title);
        topicList.add(t);
        Ui.printTopic(t, topicList);
        return t;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
