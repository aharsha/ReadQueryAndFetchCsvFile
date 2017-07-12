import java.util.*;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

public class QueryParameter {
	
	
		private String filepath;
		private String orderbycol,groupbycol,selectcol;
		
		private RestrictionCondition restrictcondition=new RestrictionCondition(); 
		
		
		
		private boolean hasgroupby,hasorderby,haswhere,hasAllColumn;
		
		private ColumnName columnames=new ColumnName();
		
		
		public boolean isHasgroupby() {
			return hasgroupby;
		}

		public void setHasgroupby(boolean hasgroupby) {
			this.hasgroupby = hasgroupby;
		}

		public boolean isHasorderby() {
			return hasorderby;
		}

		public void setHasorderby(boolean hasorderby) {
			this.hasorderby = hasorderby;
		}

		public boolean isHaswhere() {
			return haswhere;
		}

		public void setHaswhere(boolean haswhere) {
			this.haswhere = haswhere;
		}

		public boolean isHasAllColumn() {
			return hasAllColumn;
		}

		public void setHasAllColumn(boolean hasAllColumn) {
			this.hasAllColumn = hasAllColumn;
		}

		public String getFilepath() 
		{
			return filepath;
		}

		public void setFilepath(String filepath) 
		{
			this.filepath = filepath;
		}

		public QueryParameter processQueryParam(String qrystring)
		{
			String QueryBit=null,conditionqry=null;
			
			if(qrystring.contains("order by"))
			{
				QueryBit=qrystring.split("order by")[0].trim();
				System.out.println("b = "+QueryBit);
				orderbycol=qrystring.split("order by")[1].trim();
				System.out.println("column for orderby"+orderbycol);
				filepath=QueryBit.split("from")[1].trim();
				QueryBit=QueryBit.split("from")[0].trim();
				selectcol=QueryBit.split("select")[1].trim();
				this.parseColumn(selectcol);
				hasorderby=true;
			}
			if(qrystring.contains("group by"))
			{
				QueryBit=qrystring.split("group by")[0].trim();
				groupbycol=qrystring.split("group by")[1].trim();
				System.out.println(QueryBit);
				if(QueryBit.contains("where"))
				{
					conditionqry=QueryBit.split("where")[1].trim();
					String relationalqry=conditionqry.split("and|or")[0].trim();
					String relationalqry2=conditionqry.split("and|or")[0].trim();
					
					this.relationalExpressionProcessing(relationalqry);
					QueryBit=QueryBit.split("where")[0].trim();			
				}
				filepath=QueryBit.split("from")[1].trim();
				QueryBit=QueryBit.split("from")[0].trim();
				selectcol=QueryBit.split("select")[1].trim();
				this.parseColumn(selectcol);
				hasgroupby=true;
			}
			else if(qrystring.contains("where"))
			{
				QueryBit=qrystring.split("where")[0];
				conditionqry=qrystring.split("where")[1];
				conditionqry=conditionqry.trim();
				filepath=QueryBit.split("from")[1].trim();
				String relationalqry=conditionqry.split("and|or")[0].trim();
				System.out.println(relationalqry);
				this.relationalExpressionProcessing(relationalqry);
				selectcol=QueryBit.split("select")[1].trim();
				this.parseColumn(selectcol);
				haswhere=true;
			}
			else
			{
				QueryBit=qrystring.split("from")[0].trim();
				filepath=QueryBit.split("from")[1].trim();
				selectcol=QueryBit.split("select")[1].trim();
				this.parseColumn(selectcol);
			}
			
			System.out.println(QueryBit);
			System.out.println(orderbycol);
			System.out.println(groupbycol);
			System.out.println(conditionqry);	
			System.out.println(filepath);
			
			System.out.println(restrictcondition.getColumn());
			System.out.println(restrictcondition.getValue());
			System.out.println(restrictcondition.getOperator());
			
			return this;
		}
		
		private void relationalExpressionProcessing(String relationqry)
		{
			String oper[]={">","<",">=","<=","=","!="};
			
			for(String operator:oper)
			{
				if(relationqry.contains(operator))
				{
				restrictcondition.setColumn(relationqry.split(operator)[0].trim());
				restrictcondition.setValue(relationqry.split(operator)[1].trim());
				restrictcondition.setOperator(operator);
				break;
				}
			}
		}
		
		private void parseColumn(String selectcolumn)
		{
			if(selectcolumn.trim().contains("*") && selectcolumn.length()==1)
			{
				hasAllColumn=true;
			}
			if(selectcolumn.trim().contains(","))
			{
				String columnlist[]=selectcolumn.split(",");
				
				int i=0;
				for(String column:columnlist)
				{
					columnames.colnames.put(column,i);
					i++;
				}
				
			}
		}
		
	}



