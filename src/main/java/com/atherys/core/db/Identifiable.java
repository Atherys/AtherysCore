package com.atherys.core.db;

import javax.annotation.Nonnull;
import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {

    @Nonnull
    ID getId();

}
