import org.apache.avro.specific.SpecificRecord;

public class Customer  {
    private int customerID;
    private String customerName;

    private  Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public int getCustomerID() {
        return customerID;
    }


    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public static class Builder {
        private int customerID;
        private String customerName;

        public Builder() {
        }

        public Builder setCustomerID(int customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Customer build() {
            return new Customer(customerID, customerName);
        }
    }
}
