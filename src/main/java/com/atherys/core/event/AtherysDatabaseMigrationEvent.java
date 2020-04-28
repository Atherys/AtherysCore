package com.atherys.core.event;

import com.atherys.core.AtherysCore;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AtherysDatabaseMigrationEvent implements Event {

    private Cause cause;

    private List<Path> scriptFiles = new ArrayList<>();

    public AtherysDatabaseMigrationEvent() {
        this.cause = Cause.builder().append(AtherysCore.getInstance()).build(Sponge.getCauseStackManager().getCurrentContext());
    }

    public void addMigrationScripts(Path folder) throws IOException {
        if (!Files.isDirectory(folder)) {
            throw new IllegalArgumentException("The provided migration path should be a folder containing migration scripts");
        }

        Files.list(folder).forEach(scriptFiles::add);
    }

    public List<Path> getScripts() {
        return scriptFiles;
    }

    @Override
    public Cause getCause() {
        return cause;
    }
}
