package kafka;

public class MsgModel implements Cloneable {
    public int msgNo;
    public String query;
    public String outputType;
    public String sourceDB;
    public String mailTo;
    public String mailFrom;
    public String mailSubject;
    public String fileName;
    public String fileDelimiter;

    public MsgModel(){
        
    }

    public MsgModel(
        int msgNo,
        String query,
        String outputType,
        String sourceDB,
        String mailTo,
        String mailFrom,
        String mailSubject,
        String fileName,
        String fileDelimiter
    ) {
        super();
        this.msgNo = msgNo;
        this.query = query;
        this.outputType = outputType;
        this.sourceDB = sourceDB;
        this.mailTo = mailTo;
        this.mailFrom = mailFrom;
        this.mailSubject = mailSubject;
        this.fileName = fileName;
        this.fileDelimiter = fileDelimiter;
    }

    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    //     MsgModel clone = null;
    //     try {
    //         clone = (MsgModel) super.clone();

    //         // Copy new date object to cloned method
    //         // clone.setDob((Date) this.getDob().clone());
    //     } catch (CloneNotSupportedException e) {
    //         throw new RuntimeException(e);
    //     }
    //     return clone;
    // }

    public int getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(int msgNo) {
        this.msgNo = msgNo;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getSourceDB() {
        return sourceDB;
    }

    public void setSourceDB(String sourceDB) {
        this.sourceDB = sourceDB;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDelimiter() {
        return fileDelimiter;
    }

    public void setFileDelimiter(String fileDelimiter) {
        this.fileDelimiter = fileDelimiter;
    }

    @Override
    public String toString() {
        return "MsgModel [fileDelimiter=" + fileDelimiter + ", fileName=" + fileName + ", mailFrom=" + mailFrom
                + ", mailSubject=" + mailSubject + ", mailTo=" + mailTo + ", msgNo=" + msgNo + ", outputType="
                + outputType + ", query=" + query + ", sourceDB=" + sourceDB + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileDelimiter == null) ? 0 : fileDelimiter.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((mailFrom == null) ? 0 : mailFrom.hashCode());
        result = prime * result + ((mailSubject == null) ? 0 : mailSubject.hashCode());
        result = prime * result + ((mailTo == null) ? 0 : mailTo.hashCode());
        result = prime * result + msgNo;
        result = prime * result + ((outputType == null) ? 0 : outputType.hashCode());
        result = prime * result + ((query == null) ? 0 : query.hashCode());
        result = prime * result + ((sourceDB == null) ? 0 : sourceDB.hashCode());
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
        if (fileDelimiter == null) {
            if (other.fileDelimiter != null)
                return false;
        } else if (!fileDelimiter.equals(other.fileDelimiter))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (mailFrom == null) {
            if (other.mailFrom != null)
                return false;
        } else if (!mailFrom.equals(other.mailFrom))
            return false;
        if (mailSubject == null) {
            if (other.mailSubject != null)
                return false;
        } else if (!mailSubject.equals(other.mailSubject))
            return false;
        if (mailTo == null) {
            if (other.mailTo != null)
                return false;
        } else if (!mailTo.equals(other.mailTo))
            return false;
        if (msgNo != other.msgNo)
            return false;
        if (outputType == null) {
            if (other.outputType != null)
                return false;
        } else if (!outputType.equals(other.outputType))
            return false;
        if (query == null) {
            if (other.query != null)
                return false;
        } else if (!query.equals(other.query))
            return false;
        if (sourceDB == null) {
            if (other.sourceDB != null)
                return false;
        } else if (!sourceDB.equals(other.sourceDB))
            return false;
        return true;
    }
}