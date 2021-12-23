package com.example.SOAPZ.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Method {
    @Id
    @GeneratedValue
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Method_Type method_type;
    String url;
    String input;
    String output;

    public Method() {
    }

    @Override
    public String toString() {
        return "Method{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method_type=" + method_type +
                ", url='" + url + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method method = (Method) o;
        return Objects.equals(id, method.id) &&
                Objects.equals(name, method.name) &&
                method_type == method.method_type &&
                Objects.equals(url, method.url) &&
                Objects.equals(input, method.input) &&
                Objects.equals(output, method.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, method_type, url, input, output);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method_Type getMethod_type() {
        return method_type;
    }

    public void setMethod_type(Method_Type method_type) {
        this.method_type = method_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
