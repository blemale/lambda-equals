# LambdaEquals: A Java 8 utility method to implement equals method without boilerplate

```java
class MyClass {

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

    @Override
    public int hashCode() {
        return Objects.hash(i, b, s);
    }

    @Override
    public boolean equals(Object obj) {
        return LambdaEquals.equals(this , obj,
                MyClass::getI,
                MyClass::getB,
                MyClass::getS);
    }
    
    public boolean oldStyleEquals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MyClass other = (MyClass) obj;
        return Objects.equals(this.i, other.i)
                && Objects.equals(this.b, other.b)
                && Objects.equals(this.s, other.s);
    }
}
```
