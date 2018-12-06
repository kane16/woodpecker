import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class MultipleRequestsTest {

    @DataProvider(name = "Usernames", parallel = true)
    public static Object[][] usernames(){
        return new Object[][]{{"Adam"}, {"Jola"}, {"Amelia"}, {"Monica"}};
    }

    Worker worker;

    static int enteredSize;

    List<Thread> workingThreads;

    @BeforeClass
    public void beforeTests(){
        worker = new Worker();
        enteredSize = 0;
        workingThreads = new ArrayList<>();
    }


    @Test(dataProvider = "Usernames", invocationCount = 2)
    public void multipleUsersTest(String username) throws InterruptedException {
        boolean entered = worker.work(username);
        if(entered){
            enteredSize++;
        }
    }

    @Test(dependsOnMethods = {"multipleUsersTest"})
    public void parallelRequestTestCheck(){
        Assert.assertEquals(worker.getXmax(), 6);
    }


}
