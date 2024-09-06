package tomato.addons.O3Trigger;

public class Trigger {
    private String search;
    private String say;

    public Trigger(String search, String say) {
        this.search = search;
        this.say = say;
    }

    public String getSearch() {
        return search;
    }

    public String getSay() {
        return say;
    }

    @Override
    public String toString() {
        return "Trigger { search: '" + search + "', say: '" + say + "' }";
    }
}