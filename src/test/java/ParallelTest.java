import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ParallelTest {

    Worker worker;

    static int enteredSize;

    List<Thread> workingThreads;

    @BeforeClass
    public void beforeTests(){
        worker = new Worker();
        enteredSize = 0;
        workingThreads = new ArrayList<>();
    }

    @Test(threadPoolSize = 4, invocationCount = 4)
    public void parallelRequestTest() throws InterruptedException {
        boolean entered = worker.work("Liam");
        if(entered){
            enteredSize++;
        }
    }

    @Test(dependsOnMethods = {"parallelRequestTest"})
    public void parallelRequestTestCheck(){
        Assert.assertEquals(worker.getYmax(), 3);
    }

}
