package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
//import org.junit.jupiter.api.*;
//import java.security.InvalidParameterException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RangeTest {
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	
	// ----------------------------- test getLength() ----------------------------- //
	// test cases 
			// (3,10 -> 7) (0, 6 -> 6) (-10, 0 -> 10) (-6, -4 -> 2)
	@DisplayName("get Length Tests")
	@ParameterizedTest(name = "Test differnce of {1} minus {0}")
	@CsvSource({"3,10","0,6","-10,0","-6,-4", "0,0" , "-10, -10" , "7,7"})
	@Tag("Natnael")
	@Tag("GetLength")
	void getLengthShouldReturnDifference (double lower, double upper) {
		
		Range testRange = new Range(lower, upper);
		assertEquals((upper - lower) , testRange.getLength(),0,"Should return delta of bounds.");
	}

	

	
	// ----------------------------- test getLowerBound() ----------------------------- //
	// test cases
		// (2,6 -> 3) (0,6 -> 0) (-8, 0 -> -8) (-10, -5 -> -10) (-8, 5 -> -8) (0,0)
	@DisplayName("get Lower Bound")
	@ParameterizedTest(name= "Get lower bound of {1} and {0}")
	@CsvSource({"2,6", "0,6" , "-8,0" , "-10,-5", "-8,5" , "0, 0" })
	@Tag("Natnael")
	void testLowerBoundOfTwoNumber(double lower, double upper) {
		Range range = new Range(lower,upper);
		//double actual = range.getLowerBound();
		double expected = lower;
		assertEquals(expected, range.getLowerBound(),0,"Should return the lower bound for the range");
	}
	
	
	
	// ----------------------------- test getUpperBound() ----------------------------- //
	// (2,6 -> 6) (0,6 -> 6) (-8, 0 -> 0) (-10, -5 -> --5) (-6, 5 -> 5) (0,0 -> 0)
	@DisplayName("Get Upper Bound")
	@ParameterizedTest(name= "Get the Upper bound of {1} and {0}")
	@CsvSource({"2,6" , "0,6", "-8,0" , "-10,-5","-8,-5"})
	@Tag("Natnael")
	void testUpperBoundOfTwoPositiveNumber(double lower, double upper) {
		Range range = new Range(lower,upper);
		//System.out.println(range.getUpperBound());
		double expected = upper;
		assertEquals(expected, range.getUpperBound(), 0, "Should return the upper bound for the range");
		
	}
	
	
	
	// ----------------------------- test getCentralValue() ----------------------------- //
	@DisplayName("Get Central Value")
	@ParameterizedTest(name= "Get the central value of {1} and {0}")
	@CsvSource({"2,6" , "3, 6" , "0,6", "-8,0" , "-10,-5","-8,-5"})
	@Tag("Natnael")
	void testCentralValue(double lower, double upper) {
		Range range = new Range(lower, upper);
		double expected = (lower + upper)/2;
		//System.out.println(expected);
		assertEquals(expected, range.getCentralValue(), 0, "Should return the central value");
		
	}
	
	// ----------------------------- test toString() ----------------------------- //
		// test cases 
			// (4,6 -> Range[4.0,6.0]) (0, 6 -> Range[0.0,6.0]) (-3, 0 -> Range[-3.0,0.0]) (-6, -4 -> Range[-6.0,-4.0])
	@DisplayName("Get String Value")	
	@ParameterizedTest(name="Get the string value of {1} and {0}")
	@CsvSource({"2,6" , "3, 6" , "0,6", "-8,0" , "-10,-5","-8,-5"})
	@Tag("Natnael")
	void testStringValue(double lower, double upper) {
		Range range = new Range(lower, upper);
		//System.out.println(range.getLength());
		//System.out.println("*******Printing output for toString()********");
		//System.out.println(range.toString());
			
		String actual = range.toString();
		String expected = "Range["+lower+ ","+upper+"]";
		//System.out.println(expected);
		assertEquals(expected, actual,"The Return value should be \"Range[lower,upper]\"");
			
	}
	
	// ----------------------------- test equals() ----------------------------- //
	@DisplayName("Check Equal Values")	
	@ParameterizedTest(name="Check the string value of {0},{1} and {2},{3}")
	@CsvSource({"2,6,2,6" , "3, 6,3,6" , "0,6,0,6", "-8,0,-8,0" , "-10,-5,-10,-5","-8,5,-8,5"})
	@Tag("Natnael")
	void testEquals(double lower1, double upper1, double lower2, double upper2) {
		Range range = new Range(lower1,upper1);
		Range range2 = new Range(lower2, upper2);
			
		boolean actual = range.equals(range2);
		boolean expected = true;
		assertEquals(expected, actual, "The Return value should be \"Range[lower,upper]\"");
			
	}
	
	// ----------------------------- test testExpand() ----------------------------- //
	@DisplayName("Check test to include all values")
	@ParameterizedTest(name="Returns A range which spans over {0}, {1}, and has been expanded to contain {2} ")
	@CsvSource({"2,4,6" , "-3, 5, 0", "-8, -4, 4"})
	@Tag("Natnael")
	void testExpand(double lowerRange, double upperRange, double input) {
		Range range = new Range(lowerRange, upperRange);
		double num = input;
		//System.out.println("*******Printingafadfa output for ExpandToInclude********");
		//System.out.println(Range.expandToInclude(range, num));
		Range actual = Range.expandToInclude(range, num);
		//find the lowest and the highest of the 3 and create a range in between
		double low = lowerRange;
		double high = upperRange;
		
		if(input < low) {
			low = input;
		} else {
			low = lowerRange;
		}
		
		if(input > high) {
			high = input;
		} else {
			high = upperRange;
		}
		
		
		//System.out.println("Low" + low);
		//System.out.println("High" + high);
		Range expected = new Range(low,high);
		//System.out.println(expected);
		assertSame(expected, actual, "The Return value should be \"Range[lower,upper]\"");
		
		
	}
	
	
	///////////////////////****** Brendon ******///////////////////////
	
	/* cases:
	 * - two valid ranges 		(4, 6) (2, 8) -> (2, 8)
	 * - two null ranges		(null) (null) -> null
	 * - one null range			(null) (4, 5) -> (4, 5)
	 * */
	@ParameterizedTest
	@CsvSource({
		"4, 6, 2, 8, 2, 8",
		"0, 0, 0, 0, 0, 0",
		"0, 0, 2, 8, 2, 8"
	})
	void testCombine(double lower1, double upper1, double lower2, double upper2, double expectedLower, double expectedUpper) {
		if (lower1 == 0) {
			Range range1 = null;
			Range range2 = new Range(lower2, upper2);
			Range expected = new Range(expectedLower, expectedUpper);
			Range actual = Range.combine(range1, range2);
			assertEquals(expected, actual);
		} else if (lower1 == 0 && lower2 == 0) {
			Range range1 = null;
			Range range2 = null;
			Range expected = null;
			Range actual = Range.combine(range1, range2);
			assertEquals(expected, actual);
		} else {
			Range range1 = new Range(lower1, upper1);
			Range range2 = new Range(lower2, upper2);
			Range expected = new Range(expectedLower, expectedUpper);
			Range actual = Range.combine(range1, range2);
			assertEquals(expected, actual);
		}
	}
	
	
	/* cases:
	 * - value above upper 
	 * - value below lower 
	 * - value in the middle
	 * */
	@ParameterizedTest
	@CsvSource({
		"1, 8, 10, 8",
		"4, 8, 1, 4",
		"4, 8, 4, 4"
	})
	void testConstrain(double lower, double upper, double value, double expected) {
		Range range = new Range(lower, upper);
	    double actual = range.constrain(value);	
		assertEquals(expected, actual);
	}
	
	
	/* cases:
	 * - value above upper 
	 * - value below lower 
	 * - value in the middle
	 * */
	@ParameterizedTest
	@CsvSource({
		"1, 8, 0, false",
		"1, 8, 9, false",
		"1, 8, 5, true"
	})
	void testContains(double lower, double upper, double value, boolean expected) {
		Range range = new Range(lower, upper);
		boolean actual = range.contains(value);
		assertEquals(expected, actual);
	}
	
	
	/* cases:
	 * - valid range
	 * - valid negative range
	 * - null range value
	 * */
	@ParameterizedTest
	@CsvSource({
		"2, 6, 0.25, 0.5, 1, 8",
		"-6, -2, 0.5, 0.25, -8, -1",
	})
	void testExpand(double lower, double upper, double lowerMargin, double upperMargin, double expectedLower, double expectedUpper) {
		Range range = new Range(lower, upper);
		Range actual = Range.expand(range, lowerMargin, upperMargin);
		Range expected = new Range(expectedLower, expectedUpper);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testExpandNull() {
		
		assertThrows(IllegalArgumentException.class,
				()-> {
					Range range1 = null;
					Range expectedException = Range.expand(range1, 0.25, 0.5);
		 });
	}
	
	
	/* cases:
	 * - valid range
	 * - valid range, negative delta
	 * - null value -> throws exception
	 * */
	@ParameterizedTest
	@CsvSource({
		"1, 8, 5, 6, 13",
		"1, 8, -5, -4, -3"
	})
	void testShiftTwoParams(double lower, double upper, double value, double expectedLower, double expectedUpper) {
		Range range = new Range(lower, upper);
		Range actual = Range.shift(range, value);
		Range expected = new Range(expectedLower, expectedUpper);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testShiftTwoParamsNullRange() {

		assertThrows(IllegalArgumentException.class,
			()-> {
				Range range1 = null;
				Range expectedException = Range.shift(range1, 5);
	 });
	}
	
	
	/* cases: 
	 * - range where lower below 0 -> (allowZeroCrossing = true)
	 * - range where lower and upper below 0 -> (allowZeroCrossing = true)
	 * - range where lower below 0 -> (allowZeroCrossing = false)
	 * - range where lower and upper below 0 -> (allowZeroCrossing = false)
	 * - crossing zero with negative delta -> (allowZeroCrossing = false)
	 * - crossing zero with negative delta -> (allowZeroCrossing = true)
	 * - null value
	 * */	
	@ParameterizedTest
	@CsvSource({
		"1, 8, 5, 6, 13, true",
		"1, 8, -5, -4, -3, true",
		"1, 8, -5, -4, -3, true",
		"1, 8, -5, -4, -3, false",
		"1, 8, -5, -4, -3, false",
		"1, 8, -5, -4, -3, false"
	})
	void testShiftThreeParams(double lower, double upper, double value, double expectedLower, double expectedUpper, boolean zeroCrossing) {
		Range range = new Range(lower, upper);
		Range actual = Range.shift(range, value, zeroCrossing);
		Range expected = new Range(expectedLower, expectedUpper);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testShiftThreeParamsNullRange() {

		assertThrows(IllegalArgumentException.class,
				()-> {
					Range range1 = null;
					Range actual = Range.shift(range1, 5, true);
		 });
	}
	
	///////////////////////****** Brendon ******///////////////////////
	
	///////////////////////****** Daniel ******///////////////////////
	
	// ------- test valid intersects() -------
		@DisplayName("Test Valid Intersects")
		@ParameterizedTest
		@CsvSource({"-3,-2,-5,-4","-2,-1,-3,2", "0,0,0,0", "-4,2,-6,5", "3,6,4,5"})
		@Timeout(5)
		@Tag("Dan")
		void testIntersectsTrue(double rangeLower, double rangeUpper, double intersectLower, double intersectUpper) {
			System.out.print(rangeLower+ rangeUpper+ intersectLower+ intersectUpper);
			Range testRange = new Range(rangeLower, rangeUpper);
			assertTrue(testRange.intersects(intersectLower, intersectUpper));
		}

		// ------- test invalid intersects() -------
		@DisplayName("Test Invalid Intersects")
		@ParameterizedTest
		@CsvSource({"-2,-1,-4,-3", "-2,-1,-0.5,0", "-1,1,-20,-10", "0,0,-3,-4", "1,5,0,0.5", "3,8,8.5,10", "4,5,7,8"})
		@Timeout(5)
		@Tag("Dan")
		void testIntersectsFalse(double rangeLower, double rangeUpper, double intersectLower, double intersectUpper) {
			System.out.println(rangeLower+ rangeUpper+ intersectLower+ intersectUpper);
			Range testRange = new Range(rangeLower, rangeUpper);
			assertFalse(testRange.intersects(intersectLower, intersectUpper));
		}
		
		// ------- Test shift 2 params (Range base, double delta) ------
//		Returns a range the size of the input range, which has been moved positively (to the right) 
//		by the delta value. Is equivalent to shift(base, delta, false) (does not allow zero crossing).

		@DisplayName("Test Shift With Two Parameters")
		@ParameterizedTest
		@CsvSource({"1,5,3", "2,7,3", "2,5,1", "-2,-1,3", "0,1,2"})
		@Timeout(10)
		@Tag("Dan")
		void shiftRangeByDeltaTest(double lower, double upper, double delta) {
			System.out.println(Math.signum(lower));
//			System.out.print(lower+upper);
			Range test1Range = new Range(lower,upper);
			Range actual = Range.shift(test1Range, delta);
			if (lower != 0) {
				if (Math.signum(lower) != Math.signum(lower+delta)) {
					lower = 0;
				}  else {
					lower += delta;
				}
			} else {
				lower += delta;
			}
			if (upper != 0) {
				if (Math.signum(upper) != Math.signum(upper+delta)) {
					upper = 0;
				}  else {
					upper += delta;
				}
			} else {
				upper += delta;
			}
			Range expected = new Range(lower, upper);
			System.out.println(expected.toString());
			assertEquals(expected, actual);
		}
		
		@DisplayName("Test Shift With Three Parameters")
		@ParameterizedTest
		@CsvSource({"-10,-5,3,false", "2,7,3,false", "2,5,1,false", "-2,3,3,false", "-2,3,3,true", "-2,-1,4,true", "-2,-1,4,false"})
		@Timeout(10)
		@Tag("Dan")
		void shiftRangeByDeltaWithZeroCrossing(double lower, double upper, double delta, boolean allowZeroCrossing) {
			Range initialRange = new Range(lower, upper);
			Range actual = Range.shift(initialRange, delta, allowZeroCrossing);
			if (lower != 0 && allowZeroCrossing == false) {
				if (Math.signum(lower) != Math.signum(lower+delta)) {
					lower = 0;
				}  else {
					lower += delta;
				}
			} else {
				lower += delta;
			}
			if (upper != 0 && allowZeroCrossing == false) {
				if (Math.signum(upper) != Math.signum(upper+delta)) {
					upper = 0;
				}  else {
					upper += delta;
				}
			} else {
				upper += delta;
			}
			Range expected = new Range(lower, upper);
			System.out.print(expected.toString());
			assertEquals(expected, actual);
		}
		
	///////////////////////****** Daniel ******///////////////////////
	
	
	

}