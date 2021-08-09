package pojo;

public class FormValue {
    String id;
    String yinZhangCompany;
    String reason;
    String yinZhangType;


    public FormValue(String id, String yinZhangCompany, String reason, String yinZhangType) {
        this.id = id;
        this.yinZhangCompany = yinZhangCompany;
        this.reason = reason;
        this.yinZhangType = yinZhangType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYinZhangCompany() {
        return yinZhangCompany;
    }

    public void setYinZhangCompany(String yinZhangCompany) {
        this.yinZhangCompany = yinZhangCompany;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getYinZhangType() {
        return yinZhangType;
    }

    public void setYinZhangType(String yinZhangType) {
        this.yinZhangType = yinZhangType;
    }
}
