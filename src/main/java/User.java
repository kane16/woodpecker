public class User {

    public User(String username, Long threadId) {
        this.username = username;
        this.threadId = threadId;
    }

    private String username;
    private Long threadId;
    private static int X;

    public static int getX() {
        return X;
    }

    public static void setX(int x) {
        X = x;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                threadId.equals(user.threadId);
    }
}
