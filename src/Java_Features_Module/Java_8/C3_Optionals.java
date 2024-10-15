package Java_Features_Module.Java_8;

import java.util.Optional;

public class C3_Optionals {
	/*
	 * To handle null values gracefully
	 * It is mainly to avoid Null Pointer Exception which break the app at runtime.
	 * isPresent()
	 * ifPresent()
	 * get() , empty()
	 * orElse() , orElseGet() , orElseThrow()
	 * filter()
	 * flatMap()
	 * map()
	 * 
	 * 
	 */
	
	Optional<Integer> optional = Optional.of(1);
//	Optional<Integer> optionalNull = Optional.of(null);
	/*
	 * Optional.of() won't allow null values
	 */
	Optional<Integer> optionalNullableNull = Optional.ofNullable(null);
	
	
	
	
	
	
	public void practiceRun() {
		System.out.println(this.optional.isPresent());
		System.out.println(this.optionalNullableNull.isPresent());
		this.optional.ifPresent(
				(value)-> 
					System.out.println("this.optional is present: "+value));
	}
	
	
	
	
	
	public static void main(String[] args) {
		C3_Optionals op = new C3_Optionals();
		op.practiceRun();
	}
}
