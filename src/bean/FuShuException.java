package bean;

class FuShuException extends Exception {//getMessage();
    private int value;
    public FuShuException() {
        super();
    }
    public FuShuException(String msg,int value) {
        super(msg);
        this.value=value;
    }
    public int getValue() {
        return value;
    }
    
    
}