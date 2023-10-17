package dao;
import vo.*;
import java.util.List;

public interface RecDAO {
    List<Rec> getAllRecs(int user_id);

}
