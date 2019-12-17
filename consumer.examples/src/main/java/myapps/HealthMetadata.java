package myapps; 

import myapps.HealthDeserializer;
import io.advantageous.boon.json.JsonFactory;


public class HealthMetadata {

    private final int age;
    private final int weight;
    private final String name;

    public HealthMetadata(final String json) {
        this(JsonFactory.fromJson(json, HealthMetadata.class));
    }

    public HealthMetadata() {
        age = 0;
        weight = 0;
        name = ""; 

    }

    public HealthMetadata(final String name, final int age, final int weight) {
        this.age = age;
        this.weight = weight;
        this.name = name; 

    }

    public HealthMetadata(final HealthMetadata healthMetadata) {
        this.age = healthMetadata.age;
        this.weight = healthMetadata.weight;
        this.name = healthMetadata.name; 

    }

    public int getAge() {
        return age;
    }


    public int getWeight() {
        return weight;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "HealthMetadata{" +
                "age=" + age +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthMetadata that = (HealthMetadata) o;

        if (age != that.age) return false;
        if (weight != that.weight) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * age + weight;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    public String toJson() {
        return "{" +
                "\"age\": " + age +
                ", \"weight\": " + weight +
                ", \"name\": \"" + name + '\"' +
                '}';
    }

}

