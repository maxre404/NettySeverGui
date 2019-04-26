package bean;

public class Person {
    public static Students stu = new Students();

    public static Students getStu() {
        return stu;
    }

    public static void setStu(Students stu) {
        Person.stu = stu;
    }

    public static class Students {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Students{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
