package com.atherys.core.db;

import javax.annotation.Nonnull;

public interface Identifiable<ID> {

    @Nonnull
    ID getId();

}
