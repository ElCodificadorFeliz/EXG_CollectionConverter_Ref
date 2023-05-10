// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package testOfCollectionConverter;


import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
//
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import collectionConverter.Converter;
import stuffBeginnersDontHaveToUnderstand.EnvironmentAnalyzer;
import stuffBeginnersDontHaveToUnderstand.GivenCodeVersion;
import stuffBeginnersDontHaveToUnderstand.Version;


/**
 * (Unit-)TestFrame for Palindrome Tester
 * 
 * @author  Michael Schaefers  ([UTF-8]:"Michael Schäfers");
 *          Px@Hamburg-UAS.eu
 * @version {@value #encodedVersion}
 */
public class UnitTestFrameAndStarter{
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_003___2023_05_10__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    /**
     * The method {@link #getDecodedVersion()} delivers the code version as reground/readable String.
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){ return version.getDecodedVersion(); }
    // Obiges (ab VERSION) dient nur der Versionierung.
    
    
    
    
    
    //##########################################################################
    //###
    //###   the TEST itself
    //###
    
    @BeforeAll
    public static void runSetupBeforeAnyUnitTestStarts(){
        
        // print some information at start
        System.out.printf( "TestFrame information\n" );
        System.out.printf( "=====================\n" );
        System.out.printf( "\n\n" );
        //
        System.out.printf( "Release:\n" );
        System.out.printf( "    GivenCode version:      %s\n",  GivenCodeVersion.getDecodedVersion() );
        System.out.printf( "    TestFrame version:      %s\n",  version.getDecodedVersion() );
        System.out.printf( "\n\n" );
        //
        System.out.printf( "Environment:\n" );
        System.out.printf( "    #Cores:                 %d\n",  EnvironmentAnalyzer.getAvailableCores() );
        System.out.printf( "    Java:                   %s\n",  EnvironmentAnalyzer.getJavaVersion() );
        System.out.printf( "    JUnit5/Jupiter:         %s\n",  EnvironmentAnalyzer.getJUnitJupiterVersion() );
        System.out.printf( "    JUnit5/Platform:        %s\n",  EnvironmentAnalyzer.getJUnitPlatformVersion() );
        System.out.printf( "    assert enabled?:        %s\n",  EnvironmentAnalyzer.isAssertEnabled() );
        System.out.printf( "    UTF-8 configured?:      %s      <- check output\n",  "[ÄËÏÖÜẞäëïöüß␣🙂😉🙁😟😓😎☠]" );
        System.out.printf( "\n\n\n\n" );
        //
        System.out.printf( "################################################################################" );
        System.out.printf( "\n" );
        System.out.printf( "Start of actual tests\n" );
        System.out.printf( "=====================\n" );
        System.out.printf( "Remember: The main point is the \"green bar\" (in the JUnit-window).\n" );
        System.out.printf( "\n" );
        //
        System.out.flush();
    }//method()    
    
    
    
    
    
    @Test
    public void testSimple01(){
        // prepare test parameter
        final Integer[] testInputRaw = {
            1, 11, 110,
            2, 21, 210,
            2, 22, 220
        };
        final List<Integer> listOfInteger = Arrays.asList( testInputRaw );
        final List<Object> list = new ArrayList<Object>( listOfInteger );
        //
        // do actual test
        final Converter converter = new Converter();
        final Map<Object,Map<Object,Object>> computedResult = converter.toNestedMap( list );
        //
        //check test result
        assertEquals( 2, computedResult.size() );
        for( final Object exteriorKey : computedResult.keySet() ){
            final Map<Object,Object> interiorMap = computedResult.get( exteriorKey );
            assertEquals( exteriorKey, interiorMap.size() );
            for( final Object interiorKey : interiorMap.keySet() ){
                final Object interiorData = interiorMap.get( interiorKey );
                assertEquals( interiorKey, (int)( interiorData )/10 );
                assertEquals( exteriorKey, (int)( interiorKey )/10 );
            }//for
        }//for
    }//method()
    
    
    @Test
    public void testSimple02(){
        // prepare test input/parameter
        final Integer[] testInputRaw = {
            3, 31, 310,
            3, 32, 320,
            3, 33, 330,
            1, 11, 110,
            4, 41, 410,
            4, 42, 420,
            4, 43, 430,
            4, 44, 440,
            2, 21, 210,
            2, 22, 220
        };
        final List<Integer> listOfInteger = Arrays.asList( testInputRaw );
        final List<Object> list = new ArrayList<Object>( listOfInteger );
        //
        // do actual test
        final Converter converter = new Converter();
        final Map<Object,Map<Object,Object>> computedResult = converter.toNestedMap( list );
        //
        // check test result
        assertEquals( 4, computedResult.size() );
        for( final Object exteriorKey : computedResult.keySet() ){
            final Map<Object,Object> interiorMap = computedResult.get( exteriorKey );
            assertEquals( exteriorKey, interiorMap.size() );
            for( final Object interiorKey : interiorMap.keySet() ){
                final Object interiorData = interiorMap.get( interiorKey );
                assertEquals( interiorKey, (int)( interiorData )/10 );
                assertEquals( exteriorKey, (int)( interiorKey )/10 );
            }//for
        }//for
    }//method()
    
    
    @Test
    public void testSimple03(){
        // prepare test input/parameter
        Map<Object,Map<Object,Object>> outerMap = new HashMap<Object,Map<Object,Object>>();
        Map<Object,Object> tmpInnerMap;
        tmpInnerMap = new HashMap<Object,Object>();
        tmpInnerMap.put( 11, 110 );
        outerMap.put( 1,  tmpInnerMap );
        //
        // prepare expected result
        final List<Object> expectedResult = new ArrayList<Object>( Arrays.asList( 1, 11, 110 ));
        //
        // do actual test
        final Converter converter = new Converter();
        final List<Object> computedResult = converter.toList( outerMap );
        //
        // check test result
        assertEquals( expectedResult, computedResult );
    }//method()
    
    
    
    @Test
    public void test01(){
        // prepare test input/parameter
        Map<Object,Map<Object,Object>> outerMap = new HashMap<Object,Map<Object,Object>>();
        //
        for( int i=1; i<10; i++ ){
            final Map<Object,Object> innerMap = new HashMap<Object,Object>();
            for( int j=1; j<=i; j++ ){
                 final int key = j*10 +j;
                final int data = key*10 +j; 
                innerMap.put( key, data );
            }//for
            outerMap.put( i, innerMap );
        }//for
        //
        // do actual test
        final Converter converter = new Converter();
        final List<Object> computedResultList = converter.toList( outerMap );
        
        assertEquals( 135, computedResultList.size() );
        final Map<Object,Map<Object,Object>> computeResultMap = converter.toNestedMap( computedResultList );
    }//method()
    
    
    @Test
    public void test02(){
        // prepare test input/parameter
        Map<Object,Map<Object,Object>> outerMap = new HashMap<Object,Map<Object,Object>>();
        //
        for( int i=9; i>0; i-- ){
            final Map<Object,Object> innerMap = new HashMap<Object,Object>();
            for( int j=1; j<=i; j++ ){
                 final int key = j*10 +j;
                final int data = key*10 +j; 
                innerMap.put( key, data );
            }//for
            outerMap.put( i, innerMap );
        }//for
        //
        // do actual test
        final Converter converter = new Converter();
        final List<Object> computedResultList = converter.toList( outerMap );
        assertEquals( 135, computedResultList.size() );
        
        final Map<Object,Map<Object,Object>> computedResultMap = converter.toNestedMap( computedResultList );
        assertEquals( outerMap, computedResultMap );
    }//method()
    
    
    @Test
    public void randomBasedTest01(){
        for( int runsStillToDo = 42; --runsStillToDo>0; ){
            int innerEntryCnt = 0;
            //
            // prepare test input/parameter
            final List<Integer> li = new ArrayList<Integer>( Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9 ));
            Map<Object,Map<Object,Object>> outerMap = new HashMap<Object,Map<Object,Object>>();
            //
            for( int stillToDo=7; --stillToDo>0; ){
                final int index =  (int)( li.size() * Math.random() );
                final Map<Object,Object> innerMap = new HashMap<Object,Object>();
                final int leadInt = li.remove( index );
                final int max = (int)( 9 * Math.random() +1 );
                innerEntryCnt += max;
                for( int i= max; --i>=0; ){
                    final int key = leadInt*10 +i;
                    final int data = key*10 +i; 
                    innerMap.put( key, data );
                }//for
                outerMap.put( leadInt, innerMap );
            }//for
            //
            // do actual test
            final Converter converter = new Converter();
            List<Object> computedResultList = converter.toList( outerMap );
            for( int stillToDo=5; --stillToDo>0; ){
                assertEquals( 3*innerEntryCnt, computedResultList.size() );
                computedResultList = converter.toList( converter.toNestedMap( computedResultList ));
            }//for
            final Map<Object,Map<Object,Object>> computedResultMap = converter.toNestedMap( computedResultList );
            assertEquals( outerMap, computedResultMap );
        }//for
    }//method()
    
    
    
    
    
    /** extreme test: empty list as actual parameter for toMap() */
    // empty list
    @Test
    public void extremeTest01(){
        final Converter converter = new Converter();
        final Map<Object,Map<Object,Object>> computedResult = converter.toNestedMap( new ArrayList<Object>() );
        assertTrue( computedResult.isEmpty() );
    }//method()
    
    
    /** extreme test: empty map as actual parameter for toList() */
    @Test
    public void extremeTest02(){
        final Converter converter = new Converter();
        final List<Object> computedResult = converter.toList( new HashMap<Object,Map<Object,Object>>() );
        assertTrue( computedResult.isEmpty() );
    }//method()
    
    
    
    /** extreme test: list is null */
    @Test
    public void extremeTest11(){
        final Converter converter = new Converter();
        //
        // prepare test        
        boolean expectedExceptionDetected = false;
        //
        // do actual test
        try{
            final Map<Object,Map<Object,Object>> computedResult = converter.toNestedMap( null );
        }catch( final IllegalArgumentException | AssertionError ex ){
            expectedExceptionDetected = true;
        }finally{
            //check test result
            if( ! expectedExceptionDetected ){
                fail();
            }//if
        }//try
    }//method()    
    
    
    /** extreme test: list element is null */
    @Test
    public void extremeTest12(){
        final int[] orgArrayOfInt = { 1, 2, 3, 4, 5 };
        //       
        for( int i=orgArrayOfInt.length; --i>=0; ){
            final Converter converter = new Converter();
            //
            // prepare test input/parameter
            final Integer[] tmpArrayOfInteger = new Integer[orgArrayOfInt.length];
            for( int j=orgArrayOfInt.length; --j>=0; )  tmpArrayOfInteger[j] = orgArrayOfInt[j];
            tmpArrayOfInteger[i] = null;
            //
            final List<Integer> tmpListOfInteger = Arrays.asList( tmpArrayOfInteger );
            final List<Object> list = new ArrayList<Object>( tmpListOfInteger );
            boolean expectedExceptionDetected = false;
            //
            // do actual test
            try{
                final Map<Object,Map<Object,Object>> computedResult = converter.toNestedMap( list );
            }catch( final IllegalArgumentException | AssertionError ex ){
                expectedExceptionDetected = true;
            }finally{
                //check test result
                if( ! expectedExceptionDetected ){
                    fail();
                }//if
            }//try
        }//for
    }//method()    
    
    
    
    /** extreme test: map is null */
    @Test
    public void extremeTest21(){
        final Converter converter = new Converter();
        //
        // prepare test        
        boolean expectedExceptionDetected = false;
        //
        // do actual test
        try{
            final List<Object> computedResultList = converter.toList( null );
        }catch( final IllegalArgumentException | AssertionError ex ){
            expectedExceptionDetected = true;
        }finally{
            //check test result
            if( ! expectedExceptionDetected ){
                fail();
            }//if
        }//try
    }//method()
    
    
    
    
    
    @AfterAll
    public static void tearDownAfterAllUnitTestsHaveFinished(){
        System.out.printf( "\n" );
        System.out.printf( "################################################################################\n" );
        System.out.printf( "THE END" );
        System.out.flush();
    }//method()
    
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    
    
    
    
    
    //###
    //###
    //### support stuff
    //###
    
    //##########################################################################
    //###
    //###   taken from "TS class"
    //###
    
    private static class TestSupportException extends RuntimeException {
        
        final private String errorMessage;
        final private Throwable causingThrowable;
        final static private long serialVersionUID = 2017_11_05_001L;
        
        private TestSupportException( final String message,  final Throwable throwable ){   // package scope on purpose
            super( message );
            causingThrowable = throwable;
            if( null!=causingThrowable ){
                errorMessage = " (caused by: \"" + causingThrowable.toString() + "\")";
            }else{
                errorMessage = message;
            }//if
        }//constructor()
        private TestSupportException( final String message ){
            this( message, new Throwable( "NO causing throwable existent - directly thrown : "+message ));
        }//constructor()
        private TestSupportException(){
            this( "test support functionality broken" );
        }//constructor()
        
        @Override
        public Throwable getCause(){
            return causingThrowable;
        }//method()
        
        @Override
        public String getMessage(){
            return errorMessage;
        }//method()
        
    }//class
    
    
    private static Object generateRequestedObject (
        final String      requestedClassName,
        final Class<?>[]  requestedParameterTypes,
        final Object[]    actualParameter
    ) throws TestSupportException {
        try{
            final Class<?> requestedClass = Class.forName( requestedClassName );
            final Object constructedObject = callConstructor( requestedClass, requestedParameterTypes, actualParameter );
            return constructedObject;
        }catch( final ClassNotFoundException ex ){
            throw new TestSupportException( String.format( "object of \"%s\" can NOT be generated properly", requestedClassName ),  ex );
        }//try
    }//method()
    //
    private static Object generateRequestedObject(
        final String  requestedClassName
    ) throws TestSupportException {
        return generateRequestedObject( requestedClassName, null, null );
    }//method()
    
    private static Object callConstructor (
        final Class<?>    classUnderTest,
        final Class<?>[]  requestedParameterTypes,
        final Object[]    actualParameter
    ) throws TestSupportException {
        try{
            final Constructor<?> constructor = classUnderTest.getConstructor( requestedParameterTypes );
            final Object constructedObject = constructor.newInstance( actualParameter );
            return constructedObject;
        }catch( final NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex ){
            throw new TestSupportException( String.format( "constructor for \"%s\" can NOT be called properly", classUnderTest.getSimpleName() ),  ex );
        }//try 
    }//method()
    
    private static Object callFunction(
        final Object objectUnderTest,
        final String requestedMethodName,
        final Class<?>[] requestedParameterTypes,
        final Object[] actualParameter
    ){
        try{
            final Class<?> classOfObjectUnderTest = objectUnderTest.getClass();
            final Method method = classOfObjectUnderTest.getMethod( requestedMethodName, requestedParameterTypes );
            final Object result = method.invoke( objectUnderTest, actualParameter );
            return result;
        }catch( final NoSuchMethodException | IllegalAccessException ex ){
            throw new TestSupportException( String.format( "method \"%s\" can NOT be called properly", requestedMethodName ),  ex );
        }catch( final InvocationTargetException ex ){
            throw new TestSupportException( String.format( "method \"%s\" can NOT be called properly", requestedMethodName ),  ex.getCause() );
        }//try 
    }//method()
    
}//class
