package model.API.outputAPI;

public class OfflineOutputAPI implements OutputAPI{
    @Override
    public void postEmail(String text, int selectedCur, int compareCur, String rate, String total) {
        System.out.println("email sent");
    }
}
