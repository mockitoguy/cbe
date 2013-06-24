
public class SeatParameters {
    private int mPrice;
    private boolean mIsReserved;
    private int mSeatNumber;

    public SeatParameters(int mPrice) {
        this.mPrice = mPrice;
        mIsReserved = false;
    }

    public boolean isIsReserved() {
        return mIsReserved;
    }

    public void setIsReserved(boolean mIsReserved) {
        this.mIsReserved = mIsReserved;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getSeatNumber() {
        return mSeatNumber;
    }

    public void setSeatNumber(int mSeatNumber) {
        this.mSeatNumber = mSeatNumber;
    }
}
