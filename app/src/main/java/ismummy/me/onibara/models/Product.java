package ismummy.me.onibara.models;

import java.io.Serializable;

import ismummy.me.onibara.core.Requests;

public class Product implements Serializable {
    private String image;
    private String brand;
    private String name;
    private double price;
    private double rating;
    private String category;
    private String merchantName;
    private String merchantArea;
    private String merchantCity;
    private String id;
    private String spec;

    public String getImage() {
        return Requests.MEDIA_URI + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantArea(String merchantArea) {
        this.merchantArea = merchantArea;
    }

    public String getMerchantArea() {
        return merchantArea;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpec() {
        return spec;
    }


    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof Product) {
            isEqual = (this.getId().equals(((Product) object).getId()));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.getId());
    }
}
