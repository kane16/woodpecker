import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertEquals;


public class WorkerTest {

    Worker worker;



    @BeforeMethod
    public void beforeTests(){
        worker = new Worker();
    }

    @Test(threadPoolSize = 4, invocationCount = 10)
    public void multipleUsersTest(){
        System.out.println(Thread.currentThread().getId());
        worker.work("Nowy");
    }

    @AfterMethod
    public void isCounterCorrect(){
    }

}
