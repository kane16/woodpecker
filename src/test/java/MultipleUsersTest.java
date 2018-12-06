import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MultipleUsersTest {

    @DataProvider(name = "Usernames", parallel = true)
    public static Object[][] usernames(){
        return new Object[][]{{"Adam"}, {"Jola"}, {"Amelia"}, {"Monica"}, {"Carol"}, {"Frank"}};
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


    @Test(dataProvider = "Usernames")
    public void multipleUsersTest(String username) throws InterruptedException {
        boolean entered = worker.work(username);
        if(entered){
            enteredSize++;
        }
    }

    @Test(dependsOnMethods = {"multipleUsersTest"})
    public void multipleUsersTestCheck(){
        Assert.assertEquals(worker.getNmax(), 5);
    }

}
