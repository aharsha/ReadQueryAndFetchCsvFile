import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.*;

public class Query 
{
	public void parseQuery(String queryString) throws Exception
	{
		if (isValidQueryString(queryString)) 
		{
			QueryParameter queryParameter=new QueryParameter();
			queryParameter=queryParameter.processQueryParam(queryString);
			HeaderRow headerrow=getHeaderRow(queryParameter);
			
			//queryProcessing(headerrow,obj);
			
		} 
		else 
		{
			System.out.println("Query String is not in Proper Format");
		}
	}

	private boolean isValidQueryString(String qrystring) 
	{
		if(qrystring.contains("select") && qrystring.contains("from") || (qrystring.contains("where") ||qrystring.contains("order by")|| qrystring.contains("group by")||qrystring.contains("")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public HeaderRow getHeaderRow(QueryParameter qryparam) throws Exception
	{
		BufferedReader bufferreader=new BufferedReader(new FileReader(qryparam.getFilepath()));
		HeaderRow headerrow=new HeaderRow();
		
		if(bufferreader!=null)
		{
			String rowdata=bufferreader.readLine();
			String rowvalues[]=rowdata.split(",");
			int colindex=0;
			for(String rowvalue:rowvalues)
			{
				headerrow.firstrow.put(rowvalue,colindex);
				colindex++;
			}
			
			System.out.println(headerrow.firstrow);
		}
		
		return headerrow;
	}
	
	public DataSet queryProcessing(HeaderRow headerrow,QueryParameter qryparam)
	{
		DataSet ds=new DataSet();
		
		return ds;
	}
}
