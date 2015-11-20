package hr.nullteam.rsc.util;

import java.util.List;

public final class ListUtils {

    public boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
