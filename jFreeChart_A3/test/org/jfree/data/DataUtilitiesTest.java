package org.jfree.data;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



import java.security.InvalidParameterException;



class DataUtilitiesTest {
	
	private Values2D value;
	private KeyedValues val;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach void setUp() throws Exception { 
		value = mock(Values2D.class); 
		when(value.getColumnCount()).thenReturn(4); 
		when(value.getRowCount()).thenReturn(3); 
		when(value.getValue(0, 2)).thenReturn(5); 
		when(value.getValue(1, 2)).thenReturn(7); 
		when(value.getValue(2, 2)).thenReturn(1); 
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	
//	@Test
//	void testColumnCountOfAllPositive() {
//		double actual = DataUtilities.calculateColumnTotal(value,2);
//		double expected = 13.0;
//		
//		assertEquals(expected, actual);
//	}
	
	

	@DisplayName("Get Cumulative Percentage")	
	@ParameterizedTest(name="Check the percentage value of key {0},{1} and {2} with values of {3},{4} and {5}")
	@CsvSource({"0,1,2,5,9,2", "0,1,2, 2,3,1" , "0,1,2,8,4,12"})
	@Tag("Natnael")
	void testCumulativePercentage(int key1, int key2, int key3, int value1, int value2, int value3) {
		
		val = mock(KeyedValues.class);
		//mock its behaviour
		when(val.getItemCount()).thenReturn(3);
		
		when(val.getValue(key1)).thenReturn(value1);
		when(val.getValue(key2)).thenReturn(value2);
		when(val.getValue(key3)).thenReturn(value3);
		
		when(val.getKey(key1)).thenReturn(key1);
		when(val.getKey(key2)).thenReturn(key2);
		when(val.getKey(key3)).thenReturn(key3);
		
		when(val.getIndex(key1)).thenReturn(key1);
		when(val.getIndex(key2)).thenReturn(key2);
		when(val.getIndex(key3)).thenReturn(key3);
		
		ArrayList keyList = new ArrayList<>();
		keyList.add(key1);
		keyList.add(key2);
		keyList.add(key3);
		when(val.getKeys()).thenReturn(keyList);
		
		KeyedValues actual = DataUtilities.getCumulativePercentages(val);

		
		//getting the total value of the values
		double totalVal = value1 + value2 + value3;
		System.out.println("Total Value : " + totalVal);
		
		double val1 = value1 / totalVal;
		double val2 = (value1+ value2) / totalVal;
		double val3 = (value1+ value2 + value3) / totalVal;
		
//		System.out.println(actual);
		System.out.println("*Actual*");
		System.out.println(actual.getValue(0).doubleValue());
		System.out.println(actual.getValue(1).doubleValue());
		System.out.println(actual.getValue(2).doubleValue());
		
		System.out.println("*Expected*");
		System.out.println(val1);
		System.out.println(val2);
		System.out.println(val3);
		
		System.out.println("*************************");
		
		assertAll(
				
				() -> assertEquals(val1, actual.getValue(0).doubleValue(),0.1d),
				() -> assertEquals(val2, actual.getValue(1).doubleValue(),0.1d),
				() -> assertEquals(val3, actual.getValue(2).doubleValue(),0.1d)
				
				
				);
		
		
	}
	
	
	///////////////////////****** Brendon ******///////////////////////
	@Test
	void testCreateNumberArray() {
		double[] test = {2.0, 4.0, 6.0};
		Number[] actual = DataUtilities.createNumberArray(test);
//		Number[] expected = DataUtilities.createNumberArray(numberArray);
		Number[] expected = {2.0, 4.0, 6.0};
		
		assertEquals(actual, expected);
	}
	
	@Test
	void testCreateNullNumberArray() {
		assertThrows(InvalidParameterException.class,
				()-> {
					double[] test = null;
					Number[] actual = DataUtilities.createNumberArray(test);
		 });
	}
	
	@Test
	void testCreateNumberArray2D() {
		
		double[][] test = new double[3][3];
		test[0][0] = 1.0;
		test[0][1] = 3.0;
		test[0][2] = 5.0;
		test[1][0] = 2.0;
		test[1][1] = 4.0;
		test[1][2] = 6.0;
		Number[][] actual = DataUtilities.createNumberArray2D(test);
		System.out.println("Brendon test");
		System.out.println(test);
		
//		Number[] expected = DataUtilities.createNumberArray(numberArray2D);
		Number[][] expected = new Number[3][3];
		expected[0][0] = 1.0;
		expected[0][1] = 3.0;
		expected[0][2] = 5.0;
		expected[1][0] = 2.0;
		expected[1][1] = 4.0;
		expected[1][2] = 6.0;
		System.out.println(expected);
		
		assertEquals(actual, expected);
	}
	
	@Test
	void testCreateNullNumberArray2D() {
		assertThrows(InvalidParameterException.class,
				()-> {
					double[][] test = null;
					Number[][] actual = DataUtilities.createNumberArray2D(test);
		 });
	}
	///////////////////////****** Brendon ******///////////////////////
	
	
	
	///////////////////////****** Daniel ******///////////////////////
	

//	------ Test Calculate Row Total ------- Values2D data, int row
//	Returns the sum of the values in one row of the supplied data table. 
//	With invalid input, a total of zero will be returned.
	@DisplayName("Test Calculate Row Total")
	@ParameterizedTest
	@CsvSource({"1,5,6,6", "0,-2,2,3", "5,1,2,2", "-2,4,1,3"})
	@Tag("Dan")
	@Timeout(5)
	void testCalculateRowTotalValidRange(int row, int val1, int val2, int val3) {
		when(value.getValue(row, 0)).thenReturn(val1);
		when(value.getValue(row, 1)).thenReturn(val2);
		when(value.getValue(row, 2)).thenReturn(val3);
		int expectedTotal = val1 + val2 + val3; 
		double actualTotal = DataUtilities.calculateRowTotal(value, row);
		System.out.println("Expected: " + expectedTotal);
		System.out.println("Actual: " + actualTotal);
		assertEquals(expectedTotal, actualTotal);
	}
//  ------ Test Calculate Column Total With Valid Input Range ------- Values2D data, int column
//	Returns the sum of the values in one column of the supplied data table. 
//	With invalid input, a total of zero will be returned.
	@DisplayName("Test Calculate Column Total")
	@ParameterizedTest
	@CsvSource({"1,5,6,6", "0,-2,2,3", "5,1,2,2", "-2,4,1,3"})
	@Tag("Dan")
	@Timeout(5)
	void testCalculateColumnTotalValidRange(int column, int val1, int val2, int val3) {
		when(value.getValue(0, column)).thenReturn(val1);
		when(value.getValue(1, column)).thenReturn(val2);
		when(value.getValue(2, column)).thenReturn(val3);
		int expectedTotal = val1 + val2 + val3; 
		double actualTotal = DataUtilities.calculateColumnTotal(value, column);
		System.out.println("Expected: " + expectedTotal);
		System.out.println("Actual: " + actualTotal);
		assertEquals(expectedTotal, actualTotal);
	}
	
//  ------ Test Calculate Column Total ------- Values2D data, int column
//	Returns the sum of the values in one column of the supplied data table. 
//	With invalid input, a total of zero will be returned.
	@DisplayName("Test Calculate Column Total Invalid Range")
	@ParameterizedTest
	@CsvSource({"4,2", "5,1","-1,-2"})
	@Tag("Dan")
	@Timeout(5)
	void testCalculateColumnTotalInvalidRange(int column, int columnCount) {
		when(value.getColumnCount()).thenReturn(columnCount);
		System.out.print(DataUtilities.calculateColumnTotal(value, column));
		System.out.println(column);
		assertThrows(InvalidParameterException.class, ()-> 
		DataUtilities.calculateColumnTotal(value, column));
	}
	
//  ------ Test Calculate Row Total ------- Values2D data, int row
//	Returns the sum of the values in one row of the supplied data table. 
//	With invalid input, a total of zero will be returned.
	@DisplayName("Test Calculate Row Total Invalid Range")
	@ParameterizedTest
	@CsvSource({"4,2", "5,1","-1,-2"})
	@Tag("Dan")
	@Timeout(5)
	void testCalculateRowTotalInvalidRange(int row, int rowCount) {
		when(value.getRowCount()).thenReturn(rowCount);
		System.out.print(DataUtilities.calculateRowTotal(value, row));
		System.out.println(row);
		assertThrows(InvalidParameterException.class, ()-> 
		DataUtilities.calculateRowTotal(value, row));
	}
	
	
	
	///////////////////////****** Daniel ******///////////////////////
}
