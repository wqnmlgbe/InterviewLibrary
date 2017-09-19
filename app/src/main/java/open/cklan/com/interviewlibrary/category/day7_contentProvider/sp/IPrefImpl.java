package open.cklan.com.interviewlibrary.category.day7_contentProvider.sp;

import java.util.Map;

/**
 * AUTHOR：lanchuanke on 17/9/15 10:10
 */
public interface IPrefImpl {
    String getPrefString(String key, String defaultValue);

    void setPrefString(String key, String value);

    boolean getPrefBoolean(String key, boolean defaultValue);

    void setPrefBoolean(final String key, final boolean value);

    void setPrefInt(final String key, final int value);

    int getPrefInt(final String key, final int defaultValue);

    void setPrefFloat(final String key, final float value);

    float getPrefFloat(final String key, final float defaultValue);

    void setPrefLong(final String key, final long value);

    long getPrefLong(final String key, final long defaultValue);

    void removePreference(final String key);

    boolean hasKey(String key);

    Map<String,?> getAll();
}
