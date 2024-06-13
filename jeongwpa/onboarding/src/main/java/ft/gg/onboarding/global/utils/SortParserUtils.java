package ft.gg.onboarding.global.utils;

import org.springframework.data.domain.Sort;

public class SortParserUtils {

    public static final SortParserUtils INSTANCE = newInstance();

    private SortParserUtils() {
    }

    private static SortParserUtils newInstance() {
        return new SortParserUtils();
    }

    public Sort parse(String sort) {
        if (sort.equals(("name"))) {
            return Sort.by("name").descending();
        }
        return Sort.by("id").descending();
    }

    public Sort parse(String sort, String order) {
        Sort sortObj = sort.equals("name") ? Sort.by("name") : Sort.by("id");
        return order.equals("asc") ? sortObj.ascending() : sortObj.descending();
    }
}
