package com.atherys.core.item;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;

import java.util.UUID;

public class AttributeModification {

    private UUID uuid;

    private String id;

    private String slot;

    private float amount;

    private int operation;

    private AttributeModification(AttributeModification attributeModification) {
        this.id = attributeModification.id;
        this.slot = attributeModification.slot;
        this.amount = attributeModification.amount;
        this.operation = attributeModification.operation;
    }

    public AttributeModification(String id, String slot, float amount, int operation) {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.slot = slot;
        this.amount = amount;
        this.operation = operation;
    }

    public String getAttributeId() {
        return id;
    }

    public AttributeModification setAttributeId(String id) {
        this.id = id;
        return this;
    }

    public String getSlot() {
        return slot;
    }

    public AttributeModification setSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public AttributeModification setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public int getOperation() {
        return operation;
    }

    public AttributeModification setOperation(int operation) {
        this.operation = operation;
        return this;
    }

    public AttributeModification copy() {
        return new AttributeModification(this);
    }

    public DataContainer asDataContainer() {
        DataContainer dataContainer = DataContainer.createNew();

        dataContainer.set(DataQuery.of("AttributeName"),this.id);
        dataContainer.set(DataQuery.of("Name"),this.id);
        dataContainer.set(DataQuery.of("Amount"),this.amount);
        dataContainer.set(DataQuery.of("Operation"),this.operation);
        dataContainer.set(DataQuery.of("Slot"),this.slot);
        dataContainer.set(DataQuery.of("UUIDMost"),uuid.getMostSignificantBits());
        dataContainer.set(DataQuery.of("UUIDLeast"),uuid.getLeastSignificantBits());

        return dataContainer;
    }
}
