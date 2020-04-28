package com.atherys.core.db.migration;

import java.time.LocalDateTime;
import java.util.Objects;

public class Migration {

    private LocalDateTime timeExecuted;

    private String name;

    private String contents;

    public Migration(LocalDateTime timeExecuted, String name, String contents) {
        this.timeExecuted = timeExecuted;
        this.name = name;
        this.contents = contents;
    }

    public LocalDateTime getTimeExecuted() {
        return timeExecuted;
    }

    public String getName() {
        return name;
    }


    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Migration migration = (Migration) o;
        return Objects.equals(timeExecuted, migration.timeExecuted) &&
                Objects.equals(name, migration.name) &&
                Objects.equals(contents, migration.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeExecuted, name, contents);
    }
}
