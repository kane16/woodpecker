public class Worker {

    private static int N;

    private static int Nmax = 4;

    synchronized boolean work(String user){
        N++;
        return true;
    }

    public int getN(){
        return N;
    }

}
