package com.sap.cloud.lm.sl.mta.parsers.v1;

import static com.sap.cloud.lm.sl.mta.handlers.v1.Schemas.PLATFORM;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sap.cloud.lm.sl.common.ParsingException;
import com.sap.cloud.lm.sl.mta.model.v1.Target;
import com.sap.cloud.lm.sl.mta.model.v1.TargetModuleType;
import com.sap.cloud.lm.sl.mta.model.v1.TargetResourceType;
import com.sap.cloud.lm.sl.mta.model.v1.Target.Builder;
import com.sap.cloud.lm.sl.mta.parsers.ListParser;
import com.sap.cloud.lm.sl.mta.parsers.ModelParser;
import com.sap.cloud.lm.sl.mta.schema.MapElement;

public class TargetParser extends ModelParser<Target> {

    protected static final String PROCESSED_OBJECT_NAME = "MTA platform";

    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    public static final String PROPERTIES = "properties";
    public static final String MODULE_TYPES = "module-types";
    public static final String RESOURCE_TYPES = "resource-types";

    protected final Set<String> usedModuleTypeNames = new HashSet<String>();
    protected final Set<String> usedResourceTypeNames = new HashSet<String>();

    public TargetParser(Map<String, Object> source) {
        this(PLATFORM, source);
    }

    protected TargetParser(MapElement schema, Map<String, Object> source) {
        super(PROCESSED_OBJECT_NAME, schema, source);
    }

    @Override
    public Target parse() throws ParsingException {
        Builder builder = new Builder();
        builder.setName(getName());
        builder.setType(getType());
        builder.setDescription(getDescription());
        builder.setProperties(getProperties());
        builder.setModuleTypes1(getModuleTypes1());
        builder.setResourceTypes1(getResourceTypes1());
        return builder.build();
    }

    protected String getName() throws ParsingException {
        return getStringElement(NAME);
    }

    protected String getType() throws ParsingException {
        return getStringElement(TYPE);
    }

    protected String getDescription() throws ParsingException {
        return getStringElement(DESCRIPTION);
    }

    protected Map<String, Object> getProperties() {
        return getMapElement(PROPERTIES);
    }

    protected List<TargetModuleType> getModuleTypes1() throws ParsingException {
        return getListElement(MODULE_TYPES, new ListParser<TargetModuleType>() {
            @Override
            protected TargetModuleType parseItem(Map<String, Object> map) throws ParsingException {
                return getModuleTypeParser(map).setUsedValues(usedModuleTypeNames)
                    .parse();
            }
        });
    }

    protected PlatformModuleTypeParser getModuleTypeParser(Map<String, Object> source) {
        return new PlatformModuleTypeParser(source);
    }

    protected List<TargetResourceType> getResourceTypes1() throws ParsingException {
        return getListElement(RESOURCE_TYPES, new ListParser<TargetResourceType>() {
            @Override
            protected TargetResourceType parseItem(Map<String, Object> map) throws ParsingException {
                return getResourceTypeParser(map).setUsedValues(usedResourceTypeNames)
                    .parse();
            }
        });
    }

    protected PlatformResourceTypeParser getResourceTypeParser(Map<String, Object> source) {
        return new PlatformResourceTypeParser(source);
    }

}
