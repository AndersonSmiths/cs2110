public class PhoneDemo {
    public static void main(String[] args) {
        String phone = "607-555-5309";
        String[] tokens = phone.split("-");
        String areaCode = tokens[0];
        String exchange = tokens[1];
        String lineNum = tokens[2];

        // Print with different format
        System.out.println("(" + areaCode + ")" + exchange + "." + lineNum);
    }
}
