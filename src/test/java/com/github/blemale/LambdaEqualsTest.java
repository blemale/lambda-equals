package com.github.blemale;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaEqualsTest {

    @Test
    public void should_declare_object_not_equals_when_not_same_type() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        String b = "other";

        // When
        boolean equals = LambdaEquals.equals(a, b);

        // Then
        assertThat(equals).isFalse();
    }

    @Test
    public void should_declare_object_not_equals_when_first_is_null_and_second_not() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        MyClass b = null;

        // When
        boolean equals = LambdaEquals.equals(a, b);

        // Then
        assertThat(equals).isFalse();
    }

    @Test
    public void should_declare_object_not_equals_when_first_is_not_null_and_second_is() throws Exception {
        // Given
        MyClass a = null;
        MyClass b = new MyClass(0, true, "a");

        // When
        boolean equals = LambdaEquals.equals(a, b);

        // Then
        assertThat(equals).isFalse();
    }

    @Test
    public void should_declare_object_equals_when_objects_are_null() throws Exception {
        // Given
        MyClass a = null;
        MyClass b = null;

        // When
        boolean equals = LambdaEquals.equals(a, b);

        // Then
        assertThat(equals).isTrue();
    }

    @Test
    public void should_declare_object_equals_when_all_properties_are_equal() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        MyClass b = new MyClass(0, true, "s");

        // When
        boolean equals = LambdaEquals.equals(a, b,
                MyClass::getI,
                MyClass::getB);

        // Then
        assertThat(equals).isTrue();
    }

    @Test
    public void should_declare_object_equals_when_no_properties_and_same_class() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        MyClass b = new MyClass(0, true, "s");

        // When
        boolean equals = LambdaEquals.equals(a, b);

        // Then
        assertThat(equals).isTrue();
    }

    @Test
    public void should_declare_object_not_equals_when_any_property_is_not_equal() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        MyClass b = new MyClass(0, true, "s");

        // When
        boolean equals = LambdaEquals.equals(a, b,
                MyClass::getI,
                MyClass::getB,
                MyClass::getS);

        // Then
        assertThat(equals).isFalse();
    }

    @Test
    public void should_declare_object_not_equals_when_same_hierarchy_but_different_concrete_class() throws Exception {
        // Given
        MyClass a = new MyClass(0, true, "a");
        MyClass b = new MySubClass(0, true, "a");

        // When
        boolean equals = LambdaEquals.equals(a, b,
                MyClass::getI,
                MyClass::getB,
                MyClass::getS);

        // Then
        assertThat(equals).isFalse();
    }

    private static class MyClass {

        private final int i;
        private final Boolean b;
        private final String s;

        private MyClass(int i, Boolean b, String s) {
            this.i = i;
            this.b = b;
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public Boolean getB() {
            return b;
        }

        public String getS() {
            return s;
        }
    }

    private static class MySubClass extends MyClass {

        private MySubClass(int i, Boolean b, String s) {
            super(i, b, s);
        }
    }
}