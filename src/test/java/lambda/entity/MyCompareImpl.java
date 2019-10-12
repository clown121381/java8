package lambda.entity;

public class MyCompareImpl implements MyCompare<Employee>{
	@Override
	public boolean compare(Employee t0) {
		return t0.getAge() > 20;
	}

	
}
