
public class Experience {
    private String company;
    private String position;
    private String period;
    private String description;

    public Experience(String company, String position, String period, String description) {
        this.company = company;
        this.position = position;
        this.period = period;
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getPeriod() {
        return period;
    }

    public String getDescription() {
        return description;
    }
}
