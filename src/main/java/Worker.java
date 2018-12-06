import models.UserThread;

import java.util.*;
import java.util.stream.Collectors;

public class Worker {

    private List<UserThread> userThreads;

    private Set<String> users;

    private Set<Long> currentUserThreads;

    private int X = 0;

    private int Nmax = 5;

    private int Ymax = 3;

    private int Xmax = 6;

    private int Wmax = 3;

    Random random;

    public int getNmax() {
        return Nmax;
    }

    public int getYmax() {
        return Ymax;
    }

    public int getXmax() {
        return Xmax;
    }

    public int getWmax() {
        return Wmax;
    }

    public Worker(){
        random = new Random();
        userThreads = new ArrayList<>();
        users = new HashSet<>();
        currentUserThreads = new HashSet<>();
    }

    public Worker(int nmax, int ymax, int xmax, int wmax) {
        Nmax = nmax;
        Ymax = ymax;
        Xmax = xmax;
        Wmax = wmax;
    }

    synchronized boolean work(String username) throws InterruptedException {
        UserThread enteringUserThread = new UserThread(username, Thread.currentThread().getId());
        if (isMaxRequestAboutToBeExceeded(username))
            return false;

        if (isMaxUsersCountAboutToBeExceeded(username, enteringUserThread))
            return false;

        if (isMaxUserParallelRequestAboutToBeExceeded(username, enteringUserThread))
            return false;

        setOnSuccessfullEnterAction(username, enteringUserThread);

        return true;
    }

    private void setOnSuccessfullEnterAction(String username, UserThread enteringUserThread) throws InterruptedException {
        X++;
        if(!currentUserThreads.contains(enteringUserThread.getThreadId())){
           userThreads.add(enteringUserThread);
           currentUserThreads.add(enteringUserThread.getThreadId());
           if(!users.contains(enteringUserThread.getUsername())){
               users.add(enteringUserThread.getUsername());
           }
        }
        Thread.sleep(random.nextInt(2000));

        System.out.println("Successfully entered "+username+"!!!");
    }

    private boolean isMaxUserParallelRequestAboutToBeExceeded(String username, UserThread enteringUserThread) throws InterruptedException {
        currentUserThreads = this.userThreads.stream()
                .filter(userThread -> username.equals(userThread.getUsername()))
                .map(userThread -> userThread.getThreadId())
                .collect(Collectors.toSet());

        if(!currentUserThreads.contains(enteringUserThread.getThreadId()) && currentUserThreads.size() >= Ymax){

            Thread.sleep(Wmax*1000);

            if(!currentUserThreads.contains(enteringUserThread.getThreadId()) && currentUserThreads.size() >= Ymax){
                onAccessDeniedAction(username);

                return true;
            }

        }
        return false;
    }

    private boolean isMaxUsersCountAboutToBeExceeded(String username, UserThread enteringUserThread) throws InterruptedException {
        users = this.userThreads.stream()
                    .map(userThread -> userThread.getUsername())
                    .collect(Collectors.toSet());

        if(!users.contains(enteringUserThread.getUsername()) && users.size() >= Nmax){

            Thread.sleep(Wmax*1000);

            if(!users.contains(enteringUserThread.getUsername()) && users.size() >= Nmax){
                onAccessDeniedAction(username);

                return true;
            }

        }
        return false;
    }

    private boolean isMaxRequestAboutToBeExceeded(String username) throws InterruptedException {
        if( X >= Xmax){

            Thread.sleep(Wmax*1000);

            if(X >= Xmax){
                onAccessDeniedAction(username);

                return true;
            }

        }
        return false;
    }

    private void onAccessDeniedAction(String username) {
        System.out.println("Access denied "+username+"!!!");
    }

}
