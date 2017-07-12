import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



 



public class TestQuery {
	
	static QueryParameter queryParameter;
	
	@BeforeClass
	public static void intialise()
	{
		queryParameter=new QueryParameter();
		String queryString="select empid,empname from  d://cn.csv where empid=101 and empname='raju' order by empname";
		queryParameter.processQueryParam(queryString);
	System.out.println(queryParameter.getFilepath());
	}

    @Test  
        public void testCsvFileName(){  
    	assertEquals("File Path is correct","d://cn.csv",queryParameter.getFilepath());  
        //assertEquals(16,Calculator.findMax(new int[]{1,16,4,2}));  
        
        //assertEquals(-1,Calculator.findMax(new int[]{-12,-1,-3,-4,-2}));  
    }	

    @Test  
    public void hasOrderBy(){  
		assertEquals("havingOrder By ",true,queryParameter.isHasorderby());  
    //assertEquals(16,Calculator.findMax(new int[]{1,16,4,2}));  
    
    //assertEquals(-1,Calculator.findMax(new int[]{-12,-1,-3,-4,-2}));  
}	

}

