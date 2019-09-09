package bean;

public class TestObj {
        private String name;
        private int age;

    public TestObj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        TestObj other = (TestObj) obj;
        if (other.age==age&&other.name.equals(name))return true;
//        if (age != other.age)
//            return false;
//        if (name == null) {
//            if (other.name != null)
//                return false;
//        } else if (!name.equals(other.name))
//            return false;
        return false;
    }
    //    @Override
//    public int hashCode() {
//        String id=name+age;
//        return id.hashCode();
//    }
}
