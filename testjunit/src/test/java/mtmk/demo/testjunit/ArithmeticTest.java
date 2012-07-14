package mtmk.demo.testjunit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ArithmeticTest {

	public double num;
	public double expected;

	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection datafeed() {
		Object[][] object = { { -1.0, 1.0 }, { 2.0, 4.0 }, { 1.2, 1.44 } };
		return Arrays.asList(object);
	}

	public ArithmeticTest(double num, double expected) {
		this.num = num;
		this.expected = expected;
	}

	@Test
	public void testSqurare() {
		assertEquals(0, Double.compare(expected, Arithmetic.squrare(num)));
	}

}
