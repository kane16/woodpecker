package models;

public class UserThread {

    public UserThread(String username, Long threadId) {
        this.username = username;
        this.threadId = threadId;
    }

    private String username;
    private Long threadId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }
}
