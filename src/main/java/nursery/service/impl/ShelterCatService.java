package nursery.service.impl;

import nursery.model.ShelterCat;

public interface ShelterCatService {

    ShelterCat createShelterCat(ShelterCat shelterCat);
    ShelterCat findShelterCat(Long id);
    ShelterCat updateShelterCat(Long id, ShelterCat shelterCat);
    void deleteShelterCat(Long id);

    public String welcomesUser(Long id);
    public String info(Long id);
}
