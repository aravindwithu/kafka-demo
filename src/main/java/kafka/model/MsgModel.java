package kafka.model;

public class MsgModel implements Cloneable {
    public int msgNo;
    public String msgStr; 

    public MsgModel(){
  
    }

    public MsgModel(
        int msgNo,
        String msgStr
    ) {
        super();
        this.msgNo = msgNo;
        this.msgStr = msgStr; 
    }

    public int getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(int msgNo) {
        this.msgNo = msgNo;
    }

    public String getMsgStr() {
        return msgStr;
    }

    public void setMsgStr(String msgStr) {
        this.msgStr = msgStr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + msgNo;
        result = prime * result + ((msgStr == null) ? 0 : msgStr.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MsgModel other = (MsgModel) obj;
        if (msgNo != other.msgNo)
            return false;
        if (msgStr == null) {
            if (other.msgStr != null)
                return false;
        } else if (!msgStr.equals(other.msgStr))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MsgModel [msgNo=" + msgNo + ", msgStr=" + msgStr + "]";
    }


}