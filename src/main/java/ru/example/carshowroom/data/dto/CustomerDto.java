package ru.example.carshowroom.data.dto;


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


    public static final class Builder {
        private Integer id;
        private String lastName;
        private String firstName;
        private String middleName;
        private String phone;
        private String email;

        private Builder() {
        }

        public static Builder aCustomerDto() {
            return new Builder();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerDto build() {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(id);
            customerDto.setLastName(lastName);
            customerDto.setFirstName(firstName);
            customerDto.setMiddleName(middleName);
            customerDto.setPhone(phone);
            customerDto.setEmail(email);
            return customerDto;
        }
    }
}
