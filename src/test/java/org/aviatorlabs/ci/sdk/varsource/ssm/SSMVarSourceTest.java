package org.aviatorlabs.ci.sdk.varsource.ssm;

import org.aviatorlabs.ci.sdk.varsource.VarSourceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SSMVarSourceTest {
    @Test
    void basicTest() {
        // Arrange

        // Act
        SSMVarSource varSource = SSMVarSource.create("ssm", "us-east-1");

        // Assert
        Assertions.assertEquals(VarSourceType.SSM, varSource.getType());
        assertEquals("us-east-1", varSource.getConfig().region());
    }

    @Test
    void nullRegion() {
        assertThrows(IllegalArgumentException.class, () -> SSMVarSource.create("ssm", null));
    }
}