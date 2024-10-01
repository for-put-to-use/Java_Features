package Java_Features_Module.Java_8;

import java.util.Optional;

public class Optionals {
	/*
	 * It is mainly to avoid Null Pointer Exception which break the app at runtime.
	 */
	
	Optional<Integer> optional = Optional.of(1);
//	Optional<Integer> optionalNull = Optional.of(null);
	//Optional.of() won't allow null values
	Optional<Integer> optionalNullableNull = Optional.ofNullable(null);
	
	void practiceRun() {
		System.out.println(this.optional.isPresent());
		System.out.println(this.optionalNullableNull.isPresent());
	}
	
	public static void main(String[] args) {
		Optionals op = new Optionals();
		op.practiceRun();
	}
	
}
