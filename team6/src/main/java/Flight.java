import java.util.Objects;

public class Flight {

    private final String number;

    public Flight(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        return Objects.equals(this.number, other.number);
    }
}
