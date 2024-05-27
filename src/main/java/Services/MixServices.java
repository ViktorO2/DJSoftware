package Services;

import Models.DjMix;
import Repo.DbMainRepo;

import java.util.List;

public class MixServices {
   public List<DjMix> load(){
       return DbMainRepo.getDjMixList();
   }
    public void save(DjMix djMix) {
        DbMainRepo.getDjMixList().add(djMix);
    }
}
