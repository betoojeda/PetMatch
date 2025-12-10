package com.petmatch.repository;

import com.petmatch.model.LostPetPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostPetPostRepository extends JpaRepository<LostPetPost, Long> {

    // Busca posts que no estén marcados como "REUNITED" (reunidos)
    Page<LostPetPost> findByStatusNot(LostPetPost.PostStatus status, Pageable pageable);
}
