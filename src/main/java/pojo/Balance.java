package pojo;

public class Balance {

    String PatientAccount;//患者账号

    float balance;//患者账号对应的余额

    public String getPatientAccount() {
        return PatientAccount;
    }

    public void setPatientAccount(String patientAccount) {
        PatientAccount = patientAccount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Balance(String patientAccount, float balance) {
        PatientAccount = patientAccount;
        this.balance = balance;
    }

    public Balance() {
    }
}
