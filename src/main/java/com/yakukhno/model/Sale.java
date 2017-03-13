package com.yakukhno.model;

public class Sale {
    private Product product;
    private int change;
    private Status status;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Builder {
        private Sale sale = new Sale();

        public Builder setProduct(Product product) {
            sale.product = product;
            return this;
        }

        public Builder setChange(int change) {
            sale.change = change;
            return this;
        }

        public Builder setStatus(Status code) {
            sale.status = code;
            return this;
        }

        public Sale build() {
            return sale;
        }
    }
}
