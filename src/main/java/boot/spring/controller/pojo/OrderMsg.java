package boot.spring.controller.pojo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class OrderMsg  extends BaseRowModel {
    @ExcelProperty(value = "工单号" ,index = 0)
    String id       ;
    @ExcelProperty(value = "发起人" ,index = 0)
     String ownerName;
    @ExcelProperty(value = "创建时间" ,index = 0)

    String createTime;
    @ExcelProperty(value = "状态" ,index = 0)

    String status;
    @ExcelProperty(value = "印章所属公司" ,index = 0)

    String companyYz;
    @ExcelProperty(value = "印章类型" ,index = 0)

    String typeYz;
    @ExcelProperty(value = "审批人" ,index = 0)

    String spr;
    @ExcelProperty(value = "原因" ,index = 0)

    String reason;
    String json;


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "OrderMsg{" +
                "id='" + id + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", companyYz='" + companyYz + '\'' +
                ", typeYz='" + typeYz + '\'' +
                ", spr='" + spr + '\'' +
                ", reason='" + reason + '\'' +
                ", json='" + json + '\'' +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCompanyYz() {
        return companyYz;
    }

    public void setCompanyYz(String companyYz) {
        this.companyYz = companyYz;
    }

    public String getTypeYz() {
        return typeYz;
    }

    public void setTypeYz(String typeYz) {
        this.typeYz = typeYz;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public OrderMsg(String id, String ownerName, String createTime, String status) {
        this.id = id;
        this.ownerName = ownerName;
        this.createTime = createTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
