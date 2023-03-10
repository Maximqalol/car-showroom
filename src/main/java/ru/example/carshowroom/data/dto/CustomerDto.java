package ru.example.carshowroom.data.dto;

import java.util.Objects;

public class CustomerDto {
    private Integer id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String phone;

    private String email;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String lastName, String firstName, String middleName, String phone, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerDto {" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id.equals(that.id) &&
                lastName.equals(that.lastName) &&
                firstName.equals(that.firstName) &&
                middleName.equals(that.middleName) &&
                phone.equals(that.phone) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, phone, email);
    }
}
