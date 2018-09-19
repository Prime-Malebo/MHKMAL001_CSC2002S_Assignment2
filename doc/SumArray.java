


import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<String>  {
	  String lo; // arguments
	  String hi;
	  String[] arr;
	  static final int SEQUENTIAL_CUTOFF=20000;

	  String ans; // result

	  SumArray(String[] a, String l, String h) {
	    lo=l; hi=h; arr=a;
	  }


	  protected String compute(){// return answer - instead of run
		  if(( Integer.parseInt(hi)-Integer.parseInt(lo) ) < SEQUENTIAL_CUTOFF) {
			  String ans;
				Double ans1 =0.0;
		      for(int i=Integer.parseInt(lo); i < Integer.parseInt(hi); i++){
		        ans1 += Double.parseDouble(arr[i]);
					}
					ans = Double.toString(ans1);
		      return ans;
		  }
		  else {
				int a= (Integer.parseInt(hi)+Integer.parseInt(lo))/2;
				int b=(Integer.parseInt(hi)+Integer.parseInt(lo))/2;
			  SumArray left = new SumArray(arr,lo,Integer.toString(a));
			  SumArray right= new SumArray(arr,Integer.toString(b),hi);

			  // order of next 4 lines
			  // essential – why?
			  left.fork();
			  String rightAns = right.compute();
			  String leftAns  = left.join();
				Double totl = (Double.parseDouble(leftAns) + Double.parseDouble(rightAns));
			  return Double.toString(totl);
		  }
	 }
}
