package no.nav.kafka;

public class FizzBuzzAnswerMessage {

    private final String answer;
    private final Integer candidate;
    private final String groupId;

    public FizzBuzzAnswerMessage(final String answer, final Integer candidate, final String groupId) {
        this.answer = answer;
        this.candidate = candidate;
        this.groupId = groupId;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getCandidate() {
        return candidate;
    }


    public String getGroupId() {
        return groupId;
    }
}
