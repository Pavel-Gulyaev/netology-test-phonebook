import service.PhoneBookService;
import service.PhoneBookServiceImpl;

public class Main {
    public static void main(String[] args) {
        PhoneBookService service = new PhoneBookServiceImpl();
        service.work();
    }
}
