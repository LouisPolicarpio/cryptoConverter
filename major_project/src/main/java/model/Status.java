package model;

public class Status {
    private String timestamp;
    private int error_code;
    private String error_message;
    private int elapsed;
    private int credit_count;
    private String notice;

    public Status(String timestamp, int error_code, String error_message, int elapsed, int credit_count, String notice) {
        this.timestamp = timestamp;
        this.error_code = error_code;
        this.error_message = error_message;
        this.elapsed = elapsed;
        this.credit_count = credit_count;
        this.notice = notice;
    }

    public int getError_code() {
        return error_code;
    }


}
