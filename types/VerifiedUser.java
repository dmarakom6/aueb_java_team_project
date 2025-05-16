package types;

public class VerifiedUser extends User {
    private String verificationCode;

    public VerifiedUser(String username, String verificationCode) {
        super(username);
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public boolean checkIsVerified() {
        return true; // VerifiedUser is always verified
    }

    @Override
    public void printDetails() {
        System.out.println("User: " + username + " (Verified)");
        System.out.println("Verification Code (Encrypted): " + verificationCode.hashCode());
        System.out.println("Reviews submitted: " + reviews.size());
    }
    
}
