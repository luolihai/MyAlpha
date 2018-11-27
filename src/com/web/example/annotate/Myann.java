package com.web.example.annotate;

public @interface Myann {

	String name() default "";
	String value();
	
	int age() default 0;
	
	Myann2 myann();
}
