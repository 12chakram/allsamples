package learn.basics;



class EBank{
	 
	 public static void main(String[] args) {
		
		 try
		 {
			 
			 
			 int bal=1000;
			 int minbal=bal-500;   // mimn bal
			 int acbal =0;
			
			 int withdraw= Integer.parseInt(args[0]);
			 
		
				 

			// throw new ArrayIndexOutOfBoundsException("please enter the amount ");
		 
		
				 
			 
			 if(minbal<withdraw)
				 throw new LessBalanceException("insafisitent funds");
			 
			 else
				 
			 System.out.println("take u cash");
			 
			 System.out.println("after reciving memony");
			 
			 acbal=minbal-withdraw;
			 
			 System.out.println("u r balance is:"+acbal);
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 finally{
			 System.out.println("thank u , visit again");
		 }
		 
		 
	}
	 

 }