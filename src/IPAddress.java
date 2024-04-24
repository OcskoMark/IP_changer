import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddress {
    private static final int NUMBER_OF_OCTETS = 4;
    private int[] decimalOctets = new int[NUMBER_OF_OCTETS];
    private String binaryAddress = "";

    private boolean isDecimal;

    private static final String DECIMAL_IP_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
    private static final Pattern DECIMAL_IP_PATTERN = Pattern.compile(DECIMAL_IP_REGEX);
    private static final String IP_BINARY_REGEX = "([0-1]{8}\\.?\\b){4}";
    private static final Pattern IP_BINARY_PATTERN = Pattern.compile(IP_BINARY_REGEX);

    public IPAddress(String address) {
        if (checkIsValidDecimalIPAddress(address)) {
            String[] octetStrings = getOctetStrings(address);
            this.isDecimal = true;

            for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
                this.decimalOctets[i] = Integer.parseInt(octetStrings[i]);
            }

            convertToBinary(this.decimalOctets);
        } else {
            this.binaryAddress = address;
            convertToDecimal(getOctetStrings(this.binaryAddress));
            this.isDecimal = false;
        }
    }

    public IPAddress() {
        Random random = new Random();
        decimalOctets[0] = random.nextInt(255) + 1;

        for (int i = 1; i < NUMBER_OF_OCTETS; i++) {
            decimalOctets[i] = random.nextInt(256);
        }

        convertToBinary(decimalOctets);
    }

    private static boolean checkIsValidBinaryIPAddress(String inputBinaryAddress) {
        Matcher binaryMatcher = IP_BINARY_PATTERN.matcher(inputBinaryAddress);

        return binaryMatcher.matches();
    }

    private static boolean checkIsValidDecimalIPAddress(String inputDecimalIpAddress) {
        Matcher decimalMatcher = DECIMAL_IP_PATTERN.matcher(inputDecimalIpAddress);

        return decimalMatcher.matches();
    }

    public static boolean checkIPAddressIsValid (String address) {
        return (checkIsValidBinaryIPAddress(address) || checkIsValidDecimalIPAddress(address));
    }

    private String[] getOctetStrings(String addressString) {
        return addressString.split("\\.");
    }

    private void convertToDecimal(String[] binaryOctetStrings) {
        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            decimalOctets[i] = Integer.parseInt(binaryOctetStrings[i], 2);
        }
    }

    private void convertToBinary(int[] decimalOctets) {
        String binaryOctet = "";

        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            binaryOctet = Integer.toBinaryString(decimalOctets[i]);
            binaryAddress += String.join("", Collections.nCopies((8 - binaryOctet.length()), "0")) + binaryOctet + ".";
        }

        binaryAddress = binaryAddress.substring(0, binaryAddress.length() - 1);
    }

    public void printChangedIPAddress() {
        if (isDecimal) {
            System.out.println("A megadott IP-cím bináris formátumban: " + binaryAddress);
        } else {
            System.out.println("A megadott IP-cím decimális formátumban: " + getDecimalAddress());
        }
    }

    public String getDecimalAddress() {
        String decimalAddress = "";

        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            decimalAddress += decimalOctets[i] + ".";
        }

        return decimalAddress.substring(0, decimalAddress.length() - 1);
    }

    public String getBinaryAddress() {
        return binaryAddress;
    }
}
