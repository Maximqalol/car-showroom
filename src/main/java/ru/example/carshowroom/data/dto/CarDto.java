package ru.example.carshowroom.data.dto;

public class CarDto {
    private Integer id;
    private String brand;
    private String model;
    private String features;
    private Integer year;
    private Integer price;
    private Integer quantity;
    private Integer producerId;

    public CarDto() {
    }

    public CarDto(Integer id, String brand, String model, String features, Integer year, Integer price, Integer quantity, Integer producerId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.features = features;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.producerId = producerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "CarDto {" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", features='" + features + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", quantity=" + quantity +
                ", producerId=" + producerId +
                '}';
    }


    public static final class Builder {
        private Integer id;
        private String brand;
        private String model;
        private String features;
        private Integer year;
        private Integer price;
        private Integer quantity;
        private Integer producerId;

        private Builder() {
        }

        public static Builder aCarDto() {
            return new Builder();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withFeatures(String features) {
            this.features = features;
            return this;
        }

        public Builder withYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder withPrice(Integer price) {
            this.price = price;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withProducerId(Integer producerId) {
            this.producerId = producerId;
            return this;
        }

        public CarDto build() {
            CarDto carDto = new CarDto();
            carDto.setId(id);
            carDto.setBrand(brand);
            carDto.setModel(model);
            carDto.setFeatures(features);
            carDto.setYear(year);
            carDto.setPrice(price);
            carDto.setQuantity(quantity);
            carDto.setProducerId(producerId);
            return carDto;
        }
    }
}
