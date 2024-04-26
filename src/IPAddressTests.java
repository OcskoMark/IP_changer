import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPAddressTests {
    private static IPAddress decimalAddress;
    private static IPAddress binaryAddress;

    @BeforeAll
    static void setUpAll() {
        decimalAddress =  new IPAddress("128.255.0.127");
        binaryAddress = new IPAddress("00000001.11000000.11111111.00000000");
    }

    @Test
    void testPrintChangedAddress() {
        assertTrue(IPAddress.checkIPAddressIsValid(decimalAddress.getDecimalAddress()), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid(decimalAddress.getBinaryAddress()), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid(binaryAddress.getDecimalAddress()), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid(binaryAddress.getBinaryAddress()), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid("0.0.0.0"), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid("255.255.255.255"), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid("00000000.00000000.00000000.00000000"), "The IP address validation is incorrect.");
        assertTrue(IPAddress.checkIPAddressIsValid("11111111.11111111.11111111.11111111"), "The IP address validation is incorrect.");

        assertFalse(IPAddress.checkIPAddressIsValid("1.2.3.4."), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("00000001.11000011.01010101.10101010."), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("01.2.3.4"), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("1.2.3.04"), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("1.10.11.00000100"), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("a1.2.3.4"), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("1.-2.3.4"), "The IP address validation is incorrect.");
        assertFalse(IPAddress.checkIPAddressIsValid("255.255.256.255"), "The IP address validation is incorrect.");
    }

    @Test
    void testChanges() {
        assertEquals("10000000.11111111.00000000.01111111", decimalAddress.getBinaryAddress());
        assertEquals("1.192.255.0", binaryAddress.getDecimalAddress());
    }
}
