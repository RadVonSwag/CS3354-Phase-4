public class SearchFunction {
    static String listUserFilters;
    public static void ApplyUserFilter(String male) {
        listUserFilters=male;
    }

    public static String ListUserFilters() {
        return listUserFilters;
    }
}
