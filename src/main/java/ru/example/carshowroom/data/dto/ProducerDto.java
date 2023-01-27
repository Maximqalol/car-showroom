package ru.example.carshowroom.data.dto;


public class ProducerDto {
    private Integer id;

    private String name;

    private String address;

    private String phone;

    public ProducerDto() {
    }

    public ProducerDto(Integer id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ProducerDto {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private String address;
        private String phone;

        private Builder() {
        }

        public static Builder aProducerDto() {
            return new Builder();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ProducerDto build() {
            ProducerDto producerDto = new ProducerDto();
            producerDto.setId(id);
            producerDto.setName(name);
            producerDto.setAddress(address);
            producerDto.setPhone(phone);
            return producerDto;
        }
    }
}
