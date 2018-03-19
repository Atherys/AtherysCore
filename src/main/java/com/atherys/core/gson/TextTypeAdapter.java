package com.atherys.core.gson;

import com.google.gson.TypeAdapter;
import org.spongepowered.api.text.Text;

/**
 * A Gson {@link TypeAdapter} for Sponge's {@link Text}
 */
public class TextTypeAdapter extends ConfigurateAdapter<Text> {

    public TextTypeAdapter() {
        super( Text.class );
    }
}
