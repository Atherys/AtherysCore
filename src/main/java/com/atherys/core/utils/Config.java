package com.atherys.core.utils;

import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    private ObjectMapper<Config>.BoundInstance configMapper;
    protected ConfigurationLoader<CommentedConfigurationNode> loader;

    protected Config( String directory ) throws IOException {

        File workingDir = new File( directory + "/config.conf" );
        if ( !workingDir.exists() ) {
            if ( workingDir.mkdirs() && workingDir.createNewFile() ) {
                this.loader = HoconConfigurationLoader.builder().setPath(workingDir.toPath()).build();
            } else {
                throw new IOException("Failed to create config directory/file.");
            }
        } else {
            this.loader = HoconConfigurationLoader.builder().setPath( workingDir.toPath() ).build();
        }

        try {
            this.configMapper = ObjectMapper.forObject(this);
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }

        this.load();
    }

    public void save() {
        try {
            SimpleConfigurationNode out = SimpleConfigurationNode.root();
            this.configMapper.serialize(out);
            this.loader.save(out);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            this.configMapper.populate( this.loader.load() );
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }

}
