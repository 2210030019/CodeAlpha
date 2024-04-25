import java.util.*;
public class StudentGradeTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		 ArrayList<Double> grade = new ArrayList<Double>();
		 System.out.println("Enter no of students: ");
		 int n=sc.nextInt();
		 System.out.println("Enter grades of the students: ");
		 for(int i=0;i<n;i++) {
			 double d=sc.nextDouble();
			 grade.add(d);
		 }
		 double max=0;
		 double min=grade.get(0);
		 double sum=0;
		 for(int i=0;i<n;i++) {
			 double num=grade.get(i);
			 if(num>max) {
				 max=num;
			 }
			 if(num<min) {
				 min=num;
			 }
			 sum+=num;
		 }
		 System.out.print("Lowest score among the students' is: ");
		 System.out.println(min);
		 System.out.print("Highest score among the students' is: ");
		 System.out.println(max);
		 System.out.print("Average score of the students is: ");
		 System.out.println(sum/n);
		 
		 
		 

	}

}
