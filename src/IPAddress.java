import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddress {
    private static final int NUMBER_OF_OCTETS = 4;
    private String decimalAddress = "";
    private String binaryAddress = "";

    private boolean decimal;

    private static final String DECIMAL_IP_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";
    private static final Pattern DECIMAL_IP_FORMAT = Pattern.compile(DECIMAL_IP_REGEX);
    private static final String IP_BINARY_REGEX = "([0-1]{8}\\.?\\b){4}";
    private static final Pattern IP_BINARY_FORMAT = Pattern.compile(IP_BINARY_REGEX);

    public IPAddress(String address) {
        if (checkIsValidDecimalIPAddress(address)) {
            this.decimalAddress = address;
            this.decimal = true;

            this.binaryAddress = convertToBinaryAddress(getOctets(this.decimalAddress));
        } else {
            this.binaryAddress = address;
            this.decimalAddress = convertToDecimalAddress(getOctets(this.binaryAddress));
            this.decimal = false;
        }
    }

    public IPAddress() {
        StringBuilder address = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            address.append((random.nextInt(255) + 1)).append(".");
        }

        this.decimalAddress = deleteLastDot(address.toString());
        this.binaryAddress = convertToBinaryAddress(getOctets(this.decimalAddress));
    }

    private static boolean checkIsValidBinaryIPAddress(String inputBinaryAddress) {
        Matcher binaryAddressFormatChecker = IP_BINARY_FORMAT.matcher(inputBinaryAddress);
        return binaryAddressFormatChecker.matches();
    }

    private static boolean checkIsValidDecimalIPAddress(String inputDecimalIpAddress) {
        Matcher decimalAddressFormatChecker = DECIMAL_IP_FORMAT.matcher(inputDecimalIpAddress);
        return decimalAddressFormatChecker.matches();
    }

    public static boolean checkIPAddressIsValid (String address) {
        return (checkIsValidBinaryIPAddress(address) || checkIsValidDecimalIPAddress(address));
    }

    private String deleteLastDot(String address) {
        if (address.charAt(address.length() - 1) == '.') {
            return address.substring(0, address.length() - 1);
        } else {
            return address;
        }
    }

    private String[] getOctets(String address) {
        return address.split("\\.");
    }

    private String convertToDecimalAddress(String[] binaryOctets) {
        StringBuilder address = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            address.append(Integer.parseInt(binaryOctets[i], 2)).append(".");
        }

        return deleteLastDot(address.toString());
    }

    private String convertToBinaryAddress(String[] decimalOctets) {
        StringBuilder address = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_OCTETS; i++) {
            String binaryOctet = Integer.toBinaryString(Integer.parseInt(decimalOctets[i]));
            address.append(String.join("",
                    Collections.nCopies((8 - binaryOctet.length()), "0"))).append(binaryOctet).append(".");
        }

        return deleteLastDot(address.toString());
    }

    public boolean isDecimal() {
        return decimal;
    }

    public String getDecimalAddress() {
        return decimalAddress;
    }

    public String getBinaryAddress() {
        return binaryAddress;
    }
}
