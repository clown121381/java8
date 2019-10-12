package lambda.entity;

public class MyCompareImpl1 implements MyCompare<Employee>{
	@Override
	public boolean compare(Employee t0) {
		return t0.getSalary() > 5000.00;
	}
}
