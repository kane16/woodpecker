import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Worker {

    private List<User> users;

    private static int X = 0;

    private static int Nmax = 4;

    private static int Ymax = 2;

    private static int Xmax = 5;

    public static int getNmax() {
        return Nmax;
    }

    public static void setNmax(int nmax) {
        Nmax = nmax;
    }

    public Worker(List<User> users){
        this.users=users;
    }

    synchronized boolean work(String username) throws Exception {
        final User enteringUser = new User(username, Thread.currentThread().getId());
        List<User> newUserList = new ArrayList<>(users);
        Optional<User> newThreadUser = this.users.stream()
                .filter(user -> user.equals(enteringUser))
                .findFirst();
        if(newThreadUser.isPresent()){
            if(Xmax < X){
                X++;
            }else throw new Exception();

        }else {
            newUserList.add(enteringUser);
            int numberOfUsers = this.users.stream()
                    .map(user -> user.getUsername())
                    .collect(Collectors.toSet())
                    .size();
            if(Nmax<numberOfUsers){
                this.users.add(enteringUser);
            }
        }
        return true;
    }

}
