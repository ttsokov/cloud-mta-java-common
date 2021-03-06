package com.sap.cloud.lm.sl.mta.handlers.v2;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import com.sap.cloud.lm.sl.mta.handlers.v2.DescriptorMerger;
import com.sap.cloud.lm.sl.mta.handlers.v2.DescriptorParser;

public class DescriptorMergerTest extends com.sap.cloud.lm.sl.mta.handlers.v1.DescriptorMergerTest {

    @Parameters
    public static Iterable<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
// @formatter:off
            // (0) Valid deployment and extension descriptor:
            {
                "/mta/sample/v2/mtad-01.yaml", new String[] { "/mta/sample/v2/config-01.mtaext", }, "R:/mta/sample/v2/merged-04.yaml.json",
            },
            // (1) Valid deployment and extension descriptors (multiple):
            {
                "/mta/sample/v2/mtad-01.yaml", new String[] { "/mta/sample/v2/config-01.mtaext", "/mta/sample/v2/config-05.mtaext", }, "R:/mta/sample/v2/merged-05.yaml.json",
            },
// @formatter:on
        });
    }

    public DescriptorMergerTest(String deploymentDescriptorLocation, String[] extensionDescriptorLocations, String expected) {
        super(deploymentDescriptorLocation, extensionDescriptorLocations, expected);
    }

    @Override
    protected DescriptorMerger createDescriptorMerger() {
        return new DescriptorMerger();
    }

    @Override
    protected DescriptorParser getDescriptorParser() {
        return new DescriptorParser();
    }

}
