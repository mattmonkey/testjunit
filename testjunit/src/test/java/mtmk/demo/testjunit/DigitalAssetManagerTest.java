package mtmk.demo.testjunit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import mtmk.demo.testjunit.DigitalAssetManager;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;

public class DigitalAssetManagerTest {
	static Logger logger = Logger.getLogger(DigitalAssetManagerTest.class);
	

	// TestWatcher is a base class for Rules that take note of the testing action, without modifying it.
	@Rule
    public TestWatcher watchman = new TestWatcher() {

		@Override
		protected void finished(Description description) {
			logger.info(methodName.getMethodName() + " findished \n");
		}
		
		@Override
		protected void starting(Description description){
			logger.info(methodName.getMethodName() + " Starting");
		}
          
    };
	
	// The TestName Rule makes the current test name available inside test methods:
	@Rule
	public TestName methodName = new TestName();
	
	// The Timeout Rule applies the same timeout to all test methods in a class
	@Rule
	public Timeout t = new Timeout(1000);

	// The ExpectedException Rule allows in-test specification of expected exception types and messages
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void throwsIllegalArgumentExceptionIfIconIsNull() throws InterruptedException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Icon is null, not a file, or doesn't exist.");
		new DigitalAssetManager(null, null);
	}
	
	@Rule  
	public ErrorCollector collector= new ErrorCollector();  
	
	@Test  
    public void errorCollectorTest() {  
        // collector.addError(new Throwable("first thing went wrong"));  
        // collector.addError(new Throwable("second thing went wrong"));  
        collector.checkThat(6, is(6));  
        collector.checkThat(1, is(2)); 
        collector.checkThat(5, is(5));  
        logger.info("no interrupted!");
    }  
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void countsAssets() throws IOException, InterruptedException {
		File icon = tempFolder.newFile("icon.png");
		File assets = tempFolder.newFolder("assets");
		DigitalAssetManager dam = new DigitalAssetManager(icon, assets);
		assertEquals(3, dam.getAssetCount());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsIllegalArgumentExceptionIfIconIsNull2() {
		new DigitalAssetManager(null, null);
	}
	
	@Test(timeout=900)
	public void timeout() throws InterruptedException {
		Thread.sleep(50);
	}
	
	// 普通的case碰到第一个fail,会结束方法.
	@Test  
    public void noErrorCollector(){  
        assertThat(6, is(6));  
        assertThat(4, is(4));  
		logger.info("no interrupted..");
    } 

	@Ignore
	public void helloTest(){
		logger.info("Hello World");
	}
	
	@Before
	public void setUp() throws Exception {
		logger.info("setup");
	}
	
	@After
	public void tearDown() throws Exception {
		logger.info("tearDown");
	}
	
	@BeforeClass
	public static void setUpBeforeClass()  {
		logger.info("setUpBeforeClass\n");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		logger.info("tearDownAfterClass");
	}

}
