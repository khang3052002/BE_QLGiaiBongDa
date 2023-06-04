package backend.qlgiaibongda.repository.MuaGiaiRepository;

import backend.qlgiaibongda.entity.MuaGiaiEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
public class MuaGiaiCustomRepositoryImpl implements IMuaGiaiCustomRepository{
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<MuaGiaiEntity> findLeaguesWithFiltered(String keyword, Integer trangthai) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MuaGiaiEntity> cq = cb.createQuery(MuaGiaiEntity.class);
        Root<MuaGiaiEntity> root = cq.from(MuaGiaiEntity.class);


        // Create a list to store the predicates
        List<Predicate> predicates = new ArrayList<>();


        if(keyword !=null)
        {
            Predicate keywordPredicate = cb.like(root.get("ten"),"%"+keyword+"%");
            predicates.add(keywordPredicate);
        }
//        if(trangthai != null)
//        {
//            Predicate trangThaiPredicate = cb.equal(root.get("trangThai"),trangthai);
//            predicates.add(trangThaiPredicate);
//        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<MuaGiaiEntity> query=entityManager.createQuery(cq);

        return query.getResultList();

    }
}
